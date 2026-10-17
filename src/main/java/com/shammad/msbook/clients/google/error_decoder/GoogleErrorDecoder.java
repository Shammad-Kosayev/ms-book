package com.shammad.msbook.clients.google.error_decoder;

import com.shammad.msbook.exception.model.TooManyRequestsException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class GoogleErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        // If Google returns 503 (Service Unavailable) or 504 (Gateway Timeout)
        if (response.status() == 503 || response.status() == 504) {
            log.error("Google returned 503 or 504. Retrying");
            try {
                log.error(Arrays.toString(response.body().asInputStream().readAllBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new RetryableException(
                    response.status(),
                    "External API temporary failure: " + response.status(),
                    response.request().httpMethod(),
                    (Long) null,
                    response.request()
            );
        }

        if (response.status() == 429) {
            return new TooManyRequestsException("Too many requests returned from Google");
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
