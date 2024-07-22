package com.agrotech.api.controller;

import com.agrotech.api.Repository.BuyRepository;
import com.agrotech.api.dto.BuyDto;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Buy;
import com.agrotech.api.model.Campany;
import com.agrotech.api.services.BuyService;
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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin(origins = { "http://localhost:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/buy")
@RequiredArgsConstructor


public class BuyController {
    @Autowired
    private UserService userService;
    private final BuyService buyService;
    private final BuyRepository buyRepository;

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

    private String getusername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();

    }




    //    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        buyRepository.deleteAll();
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody BuyDto campany) throws DocumentException, FileNotFoundException {
        BuyDto response = buyService.create(campany);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbycode/{code}")
    public ResponseEntity<?> findbycode(@PathVariable String code) throws NotFoundException {
        BuyDto response = buyService.findByCode(code,getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody BuyDto campany) throws NotFoundException {
        BuyDto response = buyService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        BuyDto response = buyService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<BuyDto> response = buyService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {
        if(getRole().equals("admin")){
            Page<Buy> response = buyService.getpages(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            if(getRole().equals("employee")){
                Page<Buy> response = buyService.getpagesFarmer(userService.getFarmerByUsername(getusername()).get()   ,pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<Buy> response = buyService.getpagesFarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        BuyDto response = buyService.findByCode(code,getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        buyService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        buyService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        buyService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {
        Page<Buy> response = buyService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/{username}/farmer")
    public ResponseEntity<String> getFarmerByUsername(@PathVariable String username) {
        Optional<String> farmer = userService.getFarmerByUsername(username);
        return farmer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
