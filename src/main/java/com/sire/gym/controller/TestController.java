package com.sire.gym.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/test/whoami")
    public ResponseEntity<String> whoAmI(Authentication authentication) {
        return ResponseEntity.ok("Authenticated as: " + authentication.getName());
    }
}
