package com.agrotech.api.controller;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.FacilityDetailsDto;
import com.agrotech.api.dto.FreightTermsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.FacilityDetails;
import com.agrotech.api.services.CampanyService;
import com.agrotech.api.services.FacilityDetailsService;
import com.agrotech.api.services.FreightTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/facilityDetails")
@RequiredArgsConstructor
public class FacilityDetailsController {

    private final FacilityDetailsService facilityDetailsService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody FacilityDetailsDto FacilityDetails) {
        System.out.println(FacilityDetails.toString());
        FacilityDetailsDto response = facilityDetailsService.create(FacilityDetails);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getbyname/{facilityName}")
    public ResponseEntity<?> findbyname(@PathVariable String facilityName) throws NotFoundException{
        FacilityDetails response=facilityDetailsService.findByFacilityName(facilityName);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody FacilityDetailsDto facilityDetails) throws NotFoundException {
        FacilityDetailsDto response = facilityDetailsService.update(id, facilityDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        FacilityDetailsDto response = facilityDetailsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<FacilityDetailsDto> response = facilityDetailsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<FacilityDetails> response = facilityDetailsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{facilityID}")
    public ResponseEntity<?> findByFacilityID(@PathVariable String facilityID) throws NotFoundException {
        FacilityDetailsDto response = facilityDetailsService.findByFacilityID(facilityID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        facilityDetailsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        facilityDetailsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        facilityDetailsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<FacilityDetails> response = facilityDetailsService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
