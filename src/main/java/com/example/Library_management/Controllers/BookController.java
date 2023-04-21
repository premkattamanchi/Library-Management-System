package com.example.Library_management.Controllers;

import com.example.Library_management.DTOs.ResponseDto.BookResponseDto;
import com.example.Library_management.Models.Book;
import com.example.Library_management.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @GetMapping("/get_book")
    public BookResponseDto getBook(@RequestParam int bookId)throws Exception{
        BookResponseDto bookResponseDto=null;
        try{
            bookResponseDto=bookService.getBook(bookId);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return bookResponseDto;
    }
    @GetMapping("/get_all")
    public List<BookResponseDto> getAllBooks(){
       return bookService.getAllBooks();
    }
    @GetMapping("/books_by_authorId")
    public List<BookResponseDto> booksByAuthorId(@RequestParam int authorId){
        return bookService.booksByAuthorId(authorId);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        String s="Book not found with given id";
        try {
          s=bookService.delete(id);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return s;
    }
}
