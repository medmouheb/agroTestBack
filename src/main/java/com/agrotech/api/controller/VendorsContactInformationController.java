package com.agrotech.api.controller;

import com.agrotech.api.Repository.VehiculeRepository;
import com.agrotech.api.Repository.VendorsContactInformationRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VehiculeDto;
import com.agrotech.api.dto.VendorsContactInformationDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Vehicule;
import com.agrotech.api.model.VendorsContactInformation;
import com.agrotech.api.services.VendorsContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vendorsContactInformation")
@RequiredArgsConstructor
public class VendorsContactInformationController{



    private final VendorsContactInformationService vendorsContactInformationService ;


    private  final VendorsContactInformationRepository vendorsContactInformationRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorsContactInformationRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorsContactInformationDto vendorsContactInformation) {
        VendorsContactInformationDto response = vendorsContactInformationService.create(vendorsContactInformation);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException{
        VendorsContactInformation response=vendorsContactInformationService.findByLabelName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorsContactInformationDto vendorsContactInformation) throws NotFoundException {
        VendorsContactInformationDto response = vendorsContactInformationService.update(id, vendorsContactInformation);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorsContactInformationDto response = vendorsContactInformationService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorsContactInformationDto> response = vendorsContactInformationService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsContactInformation> response = vendorsContactInformationService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorsContactInformationDto response = vendorsContactInformationService.findByLabelCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorsContactInformationService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorsContactInformationService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorsContactInformationService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsContactInformation> response = vendorsContactInformationService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
