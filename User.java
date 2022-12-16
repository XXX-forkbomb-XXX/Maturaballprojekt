import java.util.ArrayList;

public class User {
	private String firstname;
	private String surname;
	private String username;
	private String password;
	private int id;
	public static int counterID = 1000; 
	private ArrayList<SponsorEntry> sponsorEntries = new ArrayList<SponsorEntry>();
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public User(String firstname, String surname, String username, String password, int id) {
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.id = id;
	}	
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void printUser() {
		System.out.println("ID: " + this.getId() + "\n"
				+ "Benutzername: " + this.getUsername() + "\n"
				+ "Name: " + this.getFirstname() + " " + this.getSurname() + "\n"
				+ "");
	}

	public void addTask(Task t) {
		tasks.add(t);
	}
	
	public void printTask() {
		for(int i = 0; i < tasks.size(); i++) {
			tasks.get(i).printTask();
		}
	}

}
