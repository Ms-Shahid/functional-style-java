package functional.intro;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalWayOfProgramming {

	//functional programming is a paradigm shift -> that defines of what to do instead of how to do.
	//The operations which returns Stream are called intermediate operation eg : sorted, distinct, map, filter
	//The operations which specific type are called terminal operations eg: forEach, reduce
	public static void main (String[] args) {

		//printAllNumbersListFunctionalApproach(List.of(12,12,23,45,67,34));
		List<Integer> numbers = List.of(12, 12, 23, 45, 67, 34);
		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

		printAllEvenNumbersListFunctionalApproach(numbers);

		printAllOddNumberListFunctionalApproach(numbers);

		printAllCourses(courses);

		printAllSquaresOfEvenNumbersListFunctionalApproach(numbers);

		printAllSquaresOfNumbersListFunctionalApproach(numbers);

		int sum = printSumOfNumbers(numbers);
		System.out.println(sum);
		returnMaxValueFromList(numbers);

		printAllDistinctNumbers(numbers);

		printSortedNumbers(numbers);

		displayNamesInReverseOrder(courses);

		displayNameByLengthOfCourse(courses);

		List<Integer> collectValues = collectAndStoreIntoAnotherList(numbers);
		System.out.println(collectValues);

		List<Integer> collectEvenValues = collectAndStoreEvenValueIntoList(numbers);
		collectEvenValues.forEach(System.out :: println);

		List<String> collectLength = collectLengthOfCourseTitles(courses);
		collectLength.forEach(System.out :: println);
	}

	private static List<String> collectLengthOfCourseTitles (List<String> courses) {
		System.out.println("Collect length of courses");
		return courses.stream().map( word -> word + " " +word.length()).collect(Collectors.toList());
	}

	private static List<Integer> collectAndStoreEvenValueIntoList (List<Integer> numbers) {
		System.out.println("Collect even values");
		return numbers.stream().filter(even -> even % 2 == 0).collect(Collectors.toList());
	}

	private static List<Integer> collectAndStoreIntoAnotherList (List<Integer> numbers) {
		System.out.println("---------Collects the values in another list-------");
		return numbers.stream().map(number -> (int)Math.pow(number,2)).collect(Collectors.toList());
	}

	private static void displayNameByLengthOfCourse (List<String> courses) {
		System.out.println("......Display by length of course name.....");
		courses.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);
//		courses.stream().map(word -> word + " " + word.length()).forEach(System.out :: println);
	}

	private static void displayNamesInReverseOrder (List<String> courses) {
		System.out.println("......In Reverse Order.....");
		courses.stream().sorted(Comparator.reverseOrder()).forEach(System.out :: println);
	}

	private static void printSortedNumbers (List<Integer> numbers) {
		System.out.println("----------Sorted list----------");
		numbers.stream().sorted().forEach(System.out :: println);
	}

	private static void printAllDistinctNumbers (List<Integer> numbers) {
		//unique numbers in list
		System.out.println("----------Unique values----------");
		numbers.stream().distinct().forEach(System.out :: println);
	}

	private static void returnMaxValueFromList (List<Integer> numbers) {
		System.out.println("----------Return Max Value----------");
//		numbers.stream().reduce(Integer.MIN_VALUE, (x,y) -> x>y?x:y); //set minimum value to avoid return ZERO, in case of negative values
		numbers.stream().max(Integer :: compare).orElse(Integer.MIN_VALUE);
	}
	private static void returnSumOfSquares (List<Integer> numbers) {
		numbers.stream().map(x -> Math.pow(x,2)).reduce((double) 0, (x, y) -> x + y);
	}

	private static int printSumOfNumbers (List<Integer> numbers) {
		//Reduce the stream of numbers to one single value such as sum, max, average etc..
//		return numbers.stream().reduce(0,FunctionalWayOfProgramming :: sum);
//		return numbers.stream().reduce(0, (aggregate,nextNumber) -> aggregate + nextNumber);
		return numbers.stream().reduce(0, Integer::sum); //Built in method
	}

	private static int sum (int aggregate, int nextNumber) {
		return aggregate + nextNumber;
	}

	private static void printAllSquaresOfNumbersListFunctionalApproach (List<Integer> numbers) {
		System.out.println("----------Squares of numbers----------");
		 numbers.stream().mapToInt(square -> (int) Math.pow(square,2)).reduce(Integer::sum);
	}

	private static void printAllSquaresOfEvenNumbersListFunctionalApproach (List<Integer> numbers) {
		System.out.println("----------Squares of even numbers----------");
//		numbers.stream().filter(even -> even % 2 == 0).map(square -> square * square).forEach(System.out :: println);
		numbers.stream().filter(getPredicate()).map(getFunction(3)).forEach(getPrintln());
	}

	private static Consumer<Double> getPrintln () {
		return System.out::println;
	}

	private static Function<Integer, Double> getFunction (int b) {
		return cube -> Math.pow(cube, b);
	}

	private static Predicate<Integer> getPredicate () {
		return odd -> odd % 2 == 1;
	}

	private static void printAllOddNumberListFunctionalApproach (List<Integer> numbers) {
		System.out.println("----------Squares of even numbers----------");
		numbers.stream().filter(number -> number % 2 != 0)
				.forEach(System.out :: println);
	}

	//private static void print(int number){
	//System.out.print(number + " ");
	//}

	private static void printAllNumbersListFunctionalApproach (List<Integer> numbers) {
		//what to do in functional approach
		//convert the list of numbers into streams of numbers (individual numbers), then we define what to do with this numbers

		//numbers.stream().forEach(FunctionalWayOfProgramming :: print); //method reference
		numbers.stream().forEach(System.out :: println); //build in print & println method

	}
	private static void printAllEvenNumbersListFunctionalApproach (List<Integer> numbers) {
		System.out.println("----------Even numbers----------");
//		numbers.stream().filter(FunctionalWayOfProgramming :: isEven).forEach(System.out :: println);
		numbers.stream()
				.filter(number -> number % 2 == 0)
				.forEach(System.out :: println);
	}
	private static void printAllCourses (List<String> courses) {
		System.out.println("----------courses----------");
//		course.stream().forEach(System.out :: println);
//		course.stream().filter(match -> match.contains("Spring")) //auto match the type
//				.forEach(System.out :: println);
		//course.stream().filter(word -> word.length() >= 4).forEach(System.out :: println); //Print Courses Whose Name has atleast 4 letters
		courses.stream().map(course -> course + " " + course.length()).forEach(System.out :: println); //Print the number of characters in each course name
	}

//	private static boolean isEven (Integer number) {
//		return number % 2 == 0;
//	}

	private static void implementationOfReduceMethod(List<Integer> numbers){
		numbers.stream().reduce(0, getSum);

	}

	private static BinaryOperator<Integer> getSum = new BinaryOperator<Integer>() {
		@Override
		public Integer apply (Integer integer, Integer integer2) {
			return integer + integer2;
		}
	};
	//These abstract method in functional interface, are called as function descriptor.


}
