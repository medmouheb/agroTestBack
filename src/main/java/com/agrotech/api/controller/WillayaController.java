package com.agrotech.api.controller;

import com.agrotech.api.Repository.WillayaRepository;
import com.agrotech.api.dto.WillayaDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.Willaya;
import com.agrotech.api.services.WilayaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/willaya")
@RequiredArgsConstructor
public class WillayaController {

    @Autowired
    private final WilayaService willayaService ;

    private  final WillayaRepository willayaRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        willayaRepository.deleteAll();
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody WillayaDto div) {
        WillayaDto response = willayaService.create(div);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody WillayaDto div) throws NotFoundException {
        WillayaDto response = willayaService.update(id, div);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException{
        Willaya response=willayaService.findByname(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        WillayaDto response = willayaService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Willaya> response = willayaService.findPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<WillayaDto> response = willayaService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        WillayaDto response = willayaService.findByCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        willayaService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        willayaService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        willayaService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Willaya> response = willayaService.findArchivedPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
