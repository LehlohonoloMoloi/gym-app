package com.sire.gym.service.impl;

import com.sire.gym.dto.RegisterRequest;
import com.sire.gym.exception.UsernameExistsException;
import com.sire.gym.model.Role;
import com.sire.gym.model.User;
import com.sire.gym.repository.UserRepository;
import com.sire.gym.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new UsernameExistsException("Username already taken");
        }

        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(Role.ROLE_USER))
                .build();

        userRepository.save(user);
    }

}
