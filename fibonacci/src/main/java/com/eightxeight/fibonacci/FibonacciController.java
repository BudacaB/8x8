package com.eightxeight.fibonacci;

import com.eightxeight.fibonacci.exceptions.FibonacciNumberNotFound;
import com.eightxeight.fibonacci.exceptions.InvalidNumber;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberForBlacklist;
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
    int getFibonacciNumber(@RequestParam String limit) throws InvalidNumber {
        int validatedLimit = controllerValidator.validateNumber(limit);
        return fibonacciService.getFibonacciNumber(validatedLimit);
    }

    @GetMapping("segment")
    int[] getFibonacciSegment(@RequestParam String limit) throws InvalidNumber {
        int validatedLimit = controllerValidator.validateNumber(limit);
        return fibonacciService.getFibonacciSegment(validatedLimit);
    }

    @PostMapping("blacklist")
    int blacklistFibonacciNumber(@RequestParam String number) throws InvalidNumber, InvalidFibonacciNumberForBlacklist {
        int validatedNumberToBlacklist = controllerValidator.validateNumber(number);
        controllerValidator.validateFibonacciNumberForBlacklist(validatedNumberToBlacklist);
        return fibonacciService.blacklistNumber(validatedNumberToBlacklist).getNumber();
    }

    @DeleteMapping("blacklist")
    ResponseEntity<?> removeFibonacciNumberFromBlacklist(@RequestParam String number) throws InvalidNumber, InvalidFibonacciNumberForBlacklist, FibonacciNumberNotFound {
        int validatedNumberToRemoveFromBlacklist = controllerValidator.validateNumber(number);
        controllerValidator.validateFibonacciNumberForBlacklist(validatedNumberToRemoveFromBlacklist);
        fibonacciService.removeNumberFromBlacklist(validatedNumberToRemoveFromBlacklist);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
