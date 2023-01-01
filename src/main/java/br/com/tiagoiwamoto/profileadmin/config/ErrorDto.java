package br.com.tiagoiwamoto.profileadmin.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    private List<String> messages;
    private String message;
    private LocalDateTime timestamp;
    private String httpCodeDescription;

    public ErrorDto(List<String> messages, String httpCodeDescription) {
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
        this.httpCodeDescription = httpCodeDescription;
    }

    public ErrorDto(String message, String httpCodeDescription) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.httpCodeDescription = httpCodeDescription;
    }
}
