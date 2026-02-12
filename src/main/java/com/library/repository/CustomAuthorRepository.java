package com.library.repository;

import com.library.model.Author;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomAuthorRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public List<Author> findAllWithGraph() {
        EntityGraph<Author> graph = entityManager.createEntityGraph(Author.class);
        graph.addSubgraph("books");
        return entityManager.createQuery("select a from Author a", Author.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
    }
}
