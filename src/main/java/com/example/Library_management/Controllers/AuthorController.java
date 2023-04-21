package com.example.Library_management.Controllers;

import com.example.Library_management.DTOs.ResponseDto.AuthorResponseDto;
import com.example.Library_management.DTOs.ResponseDto.BookResponseDto;
import com.example.Library_management.Models.Author;
import com.example.Library_management.Models.Book;
import com.example.Library_management.Service.AuthorService;
import com.example.Library_management.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }
    @GetMapping("/get_author")
    public AuthorResponseDto getAuthor(@RequestParam int authorId){
        return authorService.getAuthor(authorId);
    }
    @GetMapping("get_all_authors")
    public List<AuthorResponseDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/get_books_by_id/{authorId}")
    public List<BookResponseDto> booksByAuthorId(@PathVariable int authorId){
        List<BookResponseDto> booksList=null;
        try{
            booksList=authorService.booksByAuthorId(authorId);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return booksList;
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        String s="Author not found with given id";
        try {
            s=authorService.delete(id);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return s;
    }
}
