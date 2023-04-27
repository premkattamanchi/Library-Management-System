package com.example.Library_management.Service;

import com.example.Library_management.DTOs.RequestDto.IssueBookRequestDto;
import com.example.Library_management.Enums.CardStatus;
import com.example.Library_management.Enums.TransactionStatus;
import com.example.Library_management.Models.Book;
import com.example.Library_management.Models.Card;
import com.example.Library_management.Models.Transactions;
import com.example.Library_management.Repository.BookRepository;
import com.example.Library_management.Repository.CardRepository;
import com.example.Library_management.Repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionsRepository transactionsRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{
        int bookId= issueBookRequestDto.getBookId();
        int cardId =issueBookRequestDto.getCardId();
        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();

        Transactions transactions=new Transactions();
        transactions.setIssueOperation(true);
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        //validations
        if(book==null || book.isIssued()==true){
            transactions.setTransactionStatus(TransactionStatus.FAIL);
            transactionsRepository.save(transactions);
            throw new Exception("book is not available");
        }

        if(card==null || card.getCardStatus()!=CardStatus.ACTIVATED) {
            transactions.setTransactionStatus(TransactionStatus.FAIL);
            transactionsRepository.save(transactions);
            throw new Exception("card is not valid");
        }

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        //setting card entity attributes
        List<Book> booksList=card.getBooksIssued();
        booksList.add(book);
        card.setBooksIssued(booksList);
        List<Transactions> transactionsList=card.getTransactionsList();
        transactionsList.add(transactions);
        card.setTransactionsList(transactionsList);
        //setting Book entity attributes
        book.setCard(card);
        book.setIssued(true);
        List<Transactions> transactionsList1=book.getBookTransactionsList();
        transactionsList1.add(transactions);
        book.setBookTransactionsList(transactionsList1);

        cardRepository.save(card);//by cascading effect child entities Book and Transactions will automatically be saved

        return "Book successfully issued";
    }
    public String returnBook(int cardId,int bookId)throws Exception{
        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();
        Transactions transactions=new Transactions();
        transactions.setIssueOperation(false);
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionStatus(TransactionStatus.PENDING);
        if(book.isIssued()==true && book.getCard().getId()==cardId){
            int fine=calculateFine(cardId,bookId);
            book.setIssued(false);
            book.setCard(null);
            transactions.setFine(fine);
            transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        }
        transactionsRepository.save(transactions);
        book.getBookTransactionsList().add(transactions);
        card.getTransactionsList().add(transactions);
        cardRepository.save(card);
        return "Book returned successfully";
    }
    public int calculateFine(int cardId,int bookId)throws Exception{
        int finePerDay=2;
        long fine=0;
        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();
        List<Transactions> transactionsList=card.getTransactionsList();
        Date transactionDate=null;
        for(Transactions transaction:transactionsList){
            if(transaction.getBook().getId()==bookId && transaction.getBook().isIssued()==true){
                transactionDate=transaction.getTransactionDate();
            }
        }
        if(transactionDate==null)
            throw new Exception("Cannot calculate Fine");
        LocalDate currentDate = LocalDate.parse("2023-05-27");
        //to convert Date to LocalDate
        LocalDate issuedDate= transactionDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long days = issuedDate.until(currentDate, ChronoUnit.DAYS);
        long NoOfDaysToPayFine=days-14;
        if(NoOfDaysToPayFine>0)
            fine=NoOfDaysToPayFine*2;
        return (int)fine;

    }

}
