package com.agrotech.api.controller;

import com.agrotech.api.dto.DriversDto;
import com.agrotech.api.dto.TimeSlotDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.*;
import com.agrotech.api.services.DivisionService;
import com.agrotech.api.services.DriversService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriversController {


    private final DriversService driversService;

    private final DivisionService divisionService;
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody DriversDto drivers) {
        // Appelez le service pour créer le conducteur avec les informations mises à jour
        DriversDto response = driversService.create(drivers);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }




    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        Drivers response=driversService.findByNomDuChauffeur(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody DriversDto campany) throws NotFoundException {
        DriversDto response = driversService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        DriversDto response = driversService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<DriversDto> response = driversService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Drivers> response = driversService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        DriversDto response = driversService.findByCodeEmploye(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        driversService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        driversService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        driversService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Drivers> response = driversService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get all division findByCompanyName
    @GetMapping("getbydivision")
    public ResponseEntity<?> findbydivion() throws NotFoundException {
        List<Division> divisions = divisionService.findByCompanyName();
        List<String> divisionCompanyNames = new ArrayList<>();

        // Extract company names from the list of companies
        for (Division division : divisions) {
            divisionCompanyNames.add(division.getCompanyname());
        }
        return new ResponseEntity<>(divisionCompanyNames, HttpStatus.OK);
    }

}