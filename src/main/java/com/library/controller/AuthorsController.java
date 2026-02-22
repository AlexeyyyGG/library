package com.library.controller;

import com.library.dto.AuthorResponse;
import com.library.dto.AuthorsComparisonResponse;
import com.library.service.AuthorBatchService;
import com.library.service.AuthorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {
    private final AuthorService service;
    private final AuthorBatchService batchService;

    @GetMapping
    public AuthorsComparisonResponse getAll() {
        return service.testAllWithOutBatch();
    }

    @GetMapping("/batch")
    public List<AuthorResponse> getAllBatch(){
        return batchService.findWithBatch();
    }
}