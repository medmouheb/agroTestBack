package com.agrotech.api.controller;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import com.agrotech.api.model.Produit;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.CSVReaderException;
import com.agrotech.api.exceptions.EmptyFileException;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.ProduitService;
import com.agrotech.api.utils.CSVReader;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produit")
@RequiredArgsConstructor
public class ProduitController {

	@Autowired
	private final ProduitService produitService;

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody ProduitDto produit) {
		ProduitDto response = produitService.create(produit);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/import")
	public ResponseEntity<?> importCSV(@RequestPart("file") MultipartFile file) throws CSVReaderException, EmptyFileException {
		List<CSVRecord> read = CSVReader.read(file);
		produitService.importCSV(read);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable String id,
			@RequestBody ProduitDto produit
	) throws NotFoundException {
		ProduitDto response = produitService.update(id, produit);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		ProduitDto response = produitService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<ProduitDto> response = produitService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Produit> response = produitService.findPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		ProduitDto response = produitService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		produitService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping(value = "/csv-template")
	public ResponseEntity<?> downloadCSVTemplate() throws IOException {
		File file = ResourceUtils.getFile("classpath:csv/products.csv");
		byte[] resource = Files.readAllBytes(file.toPath());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("text/csv"))
				.body(resource);
	}

	@GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		produitService.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		produitService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Produit> response = produitService.findArchivedPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
