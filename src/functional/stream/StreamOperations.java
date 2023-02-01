package functional.stream;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamOperations {

	public static void main (String[] args) {

		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

		//the values here would be converted to wrapper class objects. return type is ReferencePipeline
		Stream.of(12,9,13,12,6,8,15).reduce(0, Integer :: sum);

		//Stream of primitive type. return type would be IntPipeline
		int[] numberArray = {12,9,13,12,6,8,15};
		Arrays.stream(numberArray).sum();

		//Stream of static values of int
		IntStream.range(1,10).sum(); //range from 1-9
		IntStream.rangeClosed(1,10).sum(); //range from 1-10

		//Stream of dynamic values of int
		IntStream.iterate(1, e -> e+2).limit(10).peek(System.out :: println).sum();
		IntStream.iterate(2, e -> e + 2).limit(10).peek(System.out :: println).sum();

		//first 10 squares of 2
		IntStream.iterate(2, s -> s*2).limit(10).peek(System.out :: println).sum();

		//Collect the primitive operation into a list
		IntStream.iterate(2, e->e*2).limit(10).boxed().collect(Collectors.toList());

		//StreamOperations.bigNumberOperation();
		//StreamOperations.stringFlatMap();
		StreamOperations.formingTuplesHavingSameLength(courses);
	}

	private static void bigNumberOperation(){
		System.out.println(
				new StringBuffer("Big Number Operations").append(IntStream.rangeClosed(1,50).reduce(1, (x,y) -> x *y))
						.append("Long Stream").append(LongStream.rangeClosed(1,10).reduce(1,(x,y)-> x*y))
		);
		System.out.println(
				new StringBuffer("Big Integer multiple").append(LongStream.rangeClosed(1,50).mapToObj(BigInteger :: valueOf)
						.reduce(BigInteger:: multiply))
		);
	}

	private static void stringFlatMap(){
		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

		System.out.println(new StringBuffer("Collect the list of elements into single string with delimiter \n")
				.append(courses.stream().collect(Collectors.joining(" ")))

				.append("\n" + "display the number of chars preset in each list of string\n ")
				//.append(courses.stream().map(course -> course.split("")).collect(Collectors.toList())) //this produces stream of strings

				.append(courses.stream().map(course -> course.split("")).flatMap(Arrays :: stream).
						collect(Collectors.toList()))//Flatmap : each element of Stream replaced with content of Mapped stream

				.append(courses.stream().map(course -> course.split("")).flatMap(Arrays :: stream).distinct().
						collect(Collectors.toList()))

		);
	}

	private static void formingTuplesHavingSameLength(List<String> courses){
		List<String> duplicateCourses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

		//Task : match this 2 list of courses & create a tuple having same length of course names! such as ["API", "AWS"], ["PCF","AWS"]..
		System.out.println(
				courses.stream().flatMap(course -> duplicateCourses.stream().map(duplicateCourse -> List.of(course, duplicateCourse))).
						collect(Collectors.toList())//results in all the combinations. each element of list1 join with list2
				+ "\n" +
				courses.stream().flatMap(course -> duplicateCourses.stream().map(duplicateCourse -> List.of(course, duplicateCourse)))
						.filter(list -> !list.get(0).equals(list.get(1))).collect(Collectors.toList())
				//filter out the courses names containing the same names such as ["Spring", "Spring"]..

				+ "\n" +
				courses.stream().flatMap(course -> duplicateCourses.stream()
						.filter(duplicateCourse -> duplicateCourse.length() == course.length())
						.map(duplicateCourse -> List.of(course,duplicateCourse))
						.filter(list -> !list.get(0).equals(list.get(1))))
						.collect(Collectors.toList())

		);

	}


}
