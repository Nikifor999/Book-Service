package com.ifortex.bookservice.repository.impl;

import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.repository.CustomBookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> countBooksByGenre() {
        String sql = """
                SELECT genre, COUNT(*) AS genreCount
                FROM (SELECT unnest(genres) AS genre FROM Book ) AS genre_table
                GROUP BY genre
                ORDER BY genreCount DESC
                """;
        return entityManager.createQuery(sql, Object[].class).getResultList();
    }

    @Override
    public List<Book> findBooksBySearchWithFilters(String jpql, Map<String, Object> parameters) {
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }
}
