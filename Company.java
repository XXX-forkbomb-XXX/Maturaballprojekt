import java.util.ArrayList;
import java.util.Scanner;

public class Company {
	private String name;
	private String address;
	private String email;				//19.12
	private String number;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public Company() {

	}
	
	public Company(String name, String address, String email, String number) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.number = number;
	}
	
	public static Company createCompany() {
		
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Daten der Firma: \nName: ");
		String name = s.nextLine();
		System.out.printf("Addresse: ");
		String address = s.nextLine();
		System.out.printf("Email: ");
		String email = s.nextLine();
		System.out.printf("Nummer: ");
		String number = s.nextLine();
		Company company = new Company(name, address, email, number);
		return company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public String toString() {
		return(name + ";" + address + ";" + email + ";" + number);
	}
}