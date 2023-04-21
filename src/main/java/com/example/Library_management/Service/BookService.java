package com.example.Library_management.Service;

import com.example.Library_management.Converters.BookToDtoConverter;
import com.example.Library_management.DTOs.ResponseDto.BookResponseDto;
import com.example.Library_management.Models.Author;
import com.example.Library_management.Models.Book;
import com.example.Library_management.Repository.AuthorRepository;
import com.example.Library_management.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    public String addBook(Book book){
        int authorId=book.getAuthor().getId();//user will pass Author object with id through postman
        // we will get author id from that
        Author author= authorRepository.findById(authorId).get();

        List<Book> oldBooks=author.getBooksWritten();
        oldBooks.add(book);//updating in the parent entity

        authorRepository.save(author);// saving author in DB ,by cascading child class will also be saved in their corresponding repository

        return "Book added successfully ";
    }
    public BookResponseDto getBook(int bookId) throws Exception{
        Book book=bookRepository.findById(bookId).get();
        BookResponseDto bookResponseDto=new BookResponseDto();
        bookResponseDto=BookToDtoConverter.convert(book);
        return bookResponseDto;
    }
    public List<BookResponseDto> getAllBooks(){
        List<BookResponseDto> list=new ArrayList<>();
        List<Book> booksList=bookRepository.findAll();
        for(Book book:booksList){
            BookResponseDto bookResponseDto=new BookResponseDto();
            bookResponseDto=BookToDtoConverter.convert(book);
            list.add(bookResponseDto);
        }
        return list;
    }
    public List<BookResponseDto> booksByAuthorId(int id){
        List<BookResponseDto> list=new ArrayList<>();
        List<Book> booksList=bookRepository.findAll();
        for(Book book:booksList){
            if(book.getAuthor().getId()==id){
                BookResponseDto bookResponseDto=new BookResponseDto();
                bookResponseDto=BookToDtoConverter.convert(book);
                list.add(bookResponseDto);
            }
        }
        return list;
    }
    public String delete(int id){
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }

}
