package com.agrotech.api.controller;

import java.util.List;

import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrotech.api.dto.CampanyDto;

import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.CampanyService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/campany")
@RequiredArgsConstructor
public class CampanyController {
	private final CampanyService campanyService ;
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody CampanyDto campany) {
		CampanyDto response = campanyService.create(campany);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
@GetMapping("/getbyname/{name}")
public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException{
		Campany response=campanyService.findByname(name);
		return new ResponseEntity<>(response,HttpStatus.OK);
}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id,@RequestBody CampanyDto campany) throws NotFoundException {
		CampanyDto response = campanyService.update(id, campany);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		CampanyDto response = campanyService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<CampanyDto> response = campanyService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "3") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Campany> response = campanyService.getpages(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		CampanyDto response = campanyService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		campanyService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		campanyService.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		campanyService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Campany> response = campanyService.getpagesarchive(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
