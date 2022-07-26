package com.payu.management.controller;


import com.payu.management.model.Book;
import com.payu.management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/management-service/api/v1/book")
public class BookController {


    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
      try {
          List<Book> books = service.getBooks();
          if(!books.isEmpty()){
              return new ResponseEntity<>(books, HttpStatus.OK);
          } else if(books.isEmpty()){
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
        return null;
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        try {
            if (book != null) {
                Book bookInstance = service.createBook(book);
                return new ResponseEntity<>(bookInstance,HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"/{bookId}"})
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody Book book){
       try {
           Book bookData = service.findById(bookId);
           if(bookData != null){
               Book bookToUpdate = service.updateBook(bookId,book);
               return new ResponseEntity<>(bookToUpdate, HttpStatus.OK);
           }  else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @DeleteMapping({"/{bookId}"})
    public ResponseEntity<Book> deleteTodo(@PathVariable("bookId") Integer bookId) {
        service.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

