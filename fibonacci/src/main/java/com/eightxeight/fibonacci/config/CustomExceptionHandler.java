package com.eightxeight.fibonacci.config;

import com.eightxeight.fibonacci.exceptions.FibonacciNumberNotFound;
import com.eightxeight.fibonacci.exceptions.InvalidNumber;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberForBlacklist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({InvalidNumber.class})
    public ResponseEntity<?> handleInvalidNumber (InvalidNumber invalidNumber) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid number, please use a positive integer");
    }

    @ExceptionHandler({InvalidFibonacciNumberForBlacklist.class})
    public ResponseEntity<?> handleInvalidFibonacciNumberForBlacklist (InvalidFibonacciNumberForBlacklist invalidFibonacciNumberForBlacklist) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The number is not part of the Fibonacci series");
    }

    @ExceptionHandler({FibonacciNumberNotFound.class})
    public ResponseEntity<?> handleFibonacciNumberNotFound (FibonacciNumberNotFound fibonacciNumberNotFound) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
