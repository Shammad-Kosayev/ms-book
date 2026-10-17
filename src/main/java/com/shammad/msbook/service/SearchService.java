package com.shammad.msbook.service;

import com.shammad.msbook.clients.google.GoogleBookClient;
import com.shammad.msbook.clients.google.response.BookItem;
import com.shammad.msbook.clients.google.response.GoogleBooksResponse;
import com.shammad.msbook.domain.entity.BookDocument;
import com.shammad.msbook.domain.repository.BookRepository;
import com.shammad.msbook.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GoogleBookClient googleBookClient;

    public GoogleBooksResponse searchBooks(String query, int pageSize, int pageNumber) {
        int decrementedPageNumber = pageNumber - 1;
        return googleBookClient.searchBooks(
                query,
                decrementedPageNumber * pageSize,
                pageSize
        );
    }

    public BookDocument findById(String id) {
        Optional<BookDocument> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }

        BookItem googleBook = googleBookClient.getBookById(id);
        return bookRepository.save(bookMapper.toBookDocument(googleBook));
    }

    public BookDocument findByIsbn(String isbn) {
        String cleanIsbn = isbn.replaceAll("[^0-9X]", "");
        if (isbn.length() == 13) {
            return bookRepository.findByIsbn13(cleanIsbn)
                    .orElseGet(() -> fetchFromGoogleByIsbnAndSave(cleanIsbn));
        }
        if (isbn.length() == 10) {
            return bookRepository.findByIsbn10(cleanIsbn)
                    .orElseGet(() -> fetchFromGoogleByIsbnAndSave(cleanIsbn));
        } else {
            throw new RuntimeException("Invalid ISBN. Must be 13 or 10 digits");
        }
    }

    private BookDocument fetchFromGoogleByIsbnAndSave(String isbn) {
        GoogleBooksResponse book = googleBookClient.searchBooks(
                "isbn:%s".formatted(isbn),
                null,
                null
        );

        if (book == null || book.items() == null || book.items().isEmpty()) {
            throw new RuntimeException("No books found for isbn " + isbn);
        }

        BookDocument bookDocument = bookMapper.toBookDocument(book.items().getFirst());
        return bookRepository.save(bookDocument);
    }
}
