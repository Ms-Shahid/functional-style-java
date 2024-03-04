package org.paumard.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author José
 */
public class ChainConsumers {

    public static void main(String... args) {
        
        List<String> strings = 
                Arrays.asList("one", "two", "three", "four", "five", "six", "Seven", "eight");
        
        List<String> result = new ArrayList<>();
        
        Consumer<String> c1 = System.out::println; //method reference
        Consumer<String> c2 = result::add;
        
        strings.forEach(c1.andThen(c2));
        System.out.println("size of result = " + result.size());
    }
}
