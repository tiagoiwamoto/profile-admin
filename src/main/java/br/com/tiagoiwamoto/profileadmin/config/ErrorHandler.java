package br.com.tiagoiwamoto.profileadmin.config;


import br.com.tiagoiwamoto.profileadmin.core.exceptions.ImageRemoveException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.ImageStoreException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordRecoveryException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordRemoveException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordSaveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handlerBindingResult(MethodArgumentNotValidException ex, WebRequest request){
        log.error("Payload com erro ou mal formatado");
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(String.format("campo: %s, erro: %s", error.getField(), error.getDefaultMessage())));
        return ResponseEntity.badRequest().body(new ErrorDto(errors, HttpStatus.BAD_REQUEST.getReasonPhrase()));
    }
    @ExceptionHandler(ImageRemoveException.class)
    public ResponseEntity<ErrorDto> handleImageRemoveException(ImageRemoveException ex, WebRequest request){
        log.error(String.format("payload recebido %s", request));
        return ResponseEntity
                .internalServerError()
                .body(new ErrorDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }

    @ExceptionHandler(ImageStoreException.class)
    public ResponseEntity<ErrorDto> handleImageStoreException(ImageStoreException ex, WebRequest request){
        log.error(String.format("payload recebido %s", request));
        return ResponseEntity
                .internalServerError()
                .body(new ErrorDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorDto> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request){
        log.error(String.format("payload recebido %s", request));
        return ResponseEntity
                .ok()
                .body(new ErrorDto(ex.getMessage(), HttpStatus.OK.getReasonPhrase()));
    }
    @ExceptionHandler(RecordRecoveryException.class)
    public ResponseEntity<ErrorDto> handleRecordRecoveryException(RecordRecoveryException ex, WebRequest request){
        log.error(String.format("payload recebido %s", request));
        return ResponseEntity
                .internalServerError()
                .body(new ErrorDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
    @ExceptionHandler(RecordRemoveException.class)
    public ResponseEntity<ErrorDto> handleRecordRemoveException(RecordRemoveException ex, WebRequest request){
        log.error(String.format("payload recebido %s", request));
        return ResponseEntity
                .internalServerError()
                .body(new ErrorDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
    @ExceptionHandler(RecordSaveException.class)
    public ResponseEntity<ErrorDto> handleRecordSaveException(RecordSaveException ex, WebRequest request){
        log.error(String.format("payload recebido %s", request));
        return ResponseEntity
                .internalServerError()
                .body(new ErrorDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }

}
