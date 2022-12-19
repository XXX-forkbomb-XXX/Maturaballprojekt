import java.util.ArrayList;
import java.util.Scanner;

public class Company {
	private String name;
	private String address;
	private String email;				//19.12
	private String number;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public Company(String name, String address, String email, String number) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.number = number;
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
	
	public Company createCompany() { //19.12
		Company c = null;
		
		Scanner s = new Scanner(System.in);
		System.out.println("Daten der Firma:");
		System.out.printf("Name: ");
		c.setName(s.nextLine());
		System.out.printf("\nAddresse: ");
		c.setAddress(s.nextLine());
		System.out.println("\nEmail: ");
		c.setEmail(s.nextLine());
		System.out.printf("\nNummer: ");
		c.setNumber(s.nextLine());
		
		return c;
	}
	
}
