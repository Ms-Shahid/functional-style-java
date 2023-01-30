package functional.lambdas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class ChainLambdas {
	public static void main (String[] args) {

		Consumer<String> c1 = s -> System.out.println("C1 consumes " + s);
		Consumer<String> c2 = s -> System.out.println("C2 consumes " + s);

		/*
		Consumer<String> c3 = s -> {
			c1.accept(s);
			c2.accept(s);
		};
		*/
		//Instead of above we can use default method of consumer
		Consumer<String> c3 = c1.andThen(c2);
		c3.accept("Accept this Hello ");

		Predicate<String> isNUll = s -> s == null;
		System.out.println("For Null = " + isNUll.test(null)); //true
		System.out.println("For Hello = " + isNUll.test("Hello")); //false

		Predicate<String> isEmpty = s -> s.isEmpty();
		System.out.println("For empty = " + isEmpty.test(""));
		System.out.println("For Hello = " + isEmpty.test("Hello"));

		Predicate<String> compare = isNUll.negate().and(isEmpty.negate());
		System.out.println("For Null = " + compare.test(null));
		System.out.println("For empty = " + compare.test(""));

		List<String> stringList = Arrays.asList("one", "two", "three", "four", "five",
											"six", "seven", "eight", "nine");
		Comparator<String> cmp = (s1,s2) -> s1.compareTo(s2);
		stringList.sort(cmp);
		System.out.println("Natural Sort order " + stringList);

		ToIntFunction<String> toLength = s -> s.length();

		//Comparator<String> cmp2 = (s1,s2) -> Integer.compare(s1.length(), s2.length());
		//instead of above we can make use of default method of Function
		Comparator<String> cmp2 = Comparator.comparingInt(toLength);
		stringList.sort(cmp2);
		System.out.println("Compare based on length " +stringList);
	}
}
