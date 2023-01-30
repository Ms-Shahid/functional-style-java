package functional.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MappingFunction {

	public static void main (String[] args) {

		User sarah = new User("Shahid");
		User james = new User("James");
		User mary = new User("Mary");
		User John = new User("John");

		List<User> userList = List.of(sarah,james,mary,John);
		List<String> names = new ArrayList<>();

		//takes an object of type user & give the type string
		Function<User, String> toName = (User user) -> user.getName();
		for(User user: userList){
			String name = toName.apply(user); //invoking
			names.add(name);
		}

		/*
		Consumer<functional.lambdas.User> newList = (functional.lambdas.User user) -> System.out.println(user);
		userList.forEach(newList);
		 */
		userList.forEach(user -> System.out.println(user)); //type user
		names.forEach(name -> System.out.println(name));    //type string

		Comparator<User> cmpName = Comparator.comparing(user -> user.getName());
		Comparator<User> cmpAge = Comparator.comparing(user -> user.getAge());
		Comparator<User> comparator = cmpName.thenComparing(cmpAge);
		Comparator<User> reversed = comparator.reversed();

		userList.sort(comparator);
		userList.forEach(user -> System.out.println(user));

		userList.sort(reversed);
		userList.forEach(user -> System.out.println("Reverse " + user));

	}
}
