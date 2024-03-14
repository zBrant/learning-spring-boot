package io.github.zbrant.bookservice.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Book implements Serializable {

    private Long id;
    private String author;
    private Date lauchDate;
    private Double price;
    private String title;
    private String currency;
    private String enviroment;

    public Book() {}

    public Book(Long id, String author, String title, Date lauchDate, Double price, String currency, String enviroment) {
        this.id = id;
        this.author = author;
        this.lauchDate = lauchDate;
        this.price = price;
        this.title = title;
        this.currency = currency;
        this.enviroment = enviroment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(lauchDate, book.lauchDate) && Objects.equals(price, book.price) && Objects.equals(title, book.title) && Objects.equals(currency, book.currency) && Objects.equals(enviroment, book.enviroment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, lauchDate, price, title, currency, enviroment);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLauchDate() {
        return lauchDate;
    }

    public void setLauchDate(Date lauchDate) {
        this.lauchDate = lauchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }
}
