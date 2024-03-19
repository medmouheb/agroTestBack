package com.agrotech.api.controller;

import java.io.FileNotFoundException;
import java.util.List;

import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.Repository.SalesRepository;
import com.agrotech.api.Repository.UserRepository;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.Sales;
import com.agrotech.api.model.Tax;
import com.agrotech.api.model.User;
import com.agrotech.api.services.BuyersService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrotech.api.dto.SalesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.SalesServices;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {

	@Autowired
	private final SalesServices salesServices ;
	private  final SalesRepository salesRepository;

	private final ProduitRepository produitRepository;

	private  final BuyersRepository buyersRepository;
	private final BuyersService buyersService;
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		salesRepository.deleteAll();
	}
//	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
	public ResponseEntity<?> create(@RequestBody SalesDto sales) throws DocumentException, FileNotFoundException {
		System.out.println( ":::"+ produitRepository.findById(sales.getProduct()).get().getTags()  );
		Buyers u=buyersRepository.findById(sales.getBuyer()).get();
		u.addTags(produitRepository.findById(sales.getProduct()).get().getTags()  );
		buyersRepository.save(u);
		for(Tax tax : sales.getTaxes()){
			System.out.println(tax.getId());
		}
		SalesDto response = salesServices.create(sales);
		return new ResponseEntity<>(response , HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
	public ResponseEntity<?> findByname(@PathVariable String name) throws NotFoundException{
		Sales response=salesServices.findByname(name);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("/add")
	public ResponseEntity<?> creates(@RequestBody Sales sales){
		Sales response = salesServices.saves(sales);
		return new ResponseEntity<>(response , HttpStatus.CREATED);

	}


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable String id,
			@RequestBody SalesDto sales
	) throws NotFoundException {
		SalesDto response = salesServices.update(id, sales);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		SalesDto response = salesServices.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
	public ResponseEntity<?> findAll() {
		List<SalesDto> response = salesServices.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter

	) {
		Page<Sales> response = salesServices.findPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		SalesDto response = salesServices.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		salesServices.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		salesServices.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		salesServices.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String filter
	) {
		Page<Sales> response = salesServices.findArchivedPage1(pageSize, pageNumber, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
