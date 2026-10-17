package com.shammad.msbook.clients.google.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IndustryIdentifier(
        String type,
        String identifier
) {
}
