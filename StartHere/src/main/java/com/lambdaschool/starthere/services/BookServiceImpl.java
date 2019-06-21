package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public ArrayList<Book> findAll() {
        ArrayList<Book> addBooks = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(addBooks::add);
        return addBooks;
    }

    @Override
    public void update(long id, Book book) {

    }

    @Override
    public void delete(long id) {

    }
}
