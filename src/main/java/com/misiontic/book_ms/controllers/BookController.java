package com.misiontic.book_ms.controllers;

import com.misiontic.book_ms.exceptions.BookNotFoundException;
import com.misiontic.book_ms.models.Book;
import com.misiontic.book_ms.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    String checkMessage(){
        return "Everything went well, backend to the air baby";
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

    //traer todos los libros
    @GetMapping("/getAllBooks")
    List<Book> getAllBooks(){

        return getAllBooks();
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

    //traer los libros en los que está interesado el usuario
    @GetMapping("/books/allRequested/{idAplicant}")
    List<Book> getBooksApplicant(@PathVariable int idAplicant){
        List<Book> booksUser = bookRepository.findByIdAplicant(idAplicant);

        List<Book> bookUserFilter = new ArrayList<>();
        for (Book book : booksUser) {
                if (book.isRequested() == true) {
                    bookUserFilter.add(book);
                    // break;
            }
        }
        return bookUserFilter;
    }

}
