package com.example.Library_management.Service;

import com.example.Library_management.Converters.AuthorToDtoConverter;
import com.example.Library_management.Converters.BookToDtoConverter;
import com.example.Library_management.DTOs.ResponseDto.AuthorResponseDto;
import com.example.Library_management.DTOs.ResponseDto.BookResponseDto;
import com.example.Library_management.Models.Author;
import com.example.Library_management.Models.Book;
import com.example.Library_management.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author){
        authorRepository.save(author);
        return "Author added";
    }
    public AuthorResponseDto getAuthor(int id){
        Author author= authorRepository.findById(id).get();
        AuthorResponseDto authorResponseDto=new AuthorResponseDto();
        authorResponseDto= AuthorToDtoConverter.convert(author);
        return authorResponseDto;
    }
    public List<AuthorResponseDto> getAllAuthors(){
        List<AuthorResponseDto> dtoList=new ArrayList<>();
        List<Author> authorList=authorRepository.findAll();
        for(Author author:authorList){
            AuthorResponseDto authorResponseDto= AuthorToDtoConverter.convert(author);
            dtoList.add(authorResponseDto);
        }
        return dtoList;
    }
    public String delete(int id){
        authorRepository.deleteById(id);
        return "Author deleted";
    }
    public List<BookResponseDto> booksByAuthorId(int id) throws Exception{
        List<BookResponseDto> responseDtoList=new ArrayList<>();
        Author author=authorRepository.findById(id).get();
        List<Book> booksList=author.getBooksWritten();
        for(Book book:booksList){
           BookResponseDto bookResponseDto= BookToDtoConverter.convert(book);
           responseDtoList.add(bookResponseDto);
        }
        return responseDtoList;
    }
}
