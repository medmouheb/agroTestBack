package com.agrotech.api.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


import com.agrotech.api.services.impl.NotificationService;
import com.agrotech.api.services.impl.UserService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 3600)
@RestController
@RequestMapping("/campany")
@RequiredArgsConstructor
public class CampanyController {

	@Autowired
	private UserService userService;
	private final CampanyService campanyService;
	private final NotificationService notificationService;
	private final CampanyRepository campanyRepository;

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		campanyRepository.deleteAll();
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@PostMapping("")
	public CampanyDto create(@RequestBody CampanyDto campany) throws DocumentException, FileNotFoundException {
		CampanyDto createdCompany = campanyService.create(campany);
		notificationService.addNotification("Company created: " + createdCompany.getName());
		return createdCompany;
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/getbyname/{name}")
	public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
		Campany response = campanyService.findByname(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@PutMapping("/{id}")
	public CampanyDto update(@PathVariable String id, @RequestBody CampanyDto campany) throws NotFoundException {
		CampanyDto updatedCompany = campanyService.update(id, campany);
		notificationService.addNotification("Company updated: " + updatedCompany.getName());
		return updatedCompany;
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		CampanyDto response = campanyService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("")
	public ResponseEntity<?> findAll() throws NotFoundException {

		if(getRole().equals("employee")){
			List<CampanyDto> response = campanyService.findAll(userService.getFarmerByUsername(getusername()).get());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			List<CampanyDto> response = campanyService.findAll(getusername());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<?> findPage(
			@RequestParam(defaultValue = "3") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String farmername,
			@RequestParam(defaultValue = "") String filter) {

		AtomicReference<String> t= new AtomicReference<>("admin");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();


		userDetails.getAuthorities().forEach(authority -> {
			if(authority.getAuthority().equals("ROLE_FARMER")){
				t.set("farmer");
			}else if(authority.getAuthority().equals("ROLE_ADMIN")){
				t.set("admin");
			}else {
				t.set("employee");
			}
		});
		Page<Campany> response ;
		if(t.get().equals("admin")){
			response= campanyService.getpages(pageSize, pageNumber, filter);
		}else {

			if(t.get().equals("employee")){
				response= campanyService.getpages1(pageSize, pageNumber, filter,farmername);
			}else {

			response= campanyService.getpages1(pageSize, pageNumber, filter,farmername);}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/archived/page")
	public ResponseEntity<?> findArchivedPage(
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "") String farmername,
			@RequestParam(defaultValue = "") String filter) {
		AtomicReference<String> t= new AtomicReference<>("admin");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		userDetails.getAuthorities().forEach(authority -> {
			if(authority.getAuthority().equals("ROLE_FARMER")){
				t.set("farmer");
			}else if(authority.getAuthority().equals("ROLE_ADMIN")){
				t.set("admin");
			}else {
				t.set("employee");
			}
		});
		Page<Campany> response = campanyService.getpagesarchive(pageSize, pageNumber, filter);
		if(t.get().equals("admin")){
			response= campanyService.getpagesarchive(pageSize, pageNumber, filter);
		}else {
			if(t.get().equals("employee")){
				response= campanyService.getpagesarchive1(pageSize, pageNumber, filter,farmername);
			}else{
			response= campanyService.getpagesarchive1(pageSize, pageNumber, filter,farmername);}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private String getusername(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return userDetails.getUsername();

	}
	private String getRole() {
		AtomicReference<String> role = new AtomicReference<>("employee"); // default to "employee"
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		userDetails.getAuthorities().forEach(authority -> {
			if (authority.getAuthority().equals("ROLE_FARMER")) {
				role.set("farmer");
			} else if (authority.getAuthority().equals("ROLE_ADMIN")) {
				role.set("admin");
			}
		});

		return role.get();
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		if(getRole().equals("employee")){
			CampanyDto response = campanyService.findByCode(code,userService.getFarmerByUsername(getusername()).get()  );
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			CampanyDto response = campanyService.findByCode(code,getusername());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		campanyService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/archiver/{id}")
	public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
		campanyService.archive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
	@GetMapping("/desarchiver/{id}")
	public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
		campanyService.setNotArchive(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}



}
