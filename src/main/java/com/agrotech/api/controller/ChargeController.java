package com.agrotech.api.controller;


import com.agrotech.api.dto.ChargeDto;

import com.agrotech.api.model.Charge;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.agrotech.api.exceptions.CSVReaderException;
import com.agrotech.api.exceptions.EmptyFileException;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.ChargeService;
import com.agrotech.api.utils.CSVReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


@RestController
@RequestMapping("/charge")
@RequiredArgsConstructor
public class ChargeController {


    @Autowired
    private final  ChargeService chargeService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid ChargeDto charge) {
        ChargeDto response = chargeService.create(charge);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importCSV(@RequestPart("file") MultipartFile file) throws CSVReaderException, EmptyFileException {
        List<CSVRecord> read = CSVReader.read(file);
        chargeService.importCSV(read);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody ChargeDto commande
    ) throws NotFoundException {
        ChargeDto response = chargeService.update(id, commande);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) throws NotFoundException {
        ChargeDto response = chargeService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<ChargeDto> response = chargeService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage1(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Charge> response = chargeService.findPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        chargeService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @GetMapping(value = "/csv-template")
    public ResponseEntity<?> downloadCSVTemplate() throws IOException {
        File file = ResourceUtils.getFile("classpath:csv/providers.csv");
        byte[] resource = Files.readAllBytes(file.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        chargeService.archive(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        chargeService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Charge> response = chargeService.findArchivedPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

