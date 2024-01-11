package com.agrotech.api.controller;


import com.agrotech.api.Repository.RapprochementDesStocksRepository;
import com.agrotech.api.dto.RapprochementDesStocksDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.RapprochementDesStocks;
import com.agrotech.api.services.RapprochementDesStocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/rapprochement-des-stocks")
@RequiredArgsConstructor
public class RapprochementDesStocksController {

    @Autowired
    private final RapprochementDesStocksService rapprochementDesStocksService;
    private  final RapprochementDesStocksRepository rapprochementDesStocksRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        rapprochementDesStocksRepository.deleteAll();
    }
    @GetMapping("/numlot/{code}")
    public ResponseEntity<?> findBynumerlot(@PathVariable String code) throws NotFoundException {
        RapprochementDesStocksDto response = rapprochementDesStocksService.findbynumeroDeLot(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody RapprochementDesStocksDto rapprochementDesStocks) {
        RapprochementDesStocksDto response = rapprochementDesStocksService.create(rapprochementDesStocks);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        RapprochementDesStocks response=rapprochementDesStocksService.findByNomDuProduits(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody RapprochementDesStocksDto campany) throws NotFoundException {
        RapprochementDesStocksDto response = rapprochementDesStocksService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        RapprochementDesStocksDto response = rapprochementDesStocksService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<RapprochementDesStocksDto> response = rapprochementDesStocksService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<RapprochementDesStocks> response = rapprochementDesStocksService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        RapprochementDesStocksDto response = rapprochementDesStocksService.findBynDeReference(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        rapprochementDesStocksService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        rapprochementDesStocksService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        rapprochementDesStocksService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<RapprochementDesStocks> response = rapprochementDesStocksService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
