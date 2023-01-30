package functional.lambdas;

public class User {

	private String name;
	private int age;

	public int getAge () {
		return age;
	}

	public void setAge (int age) {
		this.age = age;
	}

	@Override
	public String toString () {
		return "functional.lambdas.User{" +
				"name='" + name + '\'' +
				'}';
	}

	public User (String name) {
		this.name = name;
	}

	public String getName () {
		return name;
	}

	public void setName (String name, int age) {
		this.name = name;
		this.age = age;
	}
}
