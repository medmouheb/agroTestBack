package com.agrotech.api.controller;

import com.agrotech.api.Repository.WarehouseRepository;
import com.agrotech.api.model.Warehouse;
import com.itextpdf.text.DocumentException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import com.agrotech.api.dto.WarehouseDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.WarehouseService;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.opencsv.CSVWriter;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

	@Autowired
    private final WarehouseService warehouseService;

    @Autowired
    private final WarehouseRepository warehouseRepository;

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        warehouseRepository.deleteAll();
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid WarehouseDto warehouse) throws DocumentException, FileNotFoundException {
        WarehouseDto response = warehouseService.create(warehouse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody @Valid WarehouseDto warehouse
    ) throws NotFoundException {
        WarehouseDto response = warehouseService.update(id, warehouse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        WarehouseDto response = warehouseService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<WarehouseDto> response = warehouseService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Warehouse> response = warehouseService.findPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        warehouseService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        warehouseService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        warehouseService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Warehouse> response = warehouseService.findArchivedPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping(value = "/csv-template")
    public ResponseEntity<?> downloadCSVTemplate() throws IOException {
        File file = ResourceUtils.getFile("classpath:csv/warehouse.csv");
        byte[] resource = Files.readAllBytes(file.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/entityCSV")
    public void downloadEntityCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=warehouseData.csv");

        List<Warehouse> entities = warehouseRepository.findAll();

        CSVWriter csvWriter = new CSVWriter(response.getWriter());

        String[] header = {
                "code",
                "name",
                "type",

                "facilityType",

                "startingDate",
                "isPrimary",
                "address1",
                "address2",
                "cityCode",
                "cityName",
                "wilayaCode",
                "wilayaName",
                "zipCode",
                "email",
                "phoneNumber",
                "faxNumber",
                "latitude",
                "longitude",
                "isDeleted"
        };
        csvWriter.writeNext(header);

        for (Warehouse entity : entities) {
            String[] data = new String[]{
                    String.valueOf(entity.getCode()),
                    String.valueOf(entity.getName()),
                    String.valueOf(entity.getType()),

                    String.valueOf(entity.getFacilityType()),

                    String.valueOf(entity.getStartingDate()),
                    String.valueOf(entity.getIsPrimary()),
                    String.valueOf(entity.getAddress1()),
                    String.valueOf(entity.getAddress2()),
                    String.valueOf(entity.getCityCode()),
                    String.valueOf(entity.getCityName()),
                    String.valueOf(entity.getWilayaCode()),
                    String.valueOf(entity.getWilayaName()),
                    String.valueOf(entity.getZipCode()),
                    String.valueOf(entity.getEmail()),
                    String.valueOf(entity.getPhoneNumber()),
                    String.valueOf(entity.getFaxNumber()),
                    String.valueOf(entity.getLatitude()),
                    String.valueOf(entity.getLongitude()),
                    String.valueOf(entity.getIsDeleted())
            };
            csvWriter.writeNext(data);
        }

        csvWriter.close();
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
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
                String[] data = line.replace("\"", "").replace(",",";").split(";");
                WarehouseDto w= new WarehouseDto();
                try{                w.setCode(data[0]);
                }catch (Exception e){}
                try{                w.setName(data[1]);
                }catch (Exception e){}
                try{                w.setType(data[2]);
                }catch (Exception e){}
                try{
                    w.setVendor(data[3]);
                }catch(Exception e) {
                    System.out.println(e);
                }
                try {
                    w.setCostCenterCode(data[4]);

                }catch (Exception e){
                    System.out.println(e);

                }
                try {
                    w.setCostCenterName(data[5]);

                }catch (Exception e){
                    System.out.println(e);

                }
                try{
                    String t01="";
                    String t02="";
                    if(data[6].split("/")[1].length()<2){
                        t01="0";
                    }
                    if(data[6].split("/")[0].length()<2){
                        t02="0";
                    }
                    String t=data[6].split("/")[2]+"-"+t01+data[6].split("/")[1]+"-"+t02+data[6].split("/")[0];
                    System.out.println(t);
                    w.setStartingDate(LocalDate.parse(t));

                }catch(Exception e){
                    System.out.println(e);
                }
                try{                w.setIsPrimary(Boolean.valueOf(data[7]));
                }catch(Exception e){}
                try{                w.setAddress1(data[8]);
                }catch(Exception e){}

                try{                w.setCityCode(data[9]);
                }catch(Exception e){}


                try{                w.setCityName(data[10]);
                }catch(Exception e){}

                try{                w.setWilayaCode(data[11]);
                }catch(Exception e){}

                try{                w.setWilayaName(data[12]);
                }catch(Exception e){}

                try{                w.setZipCode(data[13]);
                }catch(Exception e){}

                try{                w.setEmail(data[14]);
                }catch(Exception e){}

                try{                w.setPhoneNumber(data[15]);
                }catch(Exception e){}

                try{                w.setFaxNumber(data[16]);
                }catch(Exception e){}

                try{                w.setLatitude( Double.valueOf(data[17]) );
                }catch(Exception e){}

                try{                w.setLongitude(Double.valueOf(data[18]));
                }catch(Exception e){}

                try{                w.setIsDeleted(Boolean.valueOf(data[19]));
                }catch(Exception e){}

                try{                w.setStatus(Boolean.valueOf(data[20]));
                }catch(Exception e){}


                warehouseService.create(w);
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
