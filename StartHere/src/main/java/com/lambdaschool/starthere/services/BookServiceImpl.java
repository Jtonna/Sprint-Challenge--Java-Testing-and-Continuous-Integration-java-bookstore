package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class BookServiceImpl implements BookService {
    
    @Autowired
    BookRepository bookRepository;

    @Override
    public ArrayList<Book> findAll() {
        return null;
    }

    @Override
    public void update(long id, Book book) {

    }

    @Override
    public void delete(long id) {

    }
}
