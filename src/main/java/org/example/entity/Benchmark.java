package org.example.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Benchmark {
    public static Map<Integer, Long> getTimes(Consumer<Integer> func){
        Map<Integer, Long> result = new HashMap<Integer, Long>();
        for (int num = 1; num < 20; num += 1) {
            Long timeStart = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                func.accept(num);
                result.put(num, System.currentTimeMillis() - timeStart);
            }
        }
        return result;
    }
}
