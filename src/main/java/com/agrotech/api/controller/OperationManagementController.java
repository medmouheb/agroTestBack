package com.agrotech.api.controller;


import com.agrotech.api.dto.OperationManagementDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Drivers;
import com.agrotech.api.model.OperationManagement;
import com.agrotech.api.services.DriversService;
import com.agrotech.api.services.OperationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationmanagement")
@RequiredArgsConstructor
public class OperationManagementController {

    private final OperationManagementService operationManagementService;

    private final DriversService driversService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody OperationManagementDto logis) {
        OperationManagementDto response = operationManagementService.create(logis);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        OperationManagement response=operationManagementService.findByNomOperation(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody OperationManagementDto campany) throws NotFoundException {
        OperationManagementDto response = operationManagementService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        OperationManagementDto response = operationManagementService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<OperationManagementDto> response = operationManagementService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<OperationManagement> response = operationManagementService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        OperationManagementDto response = operationManagementService.findByOperationId(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        operationManagementService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        operationManagementService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        operationManagementService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<OperationManagement> response = operationManagementService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





    //GetList des drivers get nnomDuChauffeur
    @GetMapping("/getlistdrivers")
    public ResponseEntity<?> getlistdrivers() throws NotFoundException {
        List<Drivers> drivers = driversService.findByNomDuChauffeurs();
        //Get List des nomDuChauffeur
        List<String> driversName = new ArrayList<>();
        // Extract company names from the list of companies
        for (Drivers driver : drivers) {
            driversName.add(driver.getNomDuChauffeur());
        }
        return new ResponseEntity<>(driversName, HttpStatus.OK);

    }


}