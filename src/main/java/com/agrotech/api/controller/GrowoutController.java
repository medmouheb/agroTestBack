package com.agrotech.api.controller;
import java.util.List;

import com.agrotech.api.Repository.Growoutrepository;
import com.agrotech.api.model.Growout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agrotech.api.dto.GrowoutDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.GrowoutService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/growout")
@RequiredArgsConstructor
public class GrowoutController {

	@Autowired
	private GrowoutService growoutService ;
	private  final Growoutrepository growoutrepository;
	@DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		growoutrepository.deleteAll();
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody GrowoutDto growout) {
		GrowoutDto response = growoutService.create(growout);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}



	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable String id,
			@RequestBody GrowoutDto growout
	) throws NotFoundException {
		GrowoutDto response = growoutService.update(id, growout);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		GrowoutDto response = growoutService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<GrowoutDto> response = growoutService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<?> findPage1(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Growout> response = growoutService.findPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		GrowoutDto response = growoutService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		growoutService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        growoutService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		growoutService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Growout> response = growoutService.findArchivedPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
