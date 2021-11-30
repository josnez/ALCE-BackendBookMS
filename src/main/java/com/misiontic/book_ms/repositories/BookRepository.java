package com.misiontic.book_ms.repositories;

import com.misiontic.book_ms.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String>{
    List<Book> findByIdOwner(int idOwner);

}

