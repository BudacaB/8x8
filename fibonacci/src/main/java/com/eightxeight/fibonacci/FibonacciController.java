package com.eightxeight.fibonacci;

import com.eightxeight.fibonacci.exceptions.FibonacciNumberNotFound;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberLimit;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberToBlacklist;
import com.eightxeight.fibonacci.service.IFibonacciService;
import com.eightxeight.fibonacci.validation.ControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fibonacci/v1/api/")
public class FibonacciController {

    @Autowired
    IFibonacciService fibonacciService;

    @Autowired
    ControllerValidator controllerValidator;

    @GetMapping("number")
    int getFibonacciNumber(@RequestParam String limit) throws InvalidFibonacciNumberLimit {
        int validatedLimit = controllerValidator.validateFibonacciNumberLimit(limit);
        return fibonacciService.getFibonacciNumber(validatedLimit);
    }

    @GetMapping("segment")
    int[] getFibonacciSegment(@RequestParam String limit) throws InvalidFibonacciNumberLimit {
        int validatedLimit = controllerValidator.validateFibonacciNumberLimit(limit);
        return fibonacciService.getFibonacciSegment(validatedLimit);
    }

    @PostMapping("blacklist")
    int blacklistFibonacciNumber(@RequestParam String number) throws InvalidFibonacciNumberLimit, InvalidFibonacciNumberToBlacklist {
        int validatedNumberToBlacklist = controllerValidator.validateFibonacciNumberLimit(number);
        controllerValidator.validateFibonacciNumberToBlacklist(validatedNumberToBlacklist);
        return fibonacciService.blacklistNumber(validatedNumberToBlacklist).getNumber();
    }

    @DeleteMapping("blacklist")
    ResponseEntity<?> removeFibonacciNumberFromBlacklist(@RequestParam String number) throws InvalidFibonacciNumberLimit, InvalidFibonacciNumberToBlacklist, FibonacciNumberNotFound {
        int validatedNumberToRemoveFromBlacklist = controllerValidator.validateFibonacciNumberLimit(number);
        controllerValidator.validateFibonacciNumberToBlacklist(validatedNumberToRemoveFromBlacklist);
        fibonacciService.removeNumberFromBlacklist(validatedNumberToRemoveFromBlacklist);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
