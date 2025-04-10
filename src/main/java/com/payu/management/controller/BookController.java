package com.payu.management.controller;


import com.payu.management.dto.BookDTO;
import com.payu.management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/management-service/api/v1/book")
public class BookController {


    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return new ResponseEntity<>(service.getBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(service.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/{bookId}"})
    public ResponseEntity<BookDTO> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(service.updateBook(bookId, bookDTO), HttpStatus.OK);
    }

    @DeleteMapping({"/{bookId}"})
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("bookId") Integer bookId) {
        service.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

