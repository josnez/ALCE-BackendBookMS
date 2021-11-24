package com.misiontic.book_ms.controllers;

import com.misiontic.book_ms.exceptions.BookNotFoundException;
import com.misiontic.book_ms.models.Book;
import com.misiontic.book_ms.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/{id}")
    Book getBook(@PathVariable Integer id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No se encontró ningún libro"
                ));
    }

    @GetMapping("/books/{idOwner}")
    List<Book> getBooks(@PathVariable Integer idOwner){
        List<Book> booksUser = bookRepository.findByIdOwner(idOwner);
        return booksUser;
    }

    @PostMapping("/book")
    Book newBook(@RequestBody Book book) {
        return  bookRepository.save(book);
    }

    @PutMapping("/book/{id}")
    Book updateBook (@PathVariable Integer id, @RequestBody Book bookDetails) {
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("No se encontró ningún libro"));
        return bookRepository.save(bookDetails);
    }

    @DeleteMapping("/book/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Integer id){
        Book book =bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("No se encontró ningún libro"));
        bookRepository.delete(book);
        return new ResponseEntity<>("El libro fue eliminado", HttpStatus.OK);
    }
}