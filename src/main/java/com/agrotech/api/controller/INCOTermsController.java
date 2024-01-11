package com.agrotech.api.controller;

import com.agrotech.api.Repository.INCOTermsRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.INCOTermsDto;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.INCOTerms;
import com.agrotech.api.services.INCOTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/Incoterms")
@RequiredArgsConstructor
public class INCOTermsController {


    private final INCOTermsService INCOTermsService;
    private  final INCOTermsRepository incoTermsRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        incoTermsRepository.deleteAll();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody INCOTermsDto iNCOTerms) {
        INCOTermsDto response = INCOTermsService.create(iNCOTerms);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        INCOTerms response=INCOTermsService.findByINCOTermName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody INCOTermsDto incoTerms) throws NotFoundException {
        INCOTermsDto response = INCOTermsService.update(id, incoTerms);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        INCOTermsDto response = INCOTermsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<INCOTermsDto> response = INCOTermsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<INCOTerms> response = INCOTermsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        INCOTermsDto response = INCOTermsService.findByINCOTermCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        INCOTermsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        INCOTermsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        INCOTermsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<INCOTerms> response = INCOTermsService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
