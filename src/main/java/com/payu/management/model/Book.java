package com.payu.management.model;


import com.payu.management.enums.BookType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Integer id;
    @Column
    private String name;
    @Column
    private Long ISBNNumber;
    @Column
    private LocalDate publishDate;
    @Column
    private Double price;
    @Column
    BookType bookType;

    public Book(){}

    public Book(Integer id, String name, Long ISBNNumber, LocalDate publishDate, Double price, BookType bookType) {
        this.id = id;
        this.name = name;
        this.ISBNNumber = ISBNNumber;
        this.publishDate = publishDate;
        this.price = price;
        this.bookType = bookType;
    }

    public Book(String name, Long ISBNNumber, LocalDate publishDate, Double price, BookType bookType) {
        this.name = name;
        this.ISBNNumber = ISBNNumber;
        this.publishDate = publishDate;
        this.price = price;
        this.bookType = bookType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getISBNNumber() {
        return ISBNNumber;
    }

    public void setISBNNumber(Long ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}
