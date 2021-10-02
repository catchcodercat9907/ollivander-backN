package io.ollivander.ollivanderbackend.rest;

import io.ollivander.ollivanderbackend.model.dto.*;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Role;
import io.ollivander.ollivanderbackend.model.repos.AccountRepository;
import io.ollivander.ollivanderbackend.model.repos.RoleRepository;
import io.ollivander.ollivanderbackend.security.JwtUtils;
import io.ollivander.ollivanderbackend.security.UserDetailsImpl;
import io.ollivander.ollivanderbackend.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthService authService;

//    @PostMapping("/signin")
//    public ResponseEntity<Object> authenticateUser(@Validated @RequestBody LoginRequest request) {
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(userDetails.getId(), jwt, userDetails.getUsername(), roles));
//    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUserV2(@Validated @RequestBody LoginRequest request) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        Object auth = authService.authenticateUser(request);
        response.setResponseData(auth);
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<Object> registerUser(@Validated @RequestBody SignupRequest request) {
//        if (accountRepo.existsByUsername(request.getUsername())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already existed!"));
//        }
//        Account account = new Account(request.getUsername(), encoder.encode(request.getPassword()));
//        Set<String> strRole = request.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (strRole == null) {
//            Role memRole = roleRepo.findByName(Role.ROLE_MEMBER.getName())
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(memRole);
//        } else {
//            strRole.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepo.findByName(Role.ROLE_ADMIN.getName())
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//                        break;
//                    case "staff":
//                        Role staffRole = roleRepo.findByName(Role.ROLE_STAFF.getName())
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(staffRole);
//                        break;
//                    default:
//                        Role memberRole = roleRepo.findByName(Role.ROLE_MEMBER.getName())
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(memberRole);
//                }
//            });
//        }
//        account.setRoles(roles);
//        accountRepo.save(account);
//
//        return ResponseEntity.ok(new MessageResponse("Account registered successfully!"));
//    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUserV2(@Validated @RequestBody SignupRequest request) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        Object signUp = authService.signUp(request);
        response.setResponseData(signUp);
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
