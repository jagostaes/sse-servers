package be.jago.ssehttp1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class ServerConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CACHE_CONTROL, CacheControl.noCache().cachePrivate().mustRevalidate().getHeaderValue());
        return headers;
    }
}
