package com.payu.management.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.payu.management.exception.InvalidBookTypeException;

import java.io.IOException;

@JsonDeserialize(using = BookType.BookTypeDeserializer.class)
public enum BookType {
    EBOOK("EBOOK"),
    HARDCOPY("HARDCOPY"),
    SOFTCOPY("SOFTCOPY");

    private final String value;

    BookType(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value.toUpperCase();
    }

    public static class BookTypeDeserializer extends JsonDeserializer<BookType> {
        @Override
        public BookType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String value = jsonParser.getText();
            for (BookType type : BookType.values()) {
                if (type.value.equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new InvalidBookTypeException("Invalid book type: " + value + " ");
        }
    }
}
