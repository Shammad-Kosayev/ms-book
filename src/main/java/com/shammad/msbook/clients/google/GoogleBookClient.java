package com.shammad.msbook.clients.google;

import com.shammad.msbook.clients.google.error_decoder.GoogleErrorDecoder;
import com.shammad.msbook.clients.google.response.BookItem;
import com.shammad.msbook.clients.google.response.GoogleBooksResponse;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "googleBooksClient",
        url = "${clients.google-book}",
        configuration = GoogleBookClient.GoogleBookClientConfig.class
)
public interface GoogleBookClient {

    @GetMapping("/volumes")
    GoogleBooksResponse searchBooks(@RequestParam("q") String query,
                                    @RequestParam(value = "startIndex", required = false) Integer startIndex,
                                    @RequestParam(value = "maxResults", required = false) Integer maxResults
    );

    @GetMapping("/volumes/{volumeId}")
    BookItem getBookById(@PathVariable("volumeId") String volumeId);

    class GoogleBookClientConfig {

        @Bean
        public RequestInterceptor googleBookRequestInterceptor(@Value("${secrets.google-api-key}") String apiKey) {
            return template -> {
                if (apiKey != null && !apiKey.trim().isEmpty()) {
                    template.query("key", apiKey);
                }
            };
        }

        @Bean
        public Retryer googleBookRetryer() {
            return new Retryer.Default(100, 2000, 3);
        }

        @Bean
        public ErrorDecoder googleBookErrorDecoder() {
            return new GoogleErrorDecoder();
        }
    }
}
