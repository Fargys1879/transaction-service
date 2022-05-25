package ru.fargys1879.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.http.HttpHeaders;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse<T> {
    private int responseCode;
    private HttpStatus status;
    private HttpHeaders headers;
    private URI uri;
    private T body;
}
