package com.payu.management.bootstrap;


import com.payu.management.enums.BookType;
import com.payu.management.model.Book;
import com.payu.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;

@Component
public class Loader implements CommandLineRunner {

    @Autowired
    private BookRepository repository;


    @Override
    public void run(String... strings) throws Exception {
        loadDetails();
    }

    private void loadDetails() {
        if (repository.count() == 0) {
            Random random = new Random();
            BookType[] types = {BookType.EBOOK, BookType.HARDCOPY, BookType.SOFTCOPY};

            for (int i = 1; i <= 150; i++) {
                String title = "Book Title " + i;
                long isbn = 9780000000000L + random.nextInt(1000000);
                int randomDays = random.nextInt(10_000);
                LocalDate publishDate = LocalDate.now().minusDays(randomDays);
                double price = 100 + (500 * random.nextDouble());
                BigDecimal roundedPrice = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
                double finalPrice = roundedPrice.doubleValue();
                BookType type = types[random.nextInt(types.length)];


                Book book = new Book(title, isbn, publishDate, finalPrice, type);
                repository.save(book);
            }

            System.out.println("Loaded 150 random books.");
        }
    }

}
