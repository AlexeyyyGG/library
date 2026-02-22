package com.library.service;

import com.library.dto.AuthorResponse;
import com.library.dto.BookResponse;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorBatchService {
    private final AuthorRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(AuthorBatchService.class);

    @Transactional(readOnly = true)
    public List<AuthorResponse> findWithBatch() {
        logger.info("=====Batch Size=====");
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
}
