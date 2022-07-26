package com.payu.management.bootstrap;


import com.payu.management.model.Book;
import com.payu.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Loader implements CommandLineRunner {

    @Autowired
    private BookRepository repository;


    @Override
    public void run(String... strings) throws Exception {
        loadDetails();
    }

    private void loadDetails() {

        if(repository.count() ==0){
            Book book1 = new Book("OOP-using-java",987877681501L,new Date("28/08/2020"),250.83d, "eBOOK");
            repository.save(book1);

        }

    }

}
