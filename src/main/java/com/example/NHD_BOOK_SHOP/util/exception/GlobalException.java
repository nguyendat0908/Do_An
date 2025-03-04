package com.example.NHD_BOOK_SHOP.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.NHD_BOOK_SHOP.domain.dto.response.RestResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = { IdInvalidException.class })
    public ResponseEntity<RestResponse<Object>> handleIdInvalidException(Exception e) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(e.getMessage());
        res.setMessage("Exception occurs...");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = { NoResourceFoundException.class })
    public ResponseEntity<RestResponse<Object>> handleNotFoundException(Exception e) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setError(e.getMessage());
        res.setMessage("404 not found. URL may not exist...");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
}
