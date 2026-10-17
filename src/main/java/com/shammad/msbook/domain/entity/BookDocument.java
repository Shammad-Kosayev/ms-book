package com.shammad.msbook.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "books")
public class BookDocument {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private String isbn10;

    @Indexed(unique = true, sparse = true)
    private String isbn13;

    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private int pageCount;
    private List<String> categories;
    private String thumbnailUrl;

    private Instant createdAt = Instant.now();

}
