package com.shammad.msbook.mapper;

import com.shammad.msbook.clients.google.response.BookItem;
import com.shammad.msbook.clients.google.response.IndustryIdentifier;
import com.shammad.msbook.clients.google.response.VolumeInfo;
import com.shammad.msbook.domain.entity.BookDocument;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDocument toBookDocument(BookItem googleBook) {
        BookDocument book = new BookDocument();
        book.setId(googleBook.id());

        VolumeInfo volumeInfo = googleBook.volumeInfo();
        if (volumeInfo != null) {
            book.setTitle(volumeInfo.title());
            book.setAuthors(volumeInfo.authors());
            book.setPublisher(volumeInfo.publisher());
            book.setPublishedDate(volumeInfo.publishedDate());
            book.setDescription(volumeInfo.description());
            book.setPageCount(volumeInfo.pageCount());

            if (volumeInfo.categories() != null && !volumeInfo.categories().isEmpty()) {
                book.setCategories(volumeInfo.categories());
            }

            if (volumeInfo.industryIdentifiers() != null) {
                for (IndustryIdentifier id : volumeInfo.industryIdentifiers()) {
                    if ("ISBN_10".equals(id.type())) {
                        book.setIsbn10(id.identifier());
                    } else if ("ISBN_13".equals(id.type())) {
                        book.setIsbn13(id.identifier());
                    }
                }
            }

            if (volumeInfo.imageLinks() != null) {
                book.setThumbnailUrl(volumeInfo.imageLinks().thumbnail());
            }
        }

        return book;
    }
}
