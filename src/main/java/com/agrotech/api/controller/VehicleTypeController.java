package com.agrotech.api.controller;


import com.agrotech.api.dto.VehicleTypeDto;
import com.agrotech.api.dto.VendorSKUDto;
import com.agrotech.api.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehicleType")
@RequiredArgsConstructor
public class VehicleTypeController {
    private final VehicleTypeService VehicleTypeService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VehicleTypeDto vehicleType) {
        System.out.println(vehicleType.toString());
        VehicleTypeDto response = VehicleTypeService.create(vehicleType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
