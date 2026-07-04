package com.sire.gym.controller;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMemberRequest;
import com.sire.gym.dto.MemberResponse;
import com.sire.gym.dto.UpdateMemberRequest;
import com.sire.gym.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gym/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public CompletableFuture<ResponseEntity<ApiResponse<MemberResponse>>> createMember(@Valid @RequestBody CreateMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            ApiResponse<MemberResponse> response = memberService.createMember(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        });
    }

    @GetMapping
    public CompletableFuture<Page<MemberResponse>> getMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return CompletableFuture.supplyAsync(() -> memberService.getMembers(page, size));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse<MemberResponse>>> getMemberById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            ApiResponse<MemberResponse> response = memberService.getMemberById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        });
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse<MemberResponse>>> updateMember(
            @PathVariable Long id, @RequestBody @Valid UpdateMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            ApiResponse<MemberResponse> response = memberService.updateMember(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse<Void>>> deleteMember(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            ApiResponse<Void> response = memberService.deleteMember(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        });
    }

}
