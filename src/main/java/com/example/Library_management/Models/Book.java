package com.example.Library_management.Models;

import com.example.Library_management.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int pages;
    private boolean isIssued;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne    //uni directional mapping with parent Author
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Card card;  // uni directional mapping with parent Card
    public Book() {
    }
    //bidirectional mapping with child Entity Transactions
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transactions> bookTransactionsList=new ArrayList<>();

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public List<Transactions> getBookTransactionsList() {
        return bookTransactionsList;
    }

    public void setBookTransactionsList(List<Transactions> bookTransactionsList) {
        this.bookTransactionsList = bookTransactionsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
