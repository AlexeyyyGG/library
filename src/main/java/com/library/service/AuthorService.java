package com.library.service;

import com.library.dto.AuthorResponse;
import com.library.dto.AuthorsComparisonResponse;
import com.library.dto.BookResponse;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import com.library.repository.CustomAuthorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final CustomAuthorRepository customRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    public List<AuthorResponse> getAll() {
        List<Author> authors = repository.findAll();
        return mapToDTO(authors);
    }

    public List<AuthorResponse> getAllWithFetchJoin() {
        List<Author> authors = repository.findAllWithFetchJoin();
        return mapToDTO(authors);
    }

    public List<AuthorResponse> getAllWithGraph() {
        List<Author> authors = customRepository.findAllWithGraph();
        return mapToDTO(authors);
    }

    public AuthorsComparisonResponse testAllWithOutBatch() {
        logger.info("=====N+1=====");
        List<AuthorResponse> nPlusOne = getAll();

        logger.info("=====Fetch Join=====");
        List<AuthorResponse> fetchJoin = getAllWithFetchJoin();

        logger.info("=====Entity Graph=====");
        List<AuthorResponse> entityGraph = getAllWithGraph();

        return new AuthorsComparisonResponse(nPlusOne, fetchJoin, entityGraph);
    }

    private List<AuthorResponse> mapToDTO(List<Author> authors) {
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