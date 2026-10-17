package com.shammad.msbook.clients.google.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GoogleBooksResponse(
        int totalItems,
        List<BookItem> items
) {
}
