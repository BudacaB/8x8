package com.eightxeight.fibonacci.service;

import com.eightxeight.fibonacci.exceptions.FibonacciNumberNotFound;
import com.eightxeight.fibonacci.model.BlacklistedNumberEntity;

public interface IFibonacciService {

    int getFibonacciNumber(int limit);
    int[] getFibonacciSegment(int limit);
    BlacklistedNumberEntity blacklistNumber(int number);
    void removeNumberFromBlacklist(int number) throws FibonacciNumberNotFound;
}
