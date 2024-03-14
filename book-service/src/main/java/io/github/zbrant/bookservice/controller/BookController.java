package io.github.zbrant.bookservice.controller;

import io.github.zbrant.bookservice.model.Book;
import io.github.zbrant.bookservice.proxy.CambioProxy;
import io.github.zbrant.bookservice.repository.BookRepository;
import io.github.zbrant.bookservice.response.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable(name = "id") Long id, @PathVariable(name = "currency") String currency){
        Book book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not Found");

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        String port = environment.getProperty("local.server.port");
        book.setEnviroment(port);
        book.setPrice(cambio.getConvertedValue());

        return book;
    }
}
