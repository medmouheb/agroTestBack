package com.agrotech.api.controller;


import com.agrotech.api.Repository.VehiclesRepository;
import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.dto.VehiclesDto;
import com.agrotech.api.dto.VehiculeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.Vehicles;
import com.agrotech.api.services.LogisticUnitService;
import com.agrotech.api.services.VehiclesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehiclesController {

    private final VehiclesService vehiclesService ;
    private final LogisticUnitService logisticUnitService;
    private final VehiclesRepository vehiclesRepository;

    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vehiclesRepository.deleteAll();
    }



    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VehiclesDto vehicle) {
        VehiclesDto response = vehiclesService.create(vehicle);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        Vehicles response=vehiclesService.findByNomDuVehicule(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /*@GetMapping("/getallListeDesOperationsEffectuees")
    public List<String> getallListeDesOperationsEffectuees() throws NotFoundException {
        return vehiclesRepository.findAllListeDesOperationsEffectuees();
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VehiclesDto campany) throws NotFoundException {
        VehiclesDto response = vehiclesService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VehiclesDto response = vehiclesService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VehiclesDto> response = vehiclesService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Vehicles> response = vehiclesService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VehiclesDto response = vehiclesService.findByCodeVehicle(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vehiclesService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vehiclesService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vehiclesService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Vehicles> response = vehiclesService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getbylogisticUnit")
    public ResponseEntity<?> findbylogisticUnit() throws NotFoundException {
        List<LogisticUnitDto> companies = logisticUnitService.findAll();

        List<String> companyNames = new ArrayList<>();

        // Extract company names from the list of companies
        for (LogisticUnitDto company : companies) {
            companyNames.add(company.getLogisticCode());
        }

        return new ResponseEntity<>(companyNames, HttpStatus.OK);
    }


}
