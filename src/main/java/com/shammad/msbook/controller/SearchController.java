package com.shammad.msbook.controller;

import com.shammad.msbook.clients.google.response.GoogleBooksResponse;
import com.shammad.msbook.domain.entity.BookDocument;
import com.shammad.msbook.service.SearchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public GoogleBooksResponse searchBooks(
            @NotBlank(message = "Search query cannot be empty or null")
            @RequestParam(value = "query")
            String query,

            @Min(1)
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1")
            int pageNumber,

            @Min(1) @Max(20)
            @RequestParam(value = "pageSize", required = false, defaultValue = "10")
            int pageSize
    ) {
        return searchService.searchBooks(query, pageSize, pageNumber);
    }

    @GetMapping("/{id}")
    public BookDocument getBookById(@PathVariable @NotBlank String id) {
        return searchService.findById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDocument getBookByIsbn(@PathVariable @NotBlank String isbn) {
        return searchService.findByIsbn(isbn);
    }
}
