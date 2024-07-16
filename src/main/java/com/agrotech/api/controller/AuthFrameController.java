package com.agrotech.api.controller;

import com.agrotech.api.Repository.RoleRepository;
import com.agrotech.api.Repository.UserRepository;
import com.agrotech.api.model.ERole;
import com.agrotech.api.model.Role;
import com.agrotech.api.model.User;
import com.agrotech.api.payload.request.SignupRequest;
import com.agrotech.api.payload.response.MessageResponse;
import com.agrotech.api.utils.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;
@CrossOrigin(origins = { "*" }, maxAge = 3600)

@RestController
@RequestMapping("/api/auth")
public class AuthFrameController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EmailService emailService;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Role farmerRole = roleRepository.findByName(ERole.ROLE_FARMER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        Set<Role> roles = new HashSet<>();
        roles.add(farmerRole);

        user.setRoles(roles);
        userRepository.save(user);

        // Save the user to the repository
        User savedUser = userRepository.save(user);

        // Generate activation token
        String activationToken = UUID.randomUUID().toString();
        savedUser.setActivationToken(activationToken);
        userRepository.save(savedUser); // Save updated user with activation token

        // Send activation email with token
        String activationUrl = "http://localhost:4200/mail?token=" + activationToken;
        try {
            emailService.sendActivateMail(savedUser.getEmail(), activationUrl);
            logger.info("Activation email sent to " + savedUser.getEmail());
        } catch (Exception e) {
            logger.severe("Failed to send activation email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("User registered but failed to send activation email."));
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/activate/{token}")
    public ResponseEntity<?> activateUser(@PathVariable("token") String token) {
        User user = (User) userRepository.findByActivationToken(token)
                .orElseThrow(() -> new RuntimeException("Error: Activation token not found."));

        // Remove activation token
        user.setActivationToken(null);

        // Set user as verified
        user.setVerified(true);

        // Save updated user
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User activated successfully!"));
    }}
