package com.agrotech.api.controller;

import com.agrotech.api.Repository.BinDetailsRepository;
import com.agrotech.api.dto.BinDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.BinDetails;
import com.agrotech.api.services.BinDetailsService;
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
@RequestMapping("/binDetails")
@RequiredArgsConstructor
public class BinDetailsController {

    private final BinDetailsService binDetailsService;
    private final BinDetailsRepository binDetailsRepository;



	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        binDetailsRepository.deleteAll();
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody BinDetailsDto binDetails) throws DocumentException, FileNotFoundException {
        BinDetailsDto response = binDetailsService.create(binDetails);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{bin}")
    public ResponseEntity<?> findbyname(@PathVariable Number bin) throws NotFoundException {
        BinDetailsDto response=binDetailsService.findByBin(bin);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody BinDetailsDto binDetails) throws NotFoundException {
        BinDetailsDto response = binDetailsService.update(id, binDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) throws NotFoundException {
        BinDetailsDto response = binDetailsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BinDetailsDto> response = binDetailsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<BinDetails> response = binDetailsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{bin}")
    public ResponseEntity<?> findByCode(@PathVariable Number bin) throws NotFoundException {
        BinDetailsDto response = binDetailsService.findByBin(bin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        binDetailsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        binDetailsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        binDetailsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
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
