package com.agrotech.api.controller;


import com.agrotech.api.Repository.VehicleTypeRepository;
import com.agrotech.api.dto.VehicleTypeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VehicleType;
import com.agrotech.api.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vehicleType")
@RequiredArgsConstructor
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;

    private  final VehicleTypeRepository vehicleTypeRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vehicleTypeRepository.deleteAll();
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody VehicleTypeDto vehicleType) {
        System.out.println(vehicleType.toString());
        VehicleTypeDto response = vehicleTypeService.create(vehicleType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VehicleTypeDto vehicleType) throws NotFoundException {
        VehicleTypeDto response = vehicleTypeService.update(id, vehicleType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        List<VehicleTypeDto> response = vehicleTypeService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VehicleTypeDto response = vehicleTypeService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{vehicleTypeCode}")
    public ResponseEntity<?> findByVehicleTypeCode(@PathVariable String vehicleTypeCode) throws NotFoundException {
        VehicleTypeDto response = vehicleTypeService.findByVehicleTypeCode(vehicleTypeCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vehicleTypeService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vehicleTypeService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vehicleTypeService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VehicleType> response = vehicleTypeService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VehicleType> response = vehicleTypeService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
