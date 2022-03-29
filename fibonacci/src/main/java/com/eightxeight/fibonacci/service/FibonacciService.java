package com.eightxeight.fibonacci.service;

import com.eightxeight.fibonacci.data.BlacklistedRepository;
import com.eightxeight.fibonacci.exceptions.FibonacciNumberNotFound;
import com.eightxeight.fibonacci.model.BlacklistedNumberEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

@Service
public class FibonacciService implements IFibonacciService {

    @Autowired
    BlacklistedRepository blacklistedRepository;

    @Override
    public int getFibonacciNumber(int limit) {
        int[] series = getFibonacciSequenceWithoutBlacklisted(limit);
        int rnd = new Random().nextInt(series.length);
        return series[rnd];
    }

    @Override
    public int[] getFibonacciSegment(int limit) {
        return getFibonacciSequenceWithoutBlacklisted(limit);
    }

    @Override
    public BlacklistedNumberEntity blacklistNumber(int number) {
        BlacklistedNumberEntity blacklistedNumberEntity = new BlacklistedNumberEntity();
        blacklistedNumberEntity.setNumber(number);
        return blacklistedRepository.save(blacklistedNumberEntity);
    }

    @Override
    public void removeNumberFromBlacklist(int number) throws FibonacciNumberNotFound {
        try {
            blacklistedRepository.deleteById(number);
        } catch (Exception e) {
            throw new FibonacciNumberNotFound();
        }
    }

    int[] getFibonacciSequence(int limit) {
        if (limit == 0) {
            return new int[1];
        } else if (limit == 1) {
            int[] series = new int[2];
            series[1] = 1;
            return series;
        }

        int[] series = new int[limit];
        series[0] = 0;
        series[1] = 1;

        for (int i = 2; i < limit; i++) {
            series[i] = series[i - 1] + series[i - 2];
        }
        return series;
    }

    int[] getFibonacciSequenceWithoutBlacklisted(int limit) {
        List<BlacklistedNumberEntity> blacklisted = blacklistedRepository.findAll();
        int[] series = getFibonacciSequence(limit + blacklisted.size());
        for (BlacklistedNumberEntity blacklistedNumberEntity : blacklisted) {
            series = ArrayUtils.removeElement(series, blacklistedNumberEntity.getNumber());
        }
        return series;
    }
}
