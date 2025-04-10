package com.payu.management;

import com.payu.management.controller.BookController;
import com.payu.management.dto.BookDTO;
import com.payu.management.enums.BookType;
import com.payu.management.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	void getAllBooks() throws Exception {
		BookDTO book1 = new BookDTO("Book 1", 123456789L, LocalDate.of(2021, 1, 1), 100.0, BookType.EBOOK);
		BookDTO book2 = new BookDTO("Book 2", 987654321L, LocalDate.of(2022, 1, 1), 150.0, BookType.HARDCOPY);

		List<BookDTO> books = Arrays.asList(book1, book2);
		when(bookService.getBooks()).thenReturn(books);

		mockMvc.perform(get("/management-service/api/v1/book"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Book 1"))
				.andExpect(jsonPath("$[1].name").value("Book 2"));

		verify(bookService, times(1)).getBooks();
	}

	@Test
	void saveBook() throws Exception {
		BookDTO bookDTO = new BookDTO("New Book", 123456789L, LocalDate.of(2023, 5, 1), 100.0, BookType.SOFTCOPY);
		when(bookService.createBook(any(BookDTO.class))).thenReturn(bookDTO);

		mockMvc.perform(post("/management-service/api/v1/book")
				.contentType("application/json")
				.content("{\"name\":\"New Book\",\"ISBNNumber\":123456789,\"publishDate\":\"2023-05-01\",\"price\":100.0,\"bookType\":\"SOFTCOPY\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("New Book"));

		verify(bookService, times(1)).createBook(any(BookDTO.class));
	}

	@Test
	void updateBook() throws Exception {
		BookDTO updatedBookDTO = new BookDTO("Updated Book", 123456789L, LocalDate.of(2023, 6, 1), 120.0, BookType.HARDCOPY);
		when(bookService.updateBook(eq(1), any(BookDTO.class))).thenReturn(updatedBookDTO);

		mockMvc.perform(put("/management-service/api/v1/book/1")
				.contentType("application/json")
				.content("{\"name\":\"Updated Book\",\"ISBNNumber\":123456789,\"publishDate\":\"2023-06-01\",\"price\":120.0,\"bookType\":\"HARDCOPY\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Updated Book"));

		verify(bookService, times(1)).updateBook(eq(1), any(BookDTO.class));
	}

	@Test
	void deleteBook() throws Exception {
		doNothing().when(bookService).deleteBook(1);

		mockMvc.perform(delete("/management-service/api/v1/book/1"))
				.andExpect(status().isNoContent());

		verify(bookService, times(1)).deleteBook(1);
	}
}