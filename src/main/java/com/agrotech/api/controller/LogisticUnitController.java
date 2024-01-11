package com.agrotech.api.controller;


import com.agrotech.api.Repository.LogisticUnitRepository;
import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Division;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.services.CampanyService;
import com.agrotech.api.services.DivisionService;
import com.agrotech.api.services.LogisticUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/logisticunit")
@RequiredArgsConstructor
public class LogisticUnitController {

    private final LogisticUnitService logisticUnitService;
    private final CampanyService campanyService;
    private  final DivisionService divisionService;
    private  final LogisticUnitRepository logisticUnitRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        logisticUnitRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody LogisticUnitDto logis) {
        LogisticUnitDto response = logisticUnitService.create(logis);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        LogisticUnit response=logisticUnitService.findByLogisticName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody LogisticUnitDto campany) throws NotFoundException {
        LogisticUnitDto response = logisticUnitService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        LogisticUnitDto response = logisticUnitService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<LogisticUnitDto> response = logisticUnitService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<LogisticUnit> response = logisticUnitService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        LogisticUnitDto response = logisticUnitService.findByLogisticCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        logisticUnitService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        logisticUnitService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        logisticUnitService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<LogisticUnit> response = logisticUnitService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getbycompany")
    public ResponseEntity<?> findbycompany() throws NotFoundException {
        List<Campany> companies = campanyService.findBynamee();
        List<String> companyNames = new ArrayList<>();

        // Extract company names from the list of companies
        for (Campany company : companies) {
            companyNames.add(company.getName());
        }

        return new ResponseEntity<>(companyNames, HttpStatus.OK);
    }


    @GetMapping("getbydivision")
    public ResponseEntity<?> findbydivion() throws NotFoundException {
        List<Division> divisions = divisionService.findBynamee();
        List<String> divisionNames = new ArrayList<>();

        // Extract company names from the list of companies
        for (Division division : divisions) {
            divisionNames.add(division.getName());
        }
        return new ResponseEntity<>(divisionNames, HttpStatus.OK);
    }
}
