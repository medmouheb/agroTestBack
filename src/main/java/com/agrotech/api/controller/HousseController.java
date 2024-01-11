package com.agrotech.api.controller;

import java.util.List;

import com.agrotech.api.Repository.HousseRepository;
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

import com.agrotech.api.dto.HousseDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.HousseService;

import org.springframework.web.bind.annotation.CrossOrigin;


import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/housse")
@RequiredArgsConstructor
public class HousseController {

	@Autowired
	private HousseService housseService ;

	private  final HousseRepository housseRepository;
	@DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		housseRepository.deleteAll();
	}
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody HousseDto housse){
		HousseDto response = housseService.create(housse);
		return new ResponseEntity<>(response , HttpStatus.CREATED);

	}


	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable String id,
			@RequestBody HousseDto housse
	) throws NotFoundException {
		HousseDto response = housseService.update(id, housse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		HousseDto response = housseService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<HousseDto> response = housseService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber
	) {
		Page<HousseDto> response = housseService.findPage(pageSize, pageNumber, null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		HousseDto response = housseService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		housseService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
