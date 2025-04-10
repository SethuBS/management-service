package com.payu.management.service;

import com.payu.management.dto.BookDTO;
import com.payu.management.exception.BookNotFoundException;
import com.payu.management.map.BookMapper;
import com.payu.management.model.Book;
import com.payu.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .sorted((book1, book2) -> book2.getPublishDate().compareTo(book1.getPublishDate())) // Sort by publish date (desc)
                .collect(Collectors.toList());
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book savedBook = bookRepository.save(BookMapper.toEntity(bookDTO));
        return BookMapper.toDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found."));

        existingBook.setName(bookDTO.getName());
        existingBook.setISBNNumber(bookDTO.getISBNNumber());
        existingBook.setPublishDate(bookDTO.getPublishDate());
        existingBook.setPrice(bookDTO.getPrice());
        existingBook.setBookType(bookDTO.getBookType());

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toDTO(updatedBook);
    }

    @Override
    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO findBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found."));
        return BookMapper.toDTO(book);
    }
}
