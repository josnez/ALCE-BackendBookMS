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
    Book getBook(@PathVariable String id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No se encontró ningún libro"
                ));
    }

    //traer los libros de un usuario
    @GetMapping("/books/{idOwner}")
    List<Book> getBooks(@PathVariable Integer idOwner){
        List<Book> booksUser = bookRepository.findByIdOwner(idOwner);
        return booksUser;
    }

    //traer todos los libros
    @GetMapping("/getAllBooks")
    List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //traer todos los libros de todos los usuarios excepto los que estan en estado de intercambio o solicitados, filtrados por nombre
    @GetMapping("/books/getAllBooksAvailable/{tittle}")
    List<Book> getAllBooksTittle(@PathVariable String tittle){
        List<Book> booksTittle = bookRepository.findByTitle(tittle);

        List<Book> booksTittleFilter = new ArrayList<>();
        for (Book book : booksTittle){
            if (book.isRequested() == false) {
                booksTittleFilter.add(book);
                // break;
            }
        }
        return booksTittleFilter;
    }

    //traer todos los libros de todos los usuarios excepto los que estan en estado de intercambio o solicitados, filtrados por genero
    @GetMapping("/books/getAllBooksAvailable/{genre}")
    List<Book> getAllBooksGenre(@PathVariable String genre){
        List<Book> booksGenre = bookRepository.findByGenre(genre);

        List<Book> booksGenreFilter = new ArrayList<>();
        for (Book book : booksGenre){
            if (book.isRequested() == false) {
                booksGenreFilter.add(book);
                // break;
            }
        }
        return booksGenreFilter;
    }

    //traer todos los libros de todos los usuarios excepto los que estan en estado de intercambio o solicitados, filtrados por editorial
    @GetMapping("/books/getAllBooksAvailable/{editorial}")
    List<Book> getAllBookEditorial(@PathVariable String editorial){
        List<Book> booksEditorial = bookRepository.findByEditorial(editorial);

        List<Book> booksEditorialFilter = new ArrayList<>();
        for (Book book : booksEditorial){
            if (book.isRequested() == false) {
                booksEditorialFilter.add(book);
                // break;
            }
        }
        return booksEditorialFilter;
    }

    //traer todos los libros de todos los usuarios excepto los que estan en estado de intercambio o solicitados, filtrados por autor
    @GetMapping("/books/getAllBooksAvailable/{author}")
    List<Book> getAllBooksAuthor(@PathVariable String author){
        List<Book> booksAuthor = bookRepository.findByAuthor(author);

        List<Book> booksAuthorFilter = new ArrayList<>();
        for (Book book : booksAuthor){
            if (book.isRequested() == false) {
                booksAuthorFilter.add(book);
                // break;
            }
        }
        return booksAuthorFilter;
    }

    //crear un nuevo libro
    @PostMapping("/book")
    Book newBook(@RequestBody Book book) {
        return  bookRepository.save(book);
    }

    //editar la información de un libro
    @PutMapping("/book/{id}")
    Book updateBook (@PathVariable String id, @RequestBody Book bookDetails) {
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("No se encontró ningún libro"));
        return bookRepository.save(bookDetails);
    }

    //eliminar un libro
    @DeleteMapping("/book/{id}")
    ResponseEntity<?> deleteBook(@PathVariable String id){
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
