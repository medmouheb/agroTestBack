package com.agrotech.api.controller;


import com.agrotech.api.Repository.DeliveryNoteRepository;
import com.agrotech.api.Repository.DeliveryNoteRepository;
import com.agrotech.api.dto.DeliveryNoteDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.DeliveryNote;
import com.agrotech.api.model.DeliveryNote;
import com.agrotech.api.model.User;
import com.agrotech.api.services.DeliveryNoteService;
import com.agrotech.api.services.DeliveryNoteService;
import com.agrotech.api.services.impl.UserService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin(origins = { "http://localhost:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/deliveryNote")
@RequiredArgsConstructor
public class DeliveryNoteController {
    @Autowired
    private UserService userService;

    private final DeliveryNoteService deliveryNoteService;
    private final DeliveryNoteRepository deliveryNoteRepository;
    private String getusername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();

    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody DeliveryNoteDto campany) throws DocumentException, FileNotFoundException {
        DeliveryNoteDto response = deliveryNoteService.create(campany);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        deliveryNoteRepository.deleteAll();
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody DeliveryNoteDto campany) throws NotFoundException {
        DeliveryNoteDto response = deliveryNoteService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        DeliveryNoteDto response = deliveryNoteService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<DeliveryNoteDto> response = deliveryNoteService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
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


        Page<DeliveryNote> response;

        if(t.get().equals("admin")){
            response= deliveryNoteService.getpages1(pageSize, pageNumber, filter);
        }else {

            if(t.get().equals("employee")){
                response= deliveryNoteService.getpages(pageSize, pageNumber, filter,userService.getFarmerByUsername(farmername).get());
            }else {
            response= deliveryNoteService.getpages(pageSize, pageNumber, filter,farmername);}
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
        Page<User> users;
        // Replace with the actual method

        userDetails.getAuthorities().forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_FARMER")){
                t.set("farmer");
            }else if(authority.getAuthority().equals("ROLE_ADMIN")){
                t.set("admin");
            }else {
                t.set("employee");
            }
        });
        Page<DeliveryNote> response;

        if(t.get().equals("admin")){
            response= deliveryNoteService.getpagesarchive(pageSize, pageNumber, filter);
        }else {

            if(t.get().equals("employee")){
                response= deliveryNoteService.getpagesarchive1(pageSize, pageNumber, filter,userService.getFarmerByUsername(farmername).get());
            }else{
            response= deliveryNoteService.getpagesarchive1(pageSize, pageNumber, filter,farmername);}
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        DeliveryNoteDto response = deliveryNoteService.findByCode(code,getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        deliveryNoteService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        deliveryNoteService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        deliveryNoteService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
