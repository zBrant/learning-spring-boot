package io.github.zbrant.bookservice.controller;

import io.github.zbrant.bookservice.model.Book;
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

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable(name = "id") Long id, @PathVariable(name = "currency") String currency){
        Book book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not Found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
        var cambio = response.getBody();

        String port = environment.getProperty("local.server.port");
        book.setEnviroment(port);
        book.setPrice(cambio.getConvertedValue());

        return book;
    }
}
