package com.lambdaschool.starthere.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Book {

    @Id
    @GeneratedValue
    private long bookid;

    private String booktitle, isbn;

    private int copy;

    public Book() {
    }

    public Book(String booktitle, String isbn, int copy) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }
}
