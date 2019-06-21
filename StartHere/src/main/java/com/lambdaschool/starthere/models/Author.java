package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue
    private long authorid;

    private String firstname, lastname;

    @ManyToMany
    @JoinTable(name = "wrote",
            joinColumns = {@JoinColumn(name = "authorid")},
            inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // for  many to many

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
