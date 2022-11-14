package org.example.entity;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private static final Map<Integer, Long> cache = new HashMap<Integer, Long>();

    public Fibonacci() {
        cache.put(0, 0L);
        cache.put(1, 1L);
    }


    public long getFibonacci(int num) {
        if (num == 0) {
            return 0L;
        }
        if (num == 1) {
            return 1L;
        } else {
            return getFibonacci(num - 1) + getFibonacci(num - 2);
        }
    }

    public Long getCachedFibonacci(int num) {
        if (cache.containsKey(num)) {
            return cache.get(num);
        }
        Long result = getCachedFibonacci(num - 1) + getCachedFibonacci(num - 2);
        cache.put(num, result);
        return getFibonacci(num - 1) + getFibonacci(num - 2);
        
    }
}
