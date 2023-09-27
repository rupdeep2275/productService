package com.example.productservice.controllers;

import com.example.productservice.dtos.ErrorResponseDto;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
