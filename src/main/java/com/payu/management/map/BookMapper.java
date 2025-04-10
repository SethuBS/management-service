package com.payu.management.map;

import com.payu.management.dto.BookDTO;
import com.payu.management.model.Book;

public class BookMapper {

    public static BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setName(book.getName());
        dto.setISBNNumber(book.getISBNNumber());
        dto.setPublishDate(book.getPublishDate());
        dto.setPrice(book.getPrice());
        dto.setBookType(book.getBookType());
        return dto;
    }

    public static Book toEntity(BookDTO dto) {
        return new Book(
                dto.getId(),
                dto.getName(),
                dto.getISBNNumber(),
                dto.getPublishDate(),
                dto.getPrice(),
                dto.getBookType()
        );
    }
}
