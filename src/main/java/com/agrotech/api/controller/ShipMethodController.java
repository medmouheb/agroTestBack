package com.agrotech.api.controller;

import com.agrotech.api.Repository.ShipMethodsRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.ShipMethodsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.ShipMethods;
import com.agrotech.api.services.ShipMethodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/shipmethods")
@RequiredArgsConstructor
public class ShipMethodController {

    private final ShipMethodsService shipMethodsService;

    private  final ShipMethodsRepository shipMethodsRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        shipMethodsRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ShipMethodsDto campany) {
        ShipMethodsDto response = shipMethodsService.create(campany);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        ShipMethods response=shipMethodsService.findByname(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody ShipMethodsDto campany) throws NotFoundException {
        ShipMethodsDto response = shipMethodsService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        ShipMethodsDto response = shipMethodsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<ShipMethodsDto> response = shipMethodsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<ShipMethods> response = shipMethodsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        ShipMethodsDto response = shipMethodsService.findByCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        shipMethodsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        shipMethodsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        shipMethodsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<ShipMethods> response = shipMethodsService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
