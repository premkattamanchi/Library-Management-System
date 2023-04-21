package com.example.Library_management.Converters;

import com.example.Library_management.DTOs.ResponseDto.AuthorResponseDto;
import com.example.Library_management.Models.Author;

public class AuthorToDtoConverter {
    public static AuthorResponseDto convert(Author author){
        AuthorResponseDto authorResponseDto=new AuthorResponseDto();
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setName(author.getName());
        authorResponseDto.setRating(author.getRating());
        return authorResponseDto;
    }
}
