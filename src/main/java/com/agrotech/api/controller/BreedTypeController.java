package com.agrotech.api.controller;

import com.agrotech.api.Repository.BreedTypeRepository;
import com.agrotech.api.dto.BreedTypeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.BreedType;
import com.agrotech.api.services.BreedTypeService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;


@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/breedType")
@RequiredArgsConstructor
public class BreedTypeController {

    private final BreedTypeService breedTypeService;
    private  final BreedTypeRepository breedTypeRepository;
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        breedTypeRepository.deleteAll();
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<BreedTypeDto> create(@RequestBody BreedTypeDto breedType) throws DocumentException, FileNotFoundException {
        System.out.println(breedType.toString());
        BreedTypeDto response = breedTypeService.create(breedType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody BreedTypeDto breedType) throws NotFoundException {
        BreedTypeDto response = breedTypeService.update(id, breedType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        BreedTypeDto response = breedTypeService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BreedTypeDto> response = breedTypeService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BreedType> response = breedTypeService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        BreedTypeDto response = breedTypeService.findByBreedTypeCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        breedTypeService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        breedTypeService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        breedTypeService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
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
