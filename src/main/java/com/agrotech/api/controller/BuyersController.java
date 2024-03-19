package com.agrotech.api.controller;

import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.services.BuyersService;
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
@RequestMapping("/buyers")
@RequiredArgsConstructor
public class BuyersController {


    private final BuyersService buyersService;

    private  final BuyersRepository buyersRepository;
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        buyersRepository.deleteAll();
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody BuyersDto buyers) throws DocumentException, FileNotFoundException {
        BuyersDto response = buyersService.create(buyers);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        Buyers response=buyersService.findByBuyersName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody BuyersDto campany) throws NotFoundException {
        BuyersDto response = buyersService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        BuyersDto response = buyersService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BuyersDto> response = buyersService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Buyers> response = buyersService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        BuyersDto response = buyersService.findByBuyersCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        buyersService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        buyersService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        buyersService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Buyers> response = buyersService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
