package com.sire.gym.controller;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMembershipRequest;
import com.sire.gym.dto.MembershipResponse;
import com.sire.gym.model.MembershipStatus;
import com.sire.gym.service.MembershipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("gym/api/v1")
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/members/{memberId}/membership")
    public CompletableFuture<ResponseEntity<ApiResponse<MembershipResponse>>> createMembership(
            @PathVariable Long memberId,
            @RequestBody @Valid CreateMembershipRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            ApiResponse<MembershipResponse> response = membershipService.createMembership(memberId, request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        });
    }

    @GetMapping("/members/{memberId}/membership")
    public CompletableFuture<ResponseEntity<ApiResponse<MembershipResponse>>> getMembership(@PathVariable Long memberId) {
        return CompletableFuture.supplyAsync(() -> {
            ApiResponse<MembershipResponse> response = membershipService.retrieveMembership(memberId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        });
    }

    @PatchMapping("/members/{memberId}/membership/cancel")
    public CompletableFuture<ResponseEntity<ApiResponse<MembershipResponse>>> cancelMembership(@PathVariable Long memberId) {
        return CompletableFuture.supplyAsync(() -> {
            ApiResponse<MembershipResponse> response = membershipService.cancelMembership(memberId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/memberships")
    public CompletableFuture<Page<MembershipResponse>> getAllMemberships(
            // @Todo: validate the status parameter to ensure it matches the MembershipStatus enum values
            @RequestParam(required = false) MembershipStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return CompletableFuture.supplyAsync(() -> membershipService.getAllMemberships(status, page, size));
    }

}
