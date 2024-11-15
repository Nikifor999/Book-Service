package com.ifortex.bookservice.repository;

import com.ifortex.bookservice.model.Book;

import java.util.List;
import java.util.Map;

public interface CustomBookRepository {
    List<Object[]> countBooksByGenre();

     List<Book> findBooksBySearchWithFilters(String sql, Map<String, Object> parameters);
}
