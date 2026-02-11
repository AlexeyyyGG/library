package com.library.controller;

import com.library.dto.AuthorsComparisonResponse;
import com.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {
    private final AuthorService service;

    @GetMapping
    public AuthorsComparisonResponse getAll() {
        return service.testBoth();
    }
}