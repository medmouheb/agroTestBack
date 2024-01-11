package com.agrotech.api.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.agrotech.api.exceptions.CSVReaderException;
import com.agrotech.api.exceptions.EmptyFileException;
import com.agrotech.api.exceptions.NotFoundException;

import java.io.IOException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        logger.error(e.getMessage());
        return new ResponseEntity<>("errors.not-found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<?> handleEmptyFileException(EmptyFileException e){
        logger.error(e.getMessage());
        return new ResponseEntity<>("errors.empty-csv-file", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CSVReaderException.class)
    public ResponseEntity<?> handleCSVReaderException(CSVReaderException e){
        logger.error(e.getMessage());
        return new ResponseEntity<>("errors.failed-csv-file", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        boolean required = ex.getBindingResult().getFieldErrors().stream()
                .anyMatch(fieldError -> Objects.equals(fieldError.getCode(), "NotBlank"));

        if(required){
            return new ResponseEntity<>("errors.required", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("errors.invalid", HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException e){
        logger.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>("errors.server-error", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        logger.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>("errors.server-error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
