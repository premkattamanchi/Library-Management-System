package com.example.Library_management.Converters;

import com.example.Library_management.DTOs.ResponseDto.BookResponseDto;
import com.example.Library_management.Models.Book;

public class BookToDtoConverter {
    public static BookResponseDto convert(Book book){
        BookResponseDto bookResponseDto=new BookResponseDto();
        bookResponseDto.setName(book.getName());
        bookResponseDto.setPages(book.getPages());
        bookResponseDto.setAuthorName(book.getAuthor().getName());
        return bookResponseDto;
    }
}
