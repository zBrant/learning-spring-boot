package io.github.zbrant.bookservice.controller;

import io.github.zbrant.bookservice.model.Book;
import io.github.zbrant.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable(name = "id") Long id, @PathVariable(name = "currency") String currency){
        Book book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not Found");

        String port = environment.getProperty("local.server.port");
        book.setEnviroment(port);

        return book;
    }
}
