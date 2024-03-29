package com.agrotech.api.controller;


import com.agrotech.api.Repository.AirportRepo;
import com.agrotech.api.dto.AirportDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Airport;
import com.agrotech.api.services.AirportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RequestMapping(value = "/airport")
public class AirportController {


    @Autowired
    private AirportService airportService;

    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private   AirportRepo airportRepo;

    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        airportRepo.deleteAll();
    }


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity<?> addAirport(@RequestBody @Validated Airport airport) {
        if (airportService.airportCodeExists(airport.getAirportCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Airport code is already exist.");
        }
        AirportDTO newAirport = modelMapper.map(airportService.ajouterAirport(airport), AirportDTO.class);
        return new ResponseEntity<>(newAirport, HttpStatus.CREATED);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteSeaport(@PathVariable String id) {
        if (airportService.airportExists(id)) {
            airportService.supprimerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateAirport(@PathVariable String id, @RequestBody @Validated Airport airport) {
        if (!airportService.airportExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Check if the airport code already exists for another airport (excluding the one being updated)
        String updatedAirportCode = airport.getAirportCode();
        Airport existingAirportWithCode = airportService.getAirportByAirportCode(updatedAirportCode);
        if (existingAirportWithCode != null && !existingAirportWithCode.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Airport code is already in use by another airport.");
        }

        airport.setId(id);
        AirportDTO updatedAirport = modelMapper.map(airportService.modifierAirport(airport), AirportDTO.class);
        return new ResponseEntity<>(updatedAirport, HttpStatus.OK);
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateAirport(@PathVariable String id) {
        if (!airportService.airportExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Airport airport = airportService.getAirportById(id);
        airport.setActive(false);
        airportService.ajouterAirport(airport);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PatchMapping("activate/{id}")
    public ResponseEntity<?> activateAirport(@PathVariable String id) {
        if (!airportService.airportExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Airport airport = airportService.getAirportById(id);
        airport.setActive(true);
        airportService.ajouterAirport(airport);
        return new ResponseEntity<>(HttpStatus.OK);
    }


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping(value = "active")
    public ResponseEntity<List<AirportDTO>> getActiveTrueAirports() {
        List<AirportDTO> airports = airportService.getActiveTrueAirports().stream().map(airport -> modelMapper.map(airport, AirportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping(value = "archived")
    public ResponseEntity<List<AirportDTO>> getArchivedAirports() {
        List<AirportDTO> airports = airportService.getArchivedAirports().stream().map(airport -> modelMapper.map(airport, AirportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping(value = "{id}")
    public ResponseEntity<AirportDTO> getAirportById(@PathVariable String id) {
        if (airportService.airportExists(id)) {
            AirportDTO airport = modelMapper.map(airportService.getAirportById(id), AirportDTO.class);
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping(value = "/searchactive")
    public ResponseEntity<List<AirportDTO>> searchAirportByNameAndActive(@RequestParam String airportName) {
        List<AirportDTO> airports = airportService.SearchAirportByNameAndActive(airportName).stream().map(airport -> modelMapper.map(airport, AirportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping(value = "/searcharchived")
    public ResponseEntity<List<AirportDTO>> searchAirportByNameAndArchived(@RequestParam String airportName) {
        List<AirportDTO> airports = airportService.SearchAirportByNameAndArchived(airportName).stream().map(airport -> modelMapper.map(airport, AirportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }



}
