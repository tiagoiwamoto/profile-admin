package br.com.tiagoiwamoto.profileadmin.entrypoint;

import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ResourceErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handlerBindingResult(MethodArgumentNotValidException e, WebRequest request){
        log.error("Payload com erro ou mal formatado");
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(String.format("campo: %s, erro: %s", error.getField(), error.getDefaultMessage())));
        return ResponseEntity.badRequest().body(ErrorResponseDto.of(errors, request.getParameterMap(), request.getHeaderNames()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponseDto> handlerFile(BindException e, WebRequest request){
        log.error("Payload com erro ou mal formatado");
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(String.format("campo: %s, erro: %s", error.getField(), error.getDefaultMessage())));
        return ResponseEntity.badRequest().body(ErrorResponseDto.of(errors, request.getParameterMap(), request.getHeaderNames()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorResponseDto> handlerGeneric(MissingServletRequestPartException e, WebRequest request){
        log.error("Payload com erro ou mal formatado");
        List<String> errors = new ArrayList<>();
        errors.add(String.format("campo: %s, erro: %s", e.getRequestPartName(), "campo necessário"));
        return ResponseEntity.badRequest().body(ErrorResponseDto.of(errors, request.getParameterMap(), request.getHeaderNames()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handlerGeneric(Exception e, WebRequest request){
        log.error("Payload com erro ou mal formatado");
        List<String> errors = new ArrayList<>();
        errors.add(String.format("erro: %s", e.getMessage()));
        return ResponseEntity.badRequest().body(ErrorResponseDto.of(errors, request.getParameterMap(), request.getHeaderNames()));
    }
}
