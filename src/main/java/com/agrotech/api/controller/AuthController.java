package com.agrotech.api.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.agrotech.api.Repository.RoleRepository;
import com.agrotech.api.Repository.UserRepository;
import com.agrotech.api.model.ERole;
import com.agrotech.api.model.Role;
import com.agrotech.api.model.User;
import com.agrotech.api.payload.request.LoginRequest;
import com.agrotech.api.payload.request.SignupRequest;
import com.agrotech.api.payload.response.JwtResponse;
import com.agrotech.api.payload.response.MessageResponse;
import com.agrotech.api.security.jwt.JwtUtils;
import com.agrotech.api.security.services.UserDetailsImpl;
import jakarta.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.agrotech.api.exceptions.NotFoundException;
import java.io.IOException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFarmer(),
                userDetails.getModules(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getNom(),
                signUpRequest.getPrenom(),
                signUpRequest.getDateNaissance(),
                signUpRequest.getSexe(),
                signUpRequest.getPays(),
                signUpRequest.getRegion(),
                signUpRequest.getNumeroTelephone());



        Set<String> strRoles = signUpRequest.getRoles();

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_FARMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    case "farmer":
                        Role farmerRole = roleRepository.findByName(ERole.ROLE_FARMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(farmerRole);
                        user.setVerified(false);



                        break;
                    case "employee":
                        Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(employeeRole);
                        user.setFarmer(signUpRequest.getFarmer());
                        user.setModules(signUpRequest.getModules());
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_FARMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.setTags(signUpRequest.getTags());
        user.setModules(signUpRequest.getModules());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }





    @PutMapping("/verif-farmer/{farmer}")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<?> verifFarmer(@PathVariable String farmer) throws NotFoundException{
        User user= userRepository.findByUsername(farmer).get();
        user.setVerified(true);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("farmer verified succefully"));
    }

    @DeleteMapping("/delete-employee/{employee}")
    @PreAuthorize("hasRole('FARMER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteFarmer(@PathVariable String employee) throws NotFoundException{
        User user= userRepository.findByUsername(employee).get();
        userRepository.delete(user);
        return ResponseEntity.ok(new MessageResponse("employee deleted succefully"));
    }


    @GetMapping("/getFarmers")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<?> getFarmers(@RequestParam(defaultValue = "3") int pageSize,
                                        @RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = "") String filter) throws NotFoundException{
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<User> farmers= userRepository.findByRolesContainingAndUsernameContainingIgnoreCase(roleRepository.findByName(ERole.ROLE_FARMER).get(),filter,pageable);

        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @GetMapping("/getemployees")
    @PreAuthorize("hasRole('FARMER') or hasRole('ADMIN')")
    public ResponseEntity<?> getEmployees(@RequestParam(defaultValue = "3") int pageSize,
                                          @RequestParam(defaultValue = "0") int pageNumber,
                                          @RequestParam(defaultValue = "") String filter) throws NotFoundException{
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        AtomicReference<String> t= new AtomicReference<>("admin");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Page<User> users;
         // Replace with the actual method

        userDetails.getAuthorities().forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_FARMER")){
                t.set("farmer");
            }else if(authority.getAuthority().equals("ROLE_ADMIN")){
                t.set("admin");            }
        });
        if(t.get().equals("farmer")){
            String userId = userDetails.getUsername();
            users = userRepository.findByFarmerAndUsernameContainingIgnoreCase(userId,filter,pageable);
        }else {
            users= userRepository.findByRolesContainingAndUsernameContainingIgnoreCase(roleRepository.findByName(ERole.ROLE_EMPLOYEE).get(),filter,pageable);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update-access")
    @PreAuthorize("hasRole('FARMER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateAccess( @RequestBody String objectNew) throws NotFoundException, JSONException {
        System.out.println(objectNew);

        JSONObject DataJSON=new JSONObject(objectNew);
        User user= userRepository.findByUsername(DataJSON.getString("username")).get();
        HashSet<String> modules = new HashSet<>();
        JSONArray products = DataJSON.getJSONArray("modules");
        for (int i = 0; i <products.length(); i++) {
            modules.add(products.getString(i));
        }
        user.setModules(modules);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("employee was updated succefully"));
    }
}



