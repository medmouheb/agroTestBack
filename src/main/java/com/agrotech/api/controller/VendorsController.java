package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorsRepository;
import com.agrotech.api.dto.VendorsDto;
import com.agrotech.api.dto.WarehouseDto;
import com.agrotech.api.enums.CostCenterType;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Vendors;
import com.agrotech.api.model.Warehouse;
import com.agrotech.api.services.VendorsService;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorsController {


    private final VendorsService vendorsService ;
    private final VendorsRepository vendorsRepository;


    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorsRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorsDto vendors) {
        VendorsDto response = vendorsService.create(vendors);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorsDto vendors) throws NotFoundException {
        VendorsDto response = vendorsService.update(id, vendors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorsDto response = vendorsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorsDto> response = vendorsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Vendors> response = vendorsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorsDto response = vendorsService.findByVendorCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Vendors> response = vendorsService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





//    @PostMapping("/upload")
//    public String uploadCSV(@RequestParam("file") MultipartFile file) {
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
//            String line;
//            int i=0;
//            while ((line = br.readLine()) != null) {
//                i++;
//                if(i==1){
//                    continue;
//                }
//                String[] data = line.replace("\"", "").split(",");
//                WarehouseDto w= new WarehouseDto();
//                w.setCode(data[0]);
//                w.setName(data[1]);
//                w.setType(data[2]);
//                List<String> stringList = Arrays.asList("ADMIN", "INTERNAL", "EXTERNAL");
//                if(stringList.contains(data[3])){
//                    w.setCostCenterType(CostCenterType.valueOf(data[3]) );
//                }else{
//                    w.setCostCenterType(null);
//                }
//                try {
//                    w.setStartingDate(LocalDate.parse(data[4]));
//
//                }catch (Exception e){
//                    System.out.println(e);
//
//                }
//                w.setIsPrimary(Boolean.valueOf(data[5]));
//                w.setAddress1(data[6]);
//                w.setAddress2(data[7]);
//                w.setCityCode(data[8]);
//                w.setCityName(data[9]);
//                w.setWilayaCode(data[10]);
//                w.setWilayaName(data[11]);
//                w.setZipCode(data[12]);
//                w.setEmail(data[13]);
//                w.setPhoneNumber(data[14]);
//                w.setFaxNumber(data[15]);
//                w.setLatitude( Double.valueOf(data[16]) );
//                w.setLongitude(Double.valueOf(data[17]));
//                w.setIsDeleted(Boolean.valueOf(data[18]));
//                warehouseService.create(w);
//                System.out.println(line);
//            }
//            br.close();
//            return "CSV data uploaded successfully!";
//        } catch (Exception e) {
//            System.out.println(e);
//
//            return "Error uploading CSV data: " + e.getMessage();
//        }
//    }

}
