package com.lambdaschool.starthere.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    @PutMapping
    @PostMapping
    @DeleteMapping
}
