package com.agrotech.api.controller;

import com.agrotech.api.Repository.FournisseurRepository;
import com.agrotech.api.dto.WarehouseDto;
import com.agrotech.api.enums.CostCenterType;
import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.model.Vendors;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.agrotech.api.dto.FournisseurDto;
import com.agrotech.api.exceptions.CSVReaderException;
import com.agrotech.api.exceptions.EmptyFileException;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.FournisseurService;
import com.agrotech.api.utils.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fournisseur")
@RequiredArgsConstructor
public class FournisseurController {
	
	@Autowired
    private final FournisseurService fournisseurService;
    private final FournisseurRepository fournisseurRepository;


    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        fournisseurRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid FournisseurDto fournisseur) {
        FournisseurDto response = fournisseurService.create(fournisseur);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/import")
    public ResponseEntity<?> importCSV(@RequestPart("file") MultipartFile file) throws CSVReaderException, EmptyFileException {
        List<CSVRecord> read = CSVReader.read(file);
        fournisseurService.importCSV(read);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody FournisseurDto fournisseur
    ) throws NotFoundException {
        FournisseurDto response = fournisseurService.update(id, fournisseur);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) throws NotFoundException {
        FournisseurDto response = fournisseurService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<FournisseurDto> response = fournisseurService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage1(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Fournisseur> response = fournisseurService.findPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        fournisseurService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/csv-template")
    public ResponseEntity<?> downloadCSVTemplate() throws IOException {
        File file = ResourceUtils.getFile("classpath:csv/providers.csv");
        byte[] resource = Files.readAllBytes(file.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        fournisseurService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        fournisseurService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Fournisseur> response = fournisseurService.findArchivedPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/entityCSV")
    public void downloadEntityCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=VendorsData.csv");

        // Get the data from MongoDB (Assuming your entity is called "YourEntity")
        List<Fournisseur> entities = fournisseurRepository.findAll();

        // Create a CSV writer
        CSVWriter csvWriter = new CSVWriter(response.getWriter());

        // Write CSV header
        String[] header = {
                "code",
                "name",
                "type",
                "paymentTerm",
                "currency",
                "address",
                "codeCity",
                "nameCity",
                "wilayaName",
                "wilayaCode",
                "phone",
                "email",
                "zipCode",
                "vendorSKU",
                "isDeleted"
        };
        csvWriter.writeNext(header);

        // Write data rows
        for (Fournisseur entity : entities) {
            String[] data = new String[]{

                    entity.getCode(),
                    entity.getName(),
                    entity.getType(),
                    entity.getPaymentTerm(),
                    entity.getCurrency(),
                    entity.getAddress(),
                    entity.getCodeCity(),
                    entity.getNameCity(),
                    entity.getWilayaName(),
                    entity.getWilayaCode(),
                    entity.getPhone(),
                    entity.getEmail(),
                    entity.getZipCode(),
                    entity.getVendorSKU(),
                    String.valueOf(entity.getIsDeleted()),
            };
            csvWriter.writeNext(data);
        }

        csvWriter.close();
    }

    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
                i++;
                if(i==1){
                    continue;
                }
                String[] data = line.replace("\"", "").split(";");
                FournisseurDto w= new FournisseurDto();
                try{
                    w.setCode(data[0]);
                }catch(Exception e){}
                try{
                    w.setName(data[1]);
                }catch(Exception e){}
                try{
                    w.setType(data[2]);
                }catch(Exception e){}
                try{
                    w.setPaymentTerm(data[3]);
                }catch(Exception e){}
                try{
                    w.setCurrency(data[4]);
                }catch(Exception e){}
                try{
                    w.setAddress(data[5]);
                }catch(Exception e){}
                try{
                    w.setCodeCity(data[6]);
                }catch(Exception e){}
                try{
                    w.setNameCity(data[7]);
                }catch(Exception e){}
                try{
                    w.setWilayaName(data[8]);
                }catch(Exception e){}
                try{
                    w.setWilayaCode(data[9]);
                }catch(Exception e){}
                try{
                    w.setPhone(data[10]);
                }catch(Exception e){}
                try{
                    w.setEmail(data[11]);
                }catch(Exception e){}
                try{
                    w.setZipCode(data[12]);
                }catch(Exception e){}
                try{
                    w.setVendorSKU(data[13]);
                }catch(Exception e){}
                try{
                    w.setIsDeleted(Boolean.valueOf(data[13]) );
                }catch(Exception e){}
                fournisseurService.create(w);
                System.out.println(line);
            }
            br.close();
            return "CSV data uploaded successfully!";
        } catch (Exception e) {
            System.out.println(e);

            return "Error uploading CSV data: " + e.getMessage();
        }
    }
}
