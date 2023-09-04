package com.agrotech.api.controller;

import java.util.List;

import com.agrotech.api.model.Ferme;
import org.apache.commons.csv.CSVRecord;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agrotech.api.dto.DivisionDTO;
import com.agrotech.api.dto.FermeDto;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.CSVReaderException;
import com.agrotech.api.exceptions.EmptyFileException;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.FermeService;
import com.agrotech.api.services.FournisseurService;
import com.agrotech.api.utils.CSVReader;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ferme")
@RequiredArgsConstructor
public class fermeController {

	@Autowired
	private final FermeService fermeService ; 
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody FermeDto ferme) {
		FermeDto response = fermeService.create(ferme);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	

	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable String id,
			@RequestBody FermeDto ferme
	) throws NotFoundException {
		FermeDto response = fermeService.update(id, ferme);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		FermeDto response = fermeService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<FermeDto> response = fermeService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Ferme> response = fermeService.findPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		FermeDto response = fermeService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		fermeService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		fermeService.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		fermeService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
		@RequestParam(defaultValue = "") String filter
) {
		Page<Ferme> response = fermeService.findArchivedPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
