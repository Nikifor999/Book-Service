package com.ifortex.bookservice.service;

import com.ifortex.bookservice.model.Book;

import java.util.List;
import java.util.Map;

public interface CustomBookService {
    Map<String, Long> getCountBooksByGenre();

    List<Book> getBooksBySearchWithFilters(String title, String description,
                                           String author, String genre);
}
