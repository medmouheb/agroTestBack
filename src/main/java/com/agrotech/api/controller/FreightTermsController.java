package com.agrotech.api.controller;

import com.agrotech.api.Repository.FreighTermsRepository;
import com.agrotech.api.dto.FreightTermsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.FreightTerms;
import com.agrotech.api.services.FreightTermsService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/freightTerms")
@RequiredArgsConstructor
public class FreightTermsController {
      private final FreightTermsService freightTermsService ;

    private  final FreighTermsRepository freighTermsRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        freighTermsRepository.deleteAll();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid FreightTermsDto freightTerms) {
        System.out.println("tad");
        System.out.println(freightTerms.toString());
        FreightTermsDto response = freightTermsService.create(freightTerms);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/getbyfreighttermname/{freighttermname}")
    public ResponseEntity<?> findbyfreighttermname (@PathVariable String freighttermname ) throws NotFoundException {
        FreightTerms response=freightTermsService.findByfreighttermname(freighttermname );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody FreightTermsDto freightTerms) throws NotFoundException {
        FreightTermsDto response = freightTermsService.update(id, freightTerms);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        FreightTermsDto response = freightTermsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<FreightTermsDto> response = freightTermsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<FreightTerms> response = freightTermsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-freighttermcode/{freighttermcode}")
    public ResponseEntity<?> findByfreighttermcode (@PathVariable String freighttermcode ) throws NotFoundException {
        FreightTermsDto response = freightTermsService.findByfreighttermcode(freighttermcode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        freightTermsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        freightTermsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        freightTermsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<FreightTerms> response = freightTermsService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
