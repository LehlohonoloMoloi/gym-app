package com.sire.gym.controller;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.RegisterRequest;
import com.sire.gym.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sire.gym.util.ResponseUtils.generateSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gym/api/v1/auth")
public class AuthController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody @Valid RegisterRequest request) {
        registrationService.register(request);
        ApiResponse<Void> response = generateSuccessResponse("User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
