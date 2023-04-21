package com.example.Library_management.Models;

import com.example.Library_management.Enums.TransactionStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    private String transactionId= UUID.randomUUID().toString();
    private boolean isIssueOperation;
    private int fine;
    @CreationTimestamp
    private Date transactionDate;

    public Transactions() {
        fine=0;
    }
    //creating foreign key of Parent entity Book
    @ManyToOne
    @JoinColumn
    private Book book;

    //creating foreign key of parent entity card
    @ManyToOne
    @JoinColumn
    private Card card;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isIssueOperation() {
        return isIssueOperation;
    }
    public void setIssueOperation (boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}
