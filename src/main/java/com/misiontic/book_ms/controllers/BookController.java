package com.misiontic.book_ms.controllers;

import com.misiontic.book_ms.exceptions.BookNotFoundException;
import com.misiontic.book_ms.models.Book;
import com.misiontic.book_ms.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books/{idOwner}")
    List<Book> getBook(@PathVariable Integer idOwner){
        return bookRepository.findByIdOwner(idOwner)
                .orElseThrow(() -> new BookNotFoundException("No se encontro ningun libro asociado al usuario"
                    ));
    }
}
