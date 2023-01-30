package functional.methodreference;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Course{
	private String name;
	private String category;
	private int score;
	private int noOfStudents;

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getCategory () {
		return category;
	}

	public void setCategory (String category) {
		this.category = category;
	}

	public int getScore () {
		return score;
	}

	public void setScore (int score) {
		this.score = score;
	}

	public int getNoOfStudents () {
		return noOfStudents;
	}

	public void setNoOfStudents (int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public Course(){}

	public Course (String name, String category, int score, int noOfStudents) {
		this.name = name;
		this.category = category;
		this.score = score;
		this.noOfStudents = noOfStudents;
	}

	@Override
	public String toString () {
		return "course{" +
				"name='" + name + '\'' +
				", category='" + category + '\'' +
				", score=" + score +
				", noOfStudents=" + noOfStudents +
				'}';
	}
}
public class CustomClass {

	public static void main (String[] args) {
		List<Course> courses = List.of(
				new Course("Spring", "Framework", 91, 2000),
				new Course("React", "library", 96, 1000),
				new Course("Spark", "Technique", 99, 2000),
				new Course("Azure", "Technology", 88, 1000),
				new Course("Ruby on Rails", "Framework", 92, 300),
				new Course("Selenium", "Framework", 91, 1000),
				new Course("Angular", "Framework", 95, 500)
		);

		//All-Match, none match, Any-Match
		Predicate<Course>reviewScoreAllMatch = course -> course.getScore() > 90;
		Predicate<Course>reviewScoreNoneMatch = course -> course.getScore() < 60;
		Predicate<Course>reviewScoreAnyMatch = course -> course.getScore() < 90;

		Predicate<Course> valuesAllMatch = student -> student.getNoOfStudents()  > 100;
		Predicate<Course> valuesNoneMatch = student -> student.getNoOfStudents() < 50;
		Predicate<Course> valuesAnyMatch = student -> student.getNoOfStudents()  <= 300;

		System.out.println(
				courses.stream().allMatch(reviewScoreAllMatch) + "\n" +//returns true if all of them match
				courses.stream().noneMatch(reviewScoreNoneMatch) + "\n" +//returns true if no element matches
				courses.stream().anyMatch(reviewScoreAnyMatch) //return true if any one matches
		);
		System.out.println("-----------------------------------");
		System.out.println(
				courses.stream().allMatch(valuesAllMatch) + "\n" +
				courses.stream().noneMatch(valuesNoneMatch) + "\n" +
				courses.stream().anyMatch(valuesAnyMatch)
		);

		Comparator<Course> comparatorByNoOfStudents = Comparator.comparingInt(Course :: getNoOfStudents);
		System.out.println(
				courses.stream().sorted(comparatorByNoOfStudents).collect(Collectors.toList()) //Increasing order
				+ "\n" +
				courses.stream().sorted(comparatorByNoOfStudents.reversed()).collect(Collectors.toList()) //reverse order
		);

		//compare noOfStudents based on review score
		Comparator<Course> compareStudentsBasedOnReviewScore = Comparator.comparingInt(Course :: getNoOfStudents)
				.thenComparingInt(Course :: getScore).reversed();
		System.out.println( "\n" + "compare Students Based OnReviewScore" + "\n" +
				courses.stream().sorted(compareStudentsBasedOnReviewScore).collect(Collectors.toList())
		);


		Comparator<Course> filterCoursesByFramework = Comparator.comparing(Course :: getCategory).thenComparing(Course :: getName).reversed();
		System.out.println("\n" + "FilterCoursesByFramework" + "\n" +
//				courses.stream().sorted(filterCoursesByFramework)
//				.collect(Collectors.toList())
				courses.stream().takeWhile(course -> course.getCategory().equalsIgnoreCase("Framework"))
						.collect(Collectors.toList())
		);

		System.out.println("\n" + "Limit to 5 course" + "\n" +
				courses.stream().sorted(filterCoursesByFramework).limit(5)
						.collect(Collectors.toList())
		);

		System.out.println("\n" + "Skips first 3 courses" + "\n" +
				courses.stream().sorted(filterCoursesByFramework).skip(3)
						.collect(Collectors.toList())
		);

		System.out.println("\n" + "Skips 2 & Limit to 5 course" + "\n" +
				courses.stream().sorted(filterCoursesByFramework).skip(2).limit(5)
						.collect(Collectors.toList())
		);

		System.out.println("\n" + "Take while" + "\n" +
				courses.stream().takeWhile(course -> course.getScore() > 90)
						.collect(Collectors.toList()) /* takeWhile(Predicate), returns until the predicate is true.*/
				+ "\n" + " Drop while " + "\n" +
				courses.stream().dropWhile(course -> course.getScore() > 90)
						.collect(Collectors.toList()) /* dropWhile(Predicate), returns until the predicate is false*/
		);

		System.out.println(
		courses.stream()
				.max(compareStudentsBasedOnReviewScore) //by default returns the last value from list
				+ "\n" + "MIN" +
		courses.stream()
				.min(compareStudentsBasedOnReviewScore) //by default returns the first value from list
		);

		System.out.println(
				courses.stream()
						.filter(reviewScoreAllMatch)
						.min(compareStudentsBasedOnReviewScore)
						.orElse(new Course("Spring","Framework",91, 2000))
				/* Here, it results in optional type, if the min matches the condition then it returns that value
				* else by default instead of empty or null value it returns new course object that is defined*/
		);

		System.out.println(
				courses.stream().filter(reviewScoreAllMatch).findFirst() // returns the first element that matchs the condition else returns empty
				+ "\n" +
				courses.stream().filter(reviewScoreAllMatch).findAny() //returns the element that matches the condition
		);

		System.out.println(
				courses.stream().filter(reviewScoreAllMatch).mapToInt(Course::getNoOfStudents).sum()
				+ "\n" +
				courses.stream().filter(reviewScoreAllMatch).mapToInt(Course :: getNoOfStudents).average()
				+ "\n" +
				courses.stream().filter(reviewScoreAllMatch).mapToInt(Course :: getNoOfStudents).count()
				+ "\n" +
				courses.stream().filter(reviewScoreAllMatch).mapToInt(Course :: getNoOfStudents).max()
		);

		System.out.println("grouping based on certain creatiria"
			+ "\n" +
			courses.stream().collect(Collectors.groupingBy(Course :: getCategory))
			+ "\n" +
			courses.stream().collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())) //count the category
			+ "\n" + "Highest review course" +
			courses.stream().collect(Collectors.groupingBy(Course::getCategory,
					Collectors.maxBy(Comparator.comparing(Course :: getScore))))
			+ "\n" + "Collect only the course name by highest score" +
			courses.stream().collect(Collectors.groupingBy(Course::getCategory,
					Collectors.mapping(Course::getName, Collectors.toList())))
		);

	}
}
