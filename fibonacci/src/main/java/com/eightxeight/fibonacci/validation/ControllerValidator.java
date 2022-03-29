package com.eightxeight.fibonacci.validation;

import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberLimit;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberToBlacklist;
import org.springframework.stereotype.Service;

@Service
public class ControllerValidator {

    public int validateFibonacciNumberLimit(String limit) throws InvalidFibonacciNumberLimit {
        try {
            return Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            throw new InvalidFibonacciNumberLimit();
        }
    }

    public void validateFibonacciNumberToBlacklist(int number) throws InvalidFibonacciNumberToBlacklist {
        if (!isFibonacciNumber(number)) {
            throw new InvalidFibonacciNumberToBlacklist();
        }
    }

    public boolean isPerfectSquare(int number)
    {
        int s = (int) Math.sqrt(number);
        return (s*s == number);
    }

    public boolean isFibonacciNumber(int number)
    {
        return isPerfectSquare(5*number*number + 4) ||
                isPerfectSquare(5*number*number - 4);
    }
}
