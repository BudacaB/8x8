package com.eightxeight.fibonacci.config;

import com.eightxeight.fibonacci.exceptions.FibonacciNumberNotFound;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberLimit;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberToBlacklist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({InvalidFibonacciNumberLimit.class})
    public ResponseEntity<?> handleInvalidFibonacciNumberLimitException (InvalidFibonacciNumberLimit invalidFibonacciNumberLimit) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid limit, please use an integer");
    }

    @ExceptionHandler({InvalidFibonacciNumberToBlacklist.class})
    public ResponseEntity<?> handleInvalidFibonacciNumberToBlacklist (InvalidFibonacciNumberToBlacklist invalidFibonacciNumberToBlacklist) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the number is not part of the Fibonacci series");
    }

    @ExceptionHandler({FibonacciNumberNotFound.class})
    public ResponseEntity<?> handleFibonacciNumberNotFound (FibonacciNumberNotFound fibonacciNumberNotFound) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
