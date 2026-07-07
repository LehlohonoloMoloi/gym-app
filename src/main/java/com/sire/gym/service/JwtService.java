package com.sire.gym.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    List<String> extractRoles(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

}
