package com.ifortex.bookservice.repository;

import com.ifortex.bookservice.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>, CustomBookRepository {

}
