package com.payu.management.dto;

import com.payu.management.enums.BookType;

import java.time.LocalDate;

public class BookDTO {

    private Integer id;

    private String name;

    private Long ISBNNumber;

    private LocalDate publishDate;

    private Double price;

    BookType bookType;

    public BookDTO() {
    }

    public BookDTO(Integer id, String name, Long ISBNNumber, LocalDate publishDate, Double price, BookType bookType) {
        this.id = id;
        this.name = name;
        this.ISBNNumber = ISBNNumber;
        this.publishDate = publishDate;
        this.price = price;
        this.bookType = bookType;
    }


    public BookDTO(String name, Long ISBNNumber, LocalDate publishDate, Double price, BookType bookType) {
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
