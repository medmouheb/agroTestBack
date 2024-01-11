package com.agrotech.api.controller;


import com.agrotech.api.dto.ManufacturerDTO;
import com.agrotech.api.model.Manufacturer;
import com.agrotech.api.services.ManufacturerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/manufacturer")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;


    @Autowired
    private ModelMapper modelMapper ;

    @PostMapping(value = "")
    public ResponseEntity<?> addManufacturer(@RequestBody @Validated Manufacturer manufacturer) {
        if (manufacturerService.manufacturerExists(manufacturer.getManufacturerCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Manufacturer code is already exist.");
        }
        ManufacturerDTO newManufacturer = modelMapper.map(manufacturerService.ajouterManufacturer(manufacturer), ManufacturerDTO.class);
        return new ResponseEntity<>(newManufacturer, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable String id) {
        if (manufacturerService.manufacturerExists(id)) {
            manufacturerService.supprimerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateManufacturer(@PathVariable String id, @RequestBody @Validated Manufacturer manufacturer) {
        if (!manufacturerService.manufacturerExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String updatedManufacturerCode = manufacturer.getManufacturerCode();
        Manufacturer existingManufacturerWithCode = manufacturerService.getManufacturerByManufacturerCode(updatedManufacturerCode);
        if (existingManufacturerWithCode != null && !existingManufacturerWithCode.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Manufacturer code is already in use by another manufacturer.");
        }

        manufacturer.setId(id);
        ManufacturerDTO updatedManufacturer = modelMapper.map(manufacturerService.modifierManufacturer(manufacturer), ManufacturerDTO.class);
        return new ResponseEntity<>(updatedManufacturer, HttpStatus.OK);
    }
    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateManufacturer(@PathVariable String id) {
        if (!manufacturerService.manufacturerExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Manufacturer airport = manufacturerService.getManufacturerById(id);
        airport.setActive(false);
        manufacturerService.ajouterManufacturer(airport);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("activate/{id}")
    public ResponseEntity<?> activateManufacturer(@PathVariable String id) {
        if (!manufacturerService.manufacturerExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        manufacturer.setActive(true);
        manufacturerService.ajouterManufacturer(manufacturer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "active")
    public ResponseEntity<List<ManufacturerDTO>> getActiveTrueManufacturer() {
        List<ManufacturerDTO> manufacturers = manufacturerService.getActiveTrueManufacturers().stream().map(manufacturer -> modelMapper.map(manufacturer, ManufacturerDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }
    @GetMapping(value = "archived")
    public ResponseEntity<List<ManufacturerDTO>> getArchivedManufacturer() {
        List<ManufacturerDTO> manufacturers = manufacturerService.getArchivedManufacturers().stream().map(manufacturer -> modelMapper.map(manufacturer, ManufacturerDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(@PathVariable String id) {
        if (manufacturerService.manufacturerExists(id)) {
            ManufacturerDTO manufacturer = modelMapper.map(manufacturerService.getManufacturerById(id), ManufacturerDTO.class);
            return new ResponseEntity<>(manufacturer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/searchactive")
    public ResponseEntity<List<ManufacturerDTO>> searchManufacturerByNameAndActive(@RequestParam String manufacturerName) {
        List<ManufacturerDTO> manufacturers = manufacturerService.SearchManufacturerByNameAndActive(manufacturerName).stream().map(manufacturer -> modelMapper.map(manufacturer, ManufacturerDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @GetMapping(value = "/searcharchived")
    public ResponseEntity<List<ManufacturerDTO>> searchManufacturerByNameAndArchived(@RequestParam String manufacturerName) {
        List<ManufacturerDTO> manufacturers = manufacturerService.SearchManufacturerByNameAndArchived(manufacturerName).stream().map(manufacturer -> modelMapper.map(manufacturer, ManufacturerDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

}
