package functional.problemstatements;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fibonacci {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number ");

		int value = scanner.nextInt();

		//List<Integer> patterns = displayFinacciPattern(value);
		//System.out.println(patterns);
		List<Integer> fPatterns = displayFinacciPatternFunctional(value);
		System.out.println(fPatterns);
	}

	private static List<Integer> displayFinacciPatternFunctional (int value) {
		return Stream.iterate(
				new int[]{0,1}, f -> new int[]{f[1], f[0] + f[1]})
				.limit(value)
				.map(f -> f[0])
				.collect(Collectors.toList());
	}

	private static List<Integer> displayFinacciPattern (int value) {
		int firstValue = 0;
		int secondValue = 1;
		List<Integer> fibonacciValues = new ArrayList<>();
		fibonacciValues.add(firstValue);
		fibonacciValues.add(secondValue);
		for(int i = 2; i < value; i++){
			int finalValue = firstValue + secondValue;
			fibonacciValues.add(finalValue);
			firstValue = secondValue;
			secondValue = finalValue;
		}
		return fibonacciValues;
	}

}
