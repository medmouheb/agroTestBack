package com.agrotech.api.controller;


import com.agrotech.api.Repository.CropRepo;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.CropDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Crop;
import com.agrotech.api.model.ERole;
import com.agrotech.api.model.User;
import com.agrotech.api.services.CropService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/crop")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;
    private final CropRepo cropRepo;


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CropDTO campany) throws DocumentException, FileNotFoundException {
        CropDTO response = cropService.create(campany);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        cropRepo.deleteAll();
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        Crop response = cropService.findByname(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CropDTO campany) throws NotFoundException {
        CropDTO response = cropService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        CropDTO response = cropService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<CropDTO> response = cropService.findAll();
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


        Page<Crop> response;

        if(t.get().equals("admin")){
            response= cropService.getpages1(pageSize, pageNumber, filter);
        }else {
            response= cropService.getpages(pageSize, pageNumber, filter,farmername);
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
        Page<Crop> response;

        if(t.get().equals("admin")){
            response= cropService.getpagesarchive(pageSize, pageNumber, filter);
        }else {
            response= cropService.getpagesarchive1(pageSize, pageNumber, filter,farmername);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        CropDTO response = cropService.findByCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        cropService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        cropService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        cropService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
