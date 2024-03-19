package com.agrotech.api.controller;


import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.Repository.PotentialClientRepository;
import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.dto.PotentialClientDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.PotentialClient;
import com.agrotech.api.services.BuyersService;
import com.agrotech.api.services.PotentialClientService;
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
@RequestMapping("/potential-client")
@RequiredArgsConstructor
public class PotentialClientController {


    private final PotentialClientService potentialClientService;

    private  final PotentialClientRepository potentialClientRepository;
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        potentialClientRepository.deleteAll();
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PotentialClientDto buyers) throws DocumentException, FileNotFoundException {

        PotentialClientDto response = potentialClientService.create(buyers);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        PotentialClient response=potentialClientService.findByBuyersName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody PotentialClientDto campany) throws NotFoundException {
        PotentialClientDto response = potentialClientService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        PotentialClientDto response = potentialClientService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<PotentialClientDto> response = potentialClientService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<PotentialClient> response = potentialClientService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        PotentialClientDto response = potentialClientService.findByBuyersCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        potentialClientService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        potentialClientService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        potentialClientService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<PotentialClient> response = potentialClientService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
