package com.sire.gym.controller;

import com.sire.gym.common.Status;
import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMemberRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/gym/members")
public class MemberController {

    @PostMapping
    public CompletableFuture<ApiResponse<Void>> createMember(@Valid @RequestBody CreateMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> ApiResponse.of(Status.SUCCESS, "Member created successfully!"));
    }

    @GetMapping
    public CompletableFuture<ApiResponse<Void>> getMembers() {
        return CompletableFuture.supplyAsync(() -> ApiResponse.of(Status.SUCCESS, "List of members"));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ApiResponse<Void>> getMemberById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> ApiResponse.of(Status.SUCCESS, "Member details by ID"));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ApiResponse<Void>> updateMember(@PathVariable Long id, @RequestBody CreateMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> ApiResponse.of(Status.SUCCESS, "Member updated successfully!"));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ApiResponse<Void>> deleteMember(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> ApiResponse.of(Status.SUCCESS, "Member deleted successfully!"));
    }

}
