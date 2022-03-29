package com.eightxeight.fibonacci.validation;

import com.eightxeight.fibonacci.exceptions.InvalidNumber;
import com.eightxeight.fibonacci.exceptions.InvalidFibonacciNumberForBlacklist;
import org.springframework.stereotype.Service;

@Service
public class ControllerValidator {

    public int validateNumber(String limit) throws InvalidNumber {
        try {
            int parsedInt = Integer.parseInt(limit);
            if (parsedInt < 0) {
                throw new InvalidNumber();
            }
            return parsedInt;
        } catch (NumberFormatException e) {
            throw new InvalidNumber();
        }
    }

    public void validateFibonacciNumberForBlacklist(int number) throws InvalidFibonacciNumberForBlacklist {
        if (!isFibonacciNumber(number)) {
            throw new InvalidFibonacciNumberForBlacklist();
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
