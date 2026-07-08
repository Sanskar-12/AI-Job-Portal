package com.sanskar.job.job_portal_resume_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.LanguageResponse;
import com.sanskar.job.job_portal_resume_service.payload.AddLanguageRequest;
import com.sanskar.job.job_portal_resume_service.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes/{resumeId}/language")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("/create")
    public ResponseEntity<LanguageResponse> addLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddLanguageRequest request
    ) throws Exception {
        return ResponseEntity.ok(languageService.addLanguage(resumeId, candidateId, request));
    }

    @GetMapping("/get/language")
    public ResponseEntity<List<LanguageResponse>> getLanguages(
            @PathVariable Long resumeId
    ) {
        return ResponseEntity.ok(languageService.getLanguages(resumeId));
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<LanguageResponse> updateLanguage(
            @PathVariable Long languageId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddLanguageRequest request
    ) throws Exception {
        return ResponseEntity.ok(languageService.updateLanguage(languageId, resumeId, candidateId, request));
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<ApiResponse> deleteLanguage(
            @PathVariable Long languageId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        languageService.deleteLanguage(languageId, resumeId, candidateId);

        return ResponseEntity.ok(new ApiResponse("Language Deleted Successfully",true));
    }
}
