package com.example.demo.controllers;

import com.example.demo.exception.*;
import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("Book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/allBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Book>> getBook(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

    @GetMapping(value = "/getBook/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Book>> getOneBook(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookRepository.findById(id));
    }

    @PostMapping("/safeBook")
    public String safeBook(@RequestBody Book book){
        bookRepository.save(book);
        return "Add book";
    }

    @PutMapping("editBook/{id}")
    public Book editBook(@RequestBody Book freshBook, @PathVariable Integer id){
        return bookRepository.findById(id)
                .map(h -> {
                    h.setTitle(freshBook.getTitle());
                    h.setDescription(freshBook.getDescription());
                    h.setAuthor(freshBook.getAuthor());
                    h.setYear(freshBook.getYear());
                    return bookRepository.save(h);
                }).orElseThrow(() -> new BookNotFoundException(id));
    }


    @DeleteMapping("deleteBook/{id}")
    public void deleteBook(@PathVariable Integer id){
        if(bookRepository.existsById(id))
            bookRepository.deleteById(id);
        else throw new BookNotFoundException(id);
    }
}
