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

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Johannesburg"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateToFormat = dateFormat.format(date);
        Date date1 = new Date(dateToFormat);

        cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, 1);
        Date currentDatePlusOne = cal.getTime();
        return currentDatePlusOne;
    }
}
