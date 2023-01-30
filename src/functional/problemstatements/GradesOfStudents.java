package functional.problemstatements;

import java.util.Scanner;
import java.util.function.Function;

public class GradesOfStudents {

	public static void main (String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter score of Maths ");
		int maths = scanner.nextInt();

		System.out.println("Enter score of Science ");
		int science = scanner.nextInt();

		System.out.println("Enter score of English ");
		int english = scanner.nextInt();

		Function<Integer, Function<Integer, Function<Integer, Double>>>
				averageCalculator = a -> b -> c -> (a + b + c) / 3.0;

		double average  = averageCalculator.apply(maths).apply(science).apply(english);

		System.out.println("Average is " + average);

		Function<Double, Character> gradeAssigner =
				avg -> {
					 if( avg >= 90 ) return 'A';
					 if( avg >= 70 ) return 'B';
					 if( avg >= 50 ) return 'C';
					 else return 'D';
				};
		Character grade = gradeAssigner.apply(average);
		System.out.println("Grade received is " + grade);
	}
}
