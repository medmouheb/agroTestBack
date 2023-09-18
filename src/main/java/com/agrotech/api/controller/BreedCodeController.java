package com.agrotech.api.controller;

import com.agrotech.api.Repository.BreedCodeRepository;
import com.agrotech.api.dto.BreedCodeDto;
import com.agrotech.api.model.BreedCode;
import com.agrotech.api.services.BreedCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.agrotech.api.exceptions.NotFoundException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/breedCode")
@RequiredArgsConstructor
public class BreedCodeController {

    private final BreedCodeService breedCodeService;

    private  final BreedCodeRepository breedCodeRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        breedCodeRepository.deleteAll();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody BreedCodeDto breedCode) {
        BreedCodeDto response = breedCodeService.create(breedCode);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BreedCodeDto> response = breedCodeService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody BreedCodeDto breedCode) throws NotFoundException {
        BreedCodeDto response = breedCodeService.update(id, breedCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        BreedCodeDto response = breedCodeService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BreedCode> response = breedCodeService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        BreedCodeDto response = breedCodeService.findByBreedCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        breedCodeService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        breedCodeService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        breedCodeService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BreedCode> response = breedCodeService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
