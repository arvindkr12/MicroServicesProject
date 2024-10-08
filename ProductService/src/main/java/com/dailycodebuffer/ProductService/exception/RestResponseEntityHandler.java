package com.dailycodebuffer.ProductService.exception;

import com.dailycodebuffer.ProductService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException ex){
//        ErrorResponse errorResponse=ErrorResponse.builder()
//                .errorMessage(ex.getMessage())
//                .errorCode(ex.getErrorCode())
//                .build();

        return new ResponseEntity<>(new ErrorResponse().builder()
            .errorMessage(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build(), HttpStatus.NOT_FOUND
    );
    }
}
