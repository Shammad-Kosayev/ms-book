package com.shammad.msbook.domain.repository;

import com.shammad.msbook.domain.entity.BookDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<BookDocument, String> {

    Optional<BookDocument> findByIsbn13(String isbn13);
    Optional<BookDocument> findByIsbn10(String isbn10);

}
