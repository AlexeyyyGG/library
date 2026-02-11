package com.library.service;

import com.library.dto.AuthorResponse;
import com.library.dto.AuthorsComparisonResponse;
import com.library.dto.BookResponse;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;

    public List<AuthorResponse> getAll() {
        List<Author> authors = repository.findAll();
        return authors.stream()
                .map(author -> new AuthorResponse(
                        author.getId(),
                        author.getName(),
                        author.getBooks().stream()
                                .map(book -> new BookResponse(book.getId(), book.getTitle()))
                                .toList()
                ))
                .toList();
    }

    public List<AuthorResponse> getAllFixed() {
        List<Author> authors = repository.findAllWithBooks();
        return authors.stream()
                .map(author -> new AuthorResponse(
                        author.getId(),
                        author.getName(),
                        author.getBooks().stream()
                                .map(book -> new BookResponse(book.getId(), book.getTitle()))
                                .toList()
                ))
                .toList();
    }

    public AuthorsComparisonResponse testBoth() {
        List<AuthorResponse> nPlusOne = getAll();
        List<AuthorResponse> fetchJoin = getAllFixed();
        return new AuthorsComparisonResponse(nPlusOne, fetchJoin);
    }
}