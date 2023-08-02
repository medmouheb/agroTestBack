package com.agrotech.api.controller;

import com.agrotech.api.dto.VehiculeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Vehicule;
import com.agrotech.api.services.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehicule")
@RequiredArgsConstructor
public class VehiculeController  {

    private final VehiculeService vehiculeService ;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VehiculeDto vehicule) {
        VehiculeDto response = vehiculeService.create(vehicule);

        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }


    @GetMapping("/getbyname/{name}")
    public ResponseEntity<Vehicule> findbyVehiculeName(@PathVariable String name) throws NotFoundException {
        Vehicule response = vehiculeService.findByVehiculeName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VehiculeDto vehicule) throws NotFoundException {
        VehiculeDto response = vehiculeService.update(id, vehicule);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VehiculeDto response = vehiculeService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VehiculeDto> response = vehiculeService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Vehicule> response = vehiculeService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<VehiculeDto> findByVehiculeCode(@PathVariable String vehiculeCode) throws NotFoundException {
        VehiculeDto response = vehiculeService.findByVehiculeCode(vehiculeCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vehiculeService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vehiculeService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vehiculeService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<Page<VehiculeDto>> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VehiculeDto> response = vehiculeService.findArchivedPage(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
