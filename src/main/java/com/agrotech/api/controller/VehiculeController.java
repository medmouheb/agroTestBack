package com.agrotech.api.controller;

import com.agrotech.api.Repository.VehiculeRepository;
import com.agrotech.api.dto.VehiculeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Vehicule;
import com.agrotech.api.services.VehiculeService;
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

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vehicule")
@RequiredArgsConstructor
public class VehiculeController  {
    @Autowired
    private UserService userService;
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

    private final VehiculeService vehiculeService ;

    private  final VehiculeRepository vehiculeRepository;
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vehiculeRepository.deleteAll();
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VehiculeDto vehicule) throws DocumentException, FileNotFoundException {
        VehiculeDto response = vehiculeService.create(vehicule);

        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<Vehicule> findbyVehiculeName(@PathVariable String name) throws NotFoundException {
        Vehicule response = vehiculeService.findByVehiculeName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VehiculeDto vehicule) throws NotFoundException {
        VehiculeDto response = vehiculeService.update(id, vehicule);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VehiculeDto response = vehiculeService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VehiculeDto> response = vehiculeService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        if(getRole().equals("admin")){
            Page<Vehicule> response = vehiculeService.getpages(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {

            if(getRole().equals("employee")){
                Page<Vehicule> response = vehiculeService.getpagesFarmer(userService.getFarmerByUsername(getusername()).get(),pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<Vehicule> response = vehiculeService.getpagesFarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<VehiculeDto> findByVehiculeCode(@PathVariable String vehiculeCode) throws NotFoundException {
        VehiculeDto response = vehiculeService.findByVehiculeCode(vehiculeCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vehiculeService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vehiculeService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vehiculeService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<Page<VehiculeDto>> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VehiculeDto> response = vehiculeService.findArchivedPage(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
