package functional.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
	public static void main (String[] args) {

		//supplier interface just get the value & return a T
		Supplier<String> supplier = () -> "Hello!";
		String getValue = supplier.get(); //to invoke the lambda
		System.out.println("String = " + getValue);

		//consumer interface just accept a value of T & not return null
		Consumer<String> consumer = (String s) -> {
				System.out.println(s);
			};
			consumer.accept("Hello");

		List<String> numbers = new ArrayList<>(List.of("one","two","three","four","five"));
		/*
		Predicate<String> filter = string -> !string.startsWith("t"); //doing flitering
		numbers.removeIf((filter));
		Consumer<String> action = string -> System.out.println(string); //print
		numbers.forEach(action);
		*/
		numbers.removeIf(string1 -> !string1.startsWith("t"));
		numbers.forEach(string -> System.out.println(string));


	}

}