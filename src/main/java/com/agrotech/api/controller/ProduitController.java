package com.agrotech.api.controller;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.model.Produit;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.CSVReaderException;
import com.agrotech.api.exceptions.EmptyFileException;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.ProduitService;
import com.agrotech.api.utils.CSVReader;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/produit")
@RequiredArgsConstructor
public class ProduitController {

	@Autowired
	private final ProduitService produitService;
	private  final ProduitRepository produitRepository;
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		produitRepository.deleteAll();
	}
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
	public ResponseEntity<?> create(@RequestBody ProduitDto produit) throws DocumentException, FileNotFoundException {
		ProduitDto response = produitService.create(produit);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("/import")
	public ResponseEntity<?> importCSV(@RequestPart("file") MultipartFile file) throws CSVReaderException, EmptyFileException {
		List<CSVRecord> read = CSVReader.read(file);
		produitService.importCSV(read);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable String id,
			@RequestBody ProduitDto produit
	) throws NotFoundException {
		ProduitDto response = produitService.update(id, produit);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		ProduitDto response = produitService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
	public ResponseEntity<?> findAll() {
		List<ProduitDto> response = produitService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Produit> response = produitService.findPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		ProduitDto response = produitService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
	public ResponseEntity<?> findByname(@PathVariable String name) throws NotFoundException{
		Produit response=produitService.findByname(name);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		produitService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping(value = "/csv-template")
	public ResponseEntity<?> downloadCSVTemplate() throws IOException {
		File file = ResourceUtils.getFile("classpath:csv/products.csv");
		byte[] resource = Files.readAllBytes(file.toPath());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("text/csv"))
				.body(resource);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		produitService.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		produitService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
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
