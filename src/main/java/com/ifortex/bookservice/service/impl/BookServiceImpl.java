package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.repository.BookRepository;
import com.ifortex.bookservice.repository.CustomBookRepository;
import com.ifortex.bookservice.service.CustomBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements CustomBookService {

    private final BookRepository bookRepository;

    @Override
    public Map<String, Long> getCountBooksByGenre() {
        List<Object[]> results = bookRepository.countBooksByGenre();
        LinkedHashMap<String, Long> treeMap = new LinkedHashMap<>();
        for (Object[] result : results) {
            treeMap.put(String.valueOf(result[0]), (Long) result[1]);
        }
        return treeMap;
    }

    @Override
    public List<Book> getBooksBySearchWithFilters(String title, String description,
                                                  String author, String genre) {
        StringBuilder jpql = new StringBuilder("select b from Book b");
        List<String> conditions = new ArrayList<>();
        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasLength(title)) {
            conditions.add("LOWER(b.title) like LOWER(:title)");
            parameters.put("title", "%" + title.toLowerCase() + "%");
        }

        if (StringUtils.hasLength(description)) {
            conditions.add("LOWER(b.description) like LOWER(:description)");
            parameters.put("description", "%" + description.toLowerCase() + "%");
        }

        if (StringUtils.hasLength(author)) {
            conditions.add("LOWER(b.author) like LOWER(:author)");
            parameters.put("author", "%" + author.toLowerCase() + "%");
        }

        if (StringUtils.hasLength(genre)) {
            conditions.add("LOWER(b.genre) like LOWER(:genre)");
            parameters.put("genre", "%" + genre.toLowerCase() + "%");
        }

        if (!conditions.isEmpty()) {
            jpql.append(" where ").append(String.join(" and ", conditions));
        }
        return bookRepository.findBooksBySearchWithFilters(jpql.toString(), parameters);
    }
}
