package com.agrotech.api.controller;

import com.agrotech.api.Repository.BreedCodeRepository;
import com.agrotech.api.Repository.BreedTypeRepository;
import com.agrotech.api.dto.BreedTypeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.BreedType;
import com.agrotech.api.services.BreedTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/breedType")
@RequiredArgsConstructor
public class BreedTypeController {

    private final BreedTypeService breedTypeService;
    private  final BreedTypeRepository breedTypeRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        breedTypeRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<BreedTypeDto> create(@RequestBody BreedTypeDto breedType) {
        System.out.println(breedType.toString());
        BreedTypeDto response = breedTypeService.create(breedType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody BreedTypeDto breedType) throws NotFoundException {
        BreedTypeDto response = breedTypeService.update(id, breedType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        BreedTypeDto response = breedTypeService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BreedTypeDto> response = breedTypeService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BreedType> response = breedTypeService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        BreedTypeDto response = breedTypeService.findByBreedTypeCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        breedTypeService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        breedTypeService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        breedTypeService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BreedType> response = breedTypeService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
