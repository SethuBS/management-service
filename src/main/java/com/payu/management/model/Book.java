package com.payu.management.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Integer id;
    @Column
    private  String name;
    @Column
    private Long ISBNNumber;
    @Column
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date publishDate;
    @Column
    private Double price;
    @Column
    String bookType;

    public Book(){

    }

    public Book(String name, Long ISBNNumber, Date publishDate, Double price, String bookType) {
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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", ISBNNumber=" + ISBNNumber +
                ", publishDate=" + publishDate +
                ", price=" + price +
                ", bookType=" + bookType +
                '}';
    }
}
