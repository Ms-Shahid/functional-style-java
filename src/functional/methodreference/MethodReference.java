package functional.methodreference;

import java.util.List;
import java.util.function.Supplier;

public class MethodReference {

	public static void main (String[] args) {
		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
		displayCourses(courses);
	}

	private static void displayCourses(List<String> courses){
		courses.stream()
				//.map(str -> str.toUpperCase())
				.map(String :: toUpperCase) //method reference of non-static(instance methods) methods as well
				.forEach(System.out :: println);

		Supplier<String> supplier = String :: new; //constructor reference
	}


}
