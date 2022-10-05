package com.payu.management.service;


import com.payu.management.model.Book;
import com.payu.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        repository.findAll().forEach(books::add);
        books = books.stream().sorted(Comparator.comparing(Book::getId).reversed()).collect(Collectors.toList());
        return books;
    }

    public Book createBook(Book book) {
        Date date = dateWorkAround(book.getPublishDate());
        book.setPublishDate(date);
        return repository.save(book);
    }

    public Book updateBook(Integer id, Book book) {
        Book bookInstance = repository.findById(id).get();
        bookInstance.setName(book.getName());
        bookInstance.setISBNNumber(book.getISBNNumber());
        Date date = dateWorkAround(book.getPublishDate());
        bookInstance.setPublishDate(date);
        bookInstance.setPrice(book.getPrice());
        bookInstance.setBookType(book.getBookType());
        repository.save(bookInstance);
        return bookInstance;
    }

    public void deleteBook(Integer id) {
        repository.deleteById(id);
    }

    public Book findById(Integer id) {
        Book bookInstance;
        bookInstance = repository.findById(id).get();
        return bookInstance;
    }

    private Date dateWorkAround(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.format(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.YEAR, date.getYear());
        c.add(Calendar.MONTH, date.getMonth());
        c.add(Calendar.DATE, date.getDate());

        Date currentDatePlusOne = c.getTime();
        return currentDatePlusOne;
    }
}
