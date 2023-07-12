package com.agrotech.api.controller;

import java.util.List;

import com.agrotech.api.model.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.agrotech.api.dto.CostCenterDto;
import com.agrotech.api.dto.DivisionDTO;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.DivisionService;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/division")
@RequiredArgsConstructor
public class DivisionController {
	
	@Autowired
	private final DivisionService divisionService ;
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody DivisionDTO div) {
		DivisionDTO response = divisionService.create(div);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id,@RequestBody DivisionDTO div) throws NotFoundException {
		DivisionDTO response = divisionService.update(id, div);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		DivisionDTO response = divisionService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<DivisionDTO> response = divisionService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Division> response = divisionService.findPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		DivisionDTO response = divisionService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		divisionService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		divisionService.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		divisionService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Division> response = divisionService.findArchivedPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
