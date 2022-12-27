package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto implements Serializable {

    private static final long serialVersionUID = -3698694731737841525L;

    private List<String> messages;
    private LocalDateTime timestamp;
    private Object payload;
    private Object headers;

    private ErrorResponseDto(List<String> messages, Object payload, Object headers) {
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
        this.payload = payload;
        this.headers = headers;
    }

    public static ErrorResponseDto of(List<String> messages, Object payload, Object headers){
        return new ErrorResponseDto(messages, payload, headers);
    }
}
