package functional.intro;

import java.util.List;

public class StructuredWayOfProgramming {

	public static void main (String[] args) {
		//printAllNumbersListStructured(List.of(12,12,23,45,67,34));
		printAllEvenNumbersListStructured(List.of(12,12,23,45,67,34));
		System.out.println("-------------------------------");
		printAllTheCourses(List.of("Spring", "Spring Boot", "API" , "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes"));
	}

	private static void printAllTheCourses (List<String> courses) {
		for(String course : courses){
			//if(course.startsWith("Spring")) //Print Courses Containing the word "Spring"
			if(course.length() >= 4) { //Print Courses Whose Name has atleast 4 letters
				System.out.println(course);
			}
		}
	}

	private static void printAllNumbersListStructured (List<Integer> numbers) {
		//How to do in structured approach
		for(int number : numbers){
			System.out.println(number);
		}
	}
	private static void printAllEvenNumbersListStructured (List<Integer> numbers) {
		for(int num : numbers){
			if(num % 2 == 0)
				System.out.println(num);
		}
	}
}
