package com.agrotech.api.controller;

import com.agrotech.api.Repository.InventaireInitialRepository;
import com.agrotech.api.dto.InventaireInitialDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.InventaireInitial;
import com.agrotech.api.model.Produit;
import com.agrotech.api.services.InventaireInitialService;
import com.agrotech.api.services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/InventaireInitial")
@RequiredArgsConstructor
public class InventaireInitialController {

    private final InventaireInitialService inventaireInitialService;

    private final ProduitService produitService;
    private final InventaireInitialRepository inventaireInitialRepository;


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody InventaireInitialDto logis) {
        InventaireInitialDto response = inventaireInitialService.create(logis);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        InventaireInitial response=inventaireInitialService.findByNomDuProduit(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody InventaireInitialDto campany) throws NotFoundException {
        InventaireInitialDto response = inventaireInitialService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        InventaireInitialDto response = inventaireInitialService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<InventaireInitialDto> response = inventaireInitialService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<InventaireInitial> response = inventaireInitialService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        InventaireInitialDto response = inventaireInitialService.findByCodeProduit(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        inventaireInitialService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        inventaireInitialRepository.deleteAll();
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        inventaireInitialService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        inventaireInitialService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<InventaireInitial> response = inventaireInitialService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getbyProduitName/{type}")
    public ResponseEntity<?> findByProduitName(@PathVariable String type) throws NotFoundException {
        //get all produit type
        List<Produit> produits=produitService.findAllByType(type);
        //get all code produit
        List<String> codes=produits.stream().map(Produit::getCode).toList();

        return new ResponseEntity<>(codes,HttpStatus.OK);


    }



}
