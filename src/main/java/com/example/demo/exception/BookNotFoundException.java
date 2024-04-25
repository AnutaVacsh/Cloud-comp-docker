package com.example.demo.exception;

import com.example.demo.models.Book;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Integer id){
        super("Could not found book "+ id);
    }
}