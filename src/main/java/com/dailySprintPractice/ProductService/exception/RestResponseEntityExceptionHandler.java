package com.dailySprintPractice.ProductService.exception;

import com.dailySprintPractice.ProductService.ProductServiceApplication;
import com.dailySprintPractice.ProductService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceException productServiceException){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .message(productServiceException.getMessage())
                .errorCode(productServiceException.getErrorCode()).build(), HttpStatus.NOT_FOUND);
    }
}
