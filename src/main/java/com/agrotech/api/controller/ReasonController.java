package com.agrotech.api.controller;


import com.agrotech.api.dto.ReasonDTO;
import com.agrotech.api.model.Reason;
import com.agrotech.api.services.ReasonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)

@RequestMapping(value = "/reason")

public class ReasonController {


    @Autowired
    private ReasonService reasonService;
    @Autowired
    private ModelMapper modelMapper ;



    @PostMapping(value = "")
    public ResponseEntity<?> addReason(@RequestBody @Validated Reason reason) {
        if (reasonService.reasonCodeExists(reason.getReasonCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seaport code is already exist.");
        }
        ReasonDTO newReason =modelMapper.map(reasonService.ajouterReason(reason),ReasonDTO.class);
        return new ResponseEntity<>(newReason, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteReason(@PathVariable String id) {
        if (reasonService.reasonExists(id)) {
            reasonService.supprimerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateReason(@PathVariable String id, @RequestBody @Validated Reason reason) {
        if (!reasonService.reasonExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String updatedReasonCode = reason.getReasonCode();
        Reason existingReasonWithCode = reasonService.getReasonByReasonCode(updatedReasonCode);
        if (existingReasonWithCode != null && !existingReasonWithCode.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Reason code is already in use by another seaport.");
        }
        reason.setId(id);
        ReasonDTO updatedReason =modelMapper.map(reasonService.modifierReason(reason),ReasonDTO.class);

        return new ResponseEntity<>(updatedReason, HttpStatus.OK);
    }
    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateReason(@PathVariable String id) {
        if (!reasonService.reasonExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reason reason = reasonService.getReasonById(id);
        reason.setActive(false);
        reasonService.ajouterReason(reason);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("activate/{id}")
    public ResponseEntity<?> activateReason(@PathVariable String id) {
        if (!reasonService.reasonExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reason reason = reasonService.getReasonById(id);
        reason.setActive(true);
        reasonService.ajouterReason(reason);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "active")
    public ResponseEntity<List<ReasonDTO>> getActiveTrueReasons() {
        List<ReasonDTO> Reasons = reasonService.getActiveTrueReasons().stream().map(reason -> modelMapper.map(reason, ReasonDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(Reasons, HttpStatus.OK);
    }
    @GetMapping(value = "archived")
    public ResponseEntity<List<ReasonDTO>> getArchivedReasons() {
        List<ReasonDTO> reasons = reasonService.getArchivedReasons().stream().map(reason -> modelMapper.map(reason, ReasonDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(reasons, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getReasonById(@PathVariable String id) {
        if (reasonService.reasonExists(id)) {
            ReasonDTO reason = modelMapper.map(reasonService.getReasonById(id), ReasonDTO.class);
            return new ResponseEntity<>(reason, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchactive")
    public ResponseEntity<List<ReasonDTO>> searchReasonByNameAndActive(@RequestParam String reasonName) {
        List<ReasonDTO> reasons = reasonService.SearchReasonByNameAndActive(reasonName).stream().map(reason -> modelMapper.map(reason, ReasonDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(reasons, HttpStatus.OK);
    }
    @GetMapping(value = "/searcharchived")
    public ResponseEntity<List<ReasonDTO>> searchReasonByNameAndArchived(@RequestParam String reasonName) {
        List<ReasonDTO> reasons = reasonService.SearchReasonByNameAndArchived(reasonName).stream().map(reason -> modelMapper.map(reason, ReasonDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(reasons, HttpStatus.OK);
    }

}
