package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringOnlineBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOnlineBookStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BookService bookService) {
        return args -> {
            final Book book = new Book();
            book.setTitle("The Great Gatsby");
            book.setAuthor("F. Scott Fitzgerald");
            book.setIsbn("0000001");
            book.setPrice(BigDecimal.valueOf(99));
            book.setDescription("Set in the Jazz Age on Long Island, "
                    + "near New York City, the novel depicts first-person "
                    + "narrator Nick Carraway's interactions with Jay Gatsby, "
                    + "a mysterious millionaire obsessed with reuniting "
                    + "with his former lover, Daisy Buchanan.");
            book.setCoverImage("Sunless.jpg");
            bookService.save(book);
            bookService.findAll().forEach(System.out::println);
        };
    }
}
