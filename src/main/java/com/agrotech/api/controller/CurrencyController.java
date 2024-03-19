package com.agrotech.api.controller;

import java.io.FileNotFoundException;
import java.util.List;

import com.agrotech.api.Repository.CurrencyRepository;
import com.agrotech.api.model.Currency;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrotech.api.dto.CurrencyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.CurrencyService;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

	@Autowired
	private final CurrencyService currencyService ;
	private  final CurrencyRepository currencyRepository;
	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		currencyRepository.deleteAll();
	}
	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
	public ResponseEntity<?> create(@RequestBody CurrencyDto div) throws DocumentException, FileNotFoundException {
		CurrencyDto response = currencyService.create(div);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
	public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException{
		Currency response=currencyService.findByname(name);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id,@RequestBody CurrencyDto div) throws NotFoundException {
		CurrencyDto response = currencyService.update(id, div);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		CurrencyDto response = currencyService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Currency> response = currencyService.findPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
	public ResponseEntity<?> findAll() {
		List<CurrencyDto> response = currencyService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		CurrencyDto response = currencyService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		currencyService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		currencyService.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		currencyService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Currency> response = currencyService.findArchivedPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
