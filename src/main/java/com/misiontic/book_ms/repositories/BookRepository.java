package com.misiontic.book_ms.repositories;

import com.misiontic.book_ms.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>{
    List<Book> findByIdOwner(int idOwner);
    List<Book> findByIdAplicant(int idAplicant);
    List<Book> findByTitle (String title);
    List<Book> findByAuthor (String author);
    List<Book> findByGenre (String genre);
    List<Book> findByEditorial (String editorial);
}
