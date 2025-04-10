package com.payu.management.service;


import com.payu.management.dto.BookDTO;

import java.util.List;


public interface BookService {
    List<BookDTO> getBooks();

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(Integer id, BookDTO bookDTO);

    void deleteBook(Integer id);

    BookDTO findBookById(Integer id);

}
