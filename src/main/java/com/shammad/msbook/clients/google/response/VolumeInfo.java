package com.shammad.msbook.clients.google.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VolumeInfo(
        String title,
        List<String> authors,
        String publisher,
        String publishedDate,
        String description,
        int pageCount,
        List<String> categories,
        List<IndustryIdentifier> industryIdentifiers,
        ImageLinks imageLinks
) {
}
