package com.agrotech.api.controller;


import com.agrotech.api.Repository.TaskRepository;
import com.agrotech.api.dto.TaskDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Task;
import com.agrotech.api.services.TaskService;
import com.agrotech.api.services.impl.UserService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskRepository taskRepository;

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


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody TaskDto buyers) throws DocumentException, FileNotFoundException {
        TaskDto response = taskService.create(buyers);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody TaskDto campany) throws NotFoundException {
        TaskDto response = taskService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        TaskDto response = taskService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<TaskDto> response = taskService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("adminmain")
    public ResponseEntity<?> adminmain() {
        List<Task> response = taskService.findAllByDeletee(false);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("admintrash")
    public ResponseEntity<?> admintrash() {
        List<Task> response = taskService.findAllByDeletee(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        taskService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        taskService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        taskService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/owner")
    public ResponseEntity<?> findAllByOwnerDisactive(
            @RequestParam(defaultValue = "") String owner
    ) {
        List<Task> response = taskService.findAllByOwnerDisactive(owner);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/not-archived/owner")
    public ResponseEntity<?> findAllByOwnerActive(
            @RequestParam(defaultValue = "") String owner
    ) {
        List<Task> response = taskService.findAllByOwnerActive(owner);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/not-archived/actur")
    public ResponseEntity<?> findAllByOwnerAndActurActive(
            @RequestParam(defaultValue = "") String owner,
            @RequestParam(defaultValue = "") String actur
    ) {
        List<Task> response = taskService.findAllByOwnerAndActurActive(owner,actur);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/actur")
    public ResponseEntity<?> findAllByOwnerAndActurDisactive(
            @RequestParam(defaultValue = "") String owner,
            @RequestParam(defaultValue = "") String actur
    ) {
        List<Task> response = taskService.findAllByOwnerAndActurDisactive(owner,actur);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
