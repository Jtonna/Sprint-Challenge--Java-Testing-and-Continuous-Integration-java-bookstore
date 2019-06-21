package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    // finds all books

    @Override
    public ArrayList<Book> findAll() {
        ArrayList<Book> addBooks = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(addBooks::add);
        return addBooks;
    }

    // update (adding books -> authors
    @Override
    public void update(long id, Book book) {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find book with the id of: " + id));

        if (book.getBooktitle() != null) {
            currentBook.setBooktitle(book.getBooktitle());
        }

        if (book.getIsbn() != null) {
            currentBook.setIsbn(book.getIsbn());
        }

        currentBook.setCopy(book.getCopy());

        if (book.getAuthors().size() != 0) {
            currentBook.getAuthors().clear();
            ArrayList<Author> newAuthors = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                newAuthors.add(new Author(author.getLastname(), author.getFirstname()));
            }
            currentBook.setAuthors(newAuthors);
        }

        bookRepository.save(currentBook);
    }

    // delete books
    @Override
    public void delete(long id) {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find book with the id of: " + id));

        if (currentBook != null) {
            bookRepository.deleteById(id);
        }
    }

    // set book author

    @Override
    public void setBookAuthor(long bookid, long authorid) {
        Book book = bookRepository.findById(bookid)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find book with the id: " + bookid));
        Author author = authorRepository.findById(authorid)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find author with the id: " + authorid));
        if (book != null || author != null) {
            book.getAuthors().add(author);
        }

        bookRepository.save(book);
    }
}
