package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    // finds all books

    @Override
    public ArrayList<Book> findAll(Pageable pageable) {
        ArrayList<Book> addBooks = new ArrayList<>();
        bookRepository.findAll(pageable).iterator().forEachRemaining(addBooks::add);
        return addBooks;
    }

    // update (adding books -> authors

    @Transactional
    @Override
    public Book update(long id, Book book) {
        Book currentBook = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(book.getBooktitle() != null){
            currentBook.setBooktitle(book.getBooktitle());
        }
        if(book.getCopy() > 0){
            currentBook.setCopy(book.getCopy());
        }
        if (book.getIsbn() != null){
            currentBook.setIsbn(book.getIsbn());
        }
        if (book.getAuthors() != null && book.getAuthors().size() > 0){
            currentBook.setAuthors(book.getAuthors());
        }

        bookRepository.save(currentBook);
        return currentBook;
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
