package com.ifortex.bookservice.controller;

import com.ifortex.bookservice.dto.SearchCriteria;
import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.service.BookService;
import com.ifortex.bookservice.service.CustomBookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    private final CustomBookService customBookService;

    @GetMapping("gc")
    public Map<String, Long> genresCount() {
        return customBookService.getCountBooksByGenre();
    }

    @GetMapping("find")
    public List<Book> search(@RequestParam(required = false) String title,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) String genre) {
        return customBookService.getBooksBySearchWithFilters(title, description, author, genre);
    }

    @GetMapping("statistic")
    public Map<String, Long> getStatistic() {
        return bookService.getBooks();
    }

    @GetMapping("search")
    public List<Book> findBooks(@RequestBody @Nullable SearchCriteria searchCriteria) {
        return bookService.getAllByCriteria(searchCriteria);
    }
}
