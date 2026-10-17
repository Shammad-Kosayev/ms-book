package com.shammad.msbook.clients.google.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ImageLinks(
        String smallThumbnail,
        String thumbnail
) {
}
