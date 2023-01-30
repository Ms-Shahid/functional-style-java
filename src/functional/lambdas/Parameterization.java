package functional.lambdas;

import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;

public class Parameterization {


	public static void main (String[] args) {
//		mappingLogic();
//		unaryOperator();
		biFunction();
		biConsumer();
		Supplier<Integer> randomInteger = () -> 2;

		Supplier<Integer> randomNumber = () -> {
			Random random = new Random();
			return random.nextInt(1000);
		};
		//biPredicate();
//		System.out.println(randomInteger.get());
//		System.out.println(randomNumber.get());
	}

	private static void parameterization(){
		List<Integer> numbers = List.of(1,2,3,9,5,6,8);
//		numbers.stream().filter(x -> x % 2 == 0)
//				.forEach(System.out :: println);
//
//		numbers.stream().filter(x -> x % 2 != 0)
//				.forEach(System.out :: println);

		filterAndPrint(numbers, x -> x % 2 == 0);

		filterAndPrint(numbers, x -> x % 2 != 0);

	}

	private static void filterAndPrint (List<Integer> numbers, Predicate<Integer> predicate) {
		numbers.stream().filter(predicate)
				.forEach(System.out :: println);
	}

	//Behaviour Parameterization
	private static void mappingLogic(){
		List<Integer> numbers = List.of(1,-2,3,9,5,6,8);

		extractedMap(numbers, x -> x * x); //square of numbers
		List<Integer>  cubes = extractedMap(numbers, x -> (int) Math.pow(x,3));
		extractedMap(numbers, x -> (int) Math.abs(x));
		System.out.println(cubes);
	}

	private static List<Integer> extractedMap (List<Integer> numbers, Function<Integer, Integer> mapper) {
		return numbers.stream().map(mapper).collect(Collectors.toList());
	}

	private static void unaryOperator(){
		//takes since input & returns the same type
		UnaryOperator<Integer> unaryOperator = x -> 3 * x;
		System.out.println(unaryOperator.apply(10));
	}

	//Bipredicate
	private static void biPredicate(){
		BiPredicate<Integer,String> biPredicate = (number, str) -> {
			return number > 10 && str.length() > 5;
		};
		System.out.println("Bi-Predicate " + biPredicate.test(5,"test"));
	}

	//Bifunction -> taking 2 inputs & return output of 3rd type
	private static void biFunction(){
		BiFunction<Integer,String,String> biFunction = (number, str) -> {
			return number + " " + str;
		};
		System.out.println(biFunction.apply(10,"hello"));
	}

	private static void biConsumer(){
		BiConsumer<Integer,String> biConsumer = (s1,s2) -> {
			System.out.println(s1 + " " + s2);
		};
		biConsumer.accept(15,"Hey!");
	}
}
