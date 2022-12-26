package vn.edu.clevai.quiztest.filter.random;

import java.util.Collections;
import java.util.List;

public class RandomAndGet {
    public static List<?> random(List<?> input, int limit){
        Collections.shuffle(input);

        return input.subList(0,limit);
    }
}
