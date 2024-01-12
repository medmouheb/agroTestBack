package com.agrotech.api.controller;


import com.agrotech.api.dto.SeaportDTO;
import com.agrotech.api.model.Seaport;
import com.agrotech.api.services.SeaportService;
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

@RequestMapping(value = "/seaport")

public class SeaportController {



    @Autowired
    private SeaportService seaportService;

    @Autowired
    private ModelMapper modelMapper ;




    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity<?> addSeaport(@RequestBody @Validated Seaport seaport) {
        if (seaportService.seaportCodeExists(seaport.getSeaportCode())) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Seaport code is already exist.");
        }
        SeaportDTO newSeaport =modelMapper.map(seaportService.ajouterSeaport(seaport),SeaportDTO.class);
        return new ResponseEntity<>(newSeaport, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteSeaport(@PathVariable String id) {
        if (seaportService.seaportExists(id)) {
            seaportService.supprimerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateSeaport(@PathVariable String id, @RequestBody @Validated Seaport seaport) {
        if (!seaportService.seaportExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String updatedSeaportCode = seaport.getSeaportCode();
        Seaport existingSeaportWithCode = seaportService.getSeaportBySeaportCode(updatedSeaportCode);
        if (existingSeaportWithCode != null && !existingSeaportWithCode.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seaport code is already in use by another seaport.");
        }
        seaport.setId(id);
        SeaportDTO updatedSeaport =modelMapper.map(seaportService.modifierSeaport(seaport),SeaportDTO.class);
        return new ResponseEntity<>(updatedSeaport, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateSeaport(@PathVariable String id) {
        if (!seaportService.seaportExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Seaport seaport = seaportService.getSeaportById(id);
        seaport.setActive(false);
        seaportService.ajouterSeaport(seaport);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PatchMapping("activate/{id}")
    public ResponseEntity<?> activateSeaport(@PathVariable String id) {
        if (!seaportService.seaportExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Seaport seaport = seaportService.getSeaportById(id);
        seaport.setActive(true);
        seaportService.ajouterSeaport(seaport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "active")
    public ResponseEntity<List<SeaportDTO>> getActiveTrueSeaports() {
        List<SeaportDTO> seaports = seaportService.getActiveTrueSeaports().stream().map(seaport -> modelMapper.map(seaport, SeaportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(seaports, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "archived")
    public ResponseEntity<List<SeaportDTO>> getArchivedSeaports() {
        List<SeaportDTO> seaports = seaportService.getArchivedSeaports().stream().map(seaport -> modelMapper.map(seaport, SeaportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(seaports, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getSeaportById(@PathVariable String id) {
        if (seaportService.seaportExists(id)) {
            SeaportDTO seaport = modelMapper.map(seaportService.getSeaportById(id), SeaportDTO.class);
            return new ResponseEntity<>(seaport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "/searchactive")
    public ResponseEntity<List<SeaportDTO>> searchSeaportByNameAndActive(@RequestParam String seaPortName) {
        List<SeaportDTO> seaports = seaportService.SearchSeaportByNameAndActive(seaPortName).stream().map(seaport -> modelMapper.map(seaport, SeaportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(seaports, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "/searcharchived")
    public ResponseEntity<List<SeaportDTO>> searchSeaportByNameAndArchived(@RequestParam String seaPortName) {
        List<SeaportDTO> seaports = seaportService.SearchSeaportByNameAndArchived(seaPortName).stream().map(seaport -> modelMapper.map(seaport, SeaportDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(seaports, HttpStatus.OK);
    }


}
