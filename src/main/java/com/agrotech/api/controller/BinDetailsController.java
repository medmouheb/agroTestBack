package com.agrotech.api.controller;

import com.agrotech.api.Repository.BinDetailsRepository;
import com.agrotech.api.dto.BinDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.BinDetails;
import com.agrotech.api.services.BinDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/binDetails")
@RequiredArgsConstructor
public class BinDetailsController {

    private final BinDetailsService binDetailsService;
    private final BinDetailsRepository binDetailsRepository;



    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        binDetailsRepository.deleteAll();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody BinDetailsDto binDetails) {
        BinDetailsDto response = binDetailsService.create(binDetails);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/getbyname/{bin}")
    public ResponseEntity<?> findbyname(@PathVariable Number bin) throws NotFoundException {
        BinDetailsDto response=binDetailsService.findByBin(bin);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody BinDetailsDto binDetails) throws NotFoundException {
        BinDetailsDto response = binDetailsService.update(id, binDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) throws NotFoundException {
        BinDetailsDto response = binDetailsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BinDetailsDto> response = binDetailsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BinDetails> response = binDetailsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{bin}")
    public ResponseEntity<?> findByCode(@PathVariable Number bin) throws NotFoundException {
        BinDetailsDto response = binDetailsService.findByBin(bin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        binDetailsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        binDetailsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        binDetailsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BinDetails> response = binDetailsService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
