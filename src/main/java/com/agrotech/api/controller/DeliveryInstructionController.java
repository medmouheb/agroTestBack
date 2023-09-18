package com.agrotech.api.controller;

import com.agrotech.api.Repository.CurrencyRepository;
import com.agrotech.api.Repository.DeliveryInstructionRepository;
import com.agrotech.api.dto.CurrencyDto;
import com.agrotech.api.dto.DeliveryInstructionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.DeliveryInstruction;
import com.agrotech.api.services.CurrencyService;
import com.agrotech.api.services.DeliveryInstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryInstructionController {
    @Autowired
    private final DeliveryInstructionService deliveryInstructionService;
    private  final DeliveryInstructionRepository deliveryInstructionRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        deliveryInstructionRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody DeliveryInstructionDto div) {
        DeliveryInstructionDto response = deliveryInstructionService.create(div);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/getbytype/{name}")
    public ResponseEntity<?> getBytype(@PathVariable String name) throws NotFoundException {
        DeliveryInstructionDto response=deliveryInstructionService.findBytypeproduct(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody DeliveryInstructionDto div) throws NotFoundException {
        DeliveryInstructionDto response = deliveryInstructionService.update(id, div);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        DeliveryInstructionDto response = deliveryInstructionService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<DeliveryInstructionDto> response = deliveryInstructionService.findPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<DeliveryInstructionDto> response = deliveryInstructionService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/by-code/{code}")
//    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
//        CurrencyDto response = deliveryInstructionService.findBytypeproduct(code);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        deliveryInstructionService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        deliveryInstructionService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        deliveryInstructionService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<DeliveryInstructionDto> response = deliveryInstructionService.findArchivedPage1(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
