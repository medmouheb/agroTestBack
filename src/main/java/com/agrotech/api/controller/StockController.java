package com.agrotech.api.controller;


import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.Repository.StockRepository;
import com.agrotech.api.Repository.TaxRepository;
import com.agrotech.api.dto.StockDTO;
import com.agrotech.api.dto.TaxDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.Stock;
import com.agrotech.api.model.Tax;
import com.agrotech.api.services.StockServices;
import com.agrotech.api.services.TaxService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@CrossOrigin(origins = { "http://localhost:4200" }, maxAge = 3600)
@CrossOrigin(origins = { "*" }, maxAge = 3600)
@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController {

    private final StockServices stockServices;
    private final StockRepository stockRepository;
    private final ProduitRepository produitRepository;
    private  final BuyersRepository buyersRepository;
    private final EmailController emailController;


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
    public ResponseEntity<?> create(@RequestBody StockDTO campany) throws JSONException, DocumentException, FileNotFoundException {
        StockDTO response = stockServices.create(campany);
        System.out.println( ":::"+ produitRepository.findById(campany.getProduct()).get().getTags()  );

        List<Buyers> l=buyersRepository.findByTagsIn(produitRepository.findById(campany.getProduct()).get().getTags());


        for(Buyers singleBuyer:l){
            String dto="{\n" +
                    "      \"email\":\""+singleBuyer.getEmail()+"\",\n" +
                    "      \"title\":\"NEW PRODUCTS\",\n" +
                    "      \"subject\":\" "+getCurrentDate()+" NEW PRODUCTS\",\n" +
                    "      \"paragraph\":\"we have new products you can be interested in ,\n" +
                    produitRepository.findById(campany.getProduct()).get().getName()+"\","+
                    "      \"files\":[]\n" +
                    "  }";
            emailController.sendActivateMAil(dto);
        }

        return new ResponseEntity<>(l, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        Stock response = stockServices.findByname(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody StockDTO campany) throws NotFoundException {
        StockDTO response = stockServices.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        StockDTO response = stockServices.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<StockDTO> response = stockServices.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {
        Page<Stock> response = stockServices.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        StockDTO response = stockServices.findByCode(code);
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

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {
        Page<Stock> response = stockServices.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
