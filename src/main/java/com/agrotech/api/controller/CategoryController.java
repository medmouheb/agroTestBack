package com.agrotech.api.controller;

import java.io.FileNotFoundException;
import java.util.List;

import com.agrotech.api.Repository.CategoryRepository;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotech.api.dto.CategoryDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.CategoryService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

	@Autowired
	private CategoryService categoryService ;
	private CategoryRepository categoryRepository;
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		categoryRepository.deleteAll();
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody CategoryDto cat) throws DocumentException, FileNotFoundException {
		CategoryDto response = categoryService.create(cat);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id,@RequestBody CategoryDto cat) throws NotFoundException {
		CategoryDto response = categoryService.update(id, cat);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		CategoryDto response = categoryService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<CategoryDto> response = categoryService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		CategoryDto response = categoryService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		categoryService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
