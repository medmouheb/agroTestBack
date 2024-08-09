package com.agrotech.api.controller;


import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.Repository.StockRepository;
import com.agrotech.api.dto.StockDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Stock;
import com.agrotech.api.services.StockServices;
import com.agrotech.api.services.impl.NotificationService;
import com.agrotech.api.services.impl.UserService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//@CrossOrigin(origins = { "http://localhost:4200" }, maxAge = 3600)
@CrossOrigin(origins = { "*" }, maxAge = 3600)
@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController {
    @Autowired
    private UserService userService;
    private final StockServices stockServices;
    private final StockRepository stockRepository;
    private final ProduitRepository produitRepository;
    private  final BuyersRepository buyersRepository;
    private final EmailController emailController;

    private final NotificationService notificationService;

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


    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        stockRepository.deleteAll();
    }



    private static String getCurrentDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the date using a specific pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public StockDTO create(@RequestBody StockDTO campany) throws JSONException, DocumentException, FileNotFoundException {


        StockDTO createdCompany = stockServices.create(campany);
        notificationService.addNotification("Company created: " + createdCompany.getName());
        return createdCompany;


    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        Stock response = stockServices.findByname(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public StockDTO update(@PathVariable String id, @RequestBody StockDTO campany) throws NotFoundException {
        StockDTO updatedCompany = stockServices.update(id, campany);
        notificationService.addNotification("Stock updated: " + updatedCompany.getName());
        return updatedCompany;
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        StockDTO response = stockServices.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<StockDTO> response = stockServices.findAllByFarmer(getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {
        if(getRole().equals("admin")){
            Page<Stock> response = stockServices.getpages(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            if(getRole().equals("employee")){
                Page<Stock> response = stockServices.getpagesfarmer(userService.getFarmerByUsername(getusername()).get(),pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<Stock> response = stockServices.getpagesfarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {

        if(getRole().equals("admin")){
            Page<Stock> response = stockServices.getpagesarchive(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            if(getRole().equals("employee")){
                Page<Stock> response = stockServices.getpagesarchiveFarmer(userService.getFarmerByUsername(getusername()).get(),pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<Stock> response = stockServices.getpagesarchiveFarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        StockDTO response = stockServices.findByCode(code,getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        stockServices.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        stockServices.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        stockServices.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
