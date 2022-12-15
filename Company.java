import java.util.ArrayList;

public class Company {
	String name;
	String address;
	String email;
	String number;
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	public Company(String name, String address, String email, String number) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.number = number;
	}
	
}
