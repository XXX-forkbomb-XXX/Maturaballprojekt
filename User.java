import java.util.ArrayList;
import java.util.Scanner;		//19.12

public class User {
	private String firstname;
	private String surname;
	private String username;
	private String password;
	private int id;
	private static int counterId = 0; 
	private ArrayList<SponsorEntry> sponsorEntries = new ArrayList<SponsorEntry>();
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public User() {
		this.id = counterId;
		counterId++;
	}
	
	public User(String firstname, String surname, String username, String password) {
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.id = counterId;
		counterId++;
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
	
	public void printTasks() {
		for(int i = 0; i < tasks.size(); i++) {
			tasks.get(i).printTask();
		}
	}
	
	public void addSponsorEntry() {
		SponsorEntry se = new SponsorEntry();
		se.createSponsorEntry();
		sponsorEntries.add(se);
		Project.getSponsorEntries().add(se);
	}
	
	public void deleteSponsorEntry() {
		User u = Project.searchUser();
		SponsorEntry se = Project.searchSponsorEntry(u);
		for(int i = 0; i < Project.getSponsorEntries().size(); i++) {
			if(Project.getSponsorEntries().get(i).getId() == se.getId()) {
				se = Project.getSponsorEntries().get(i);
				Project.getSponsorEntries().set(i, null);
				Project.getSponsorEntries().remove(i);
			}
		}
		for(int i = 0; i < this.sponsorEntries.size(); i++) {
			if(this.sponsorEntries.get(i) == se) {
				this.sponsorEntries.set(i, null);
				this.sponsorEntries.remove(i);
			}
		}
		System.out.println("Sponsoreintrag wurde geloescht");
	}
	
	public void printSponsorEntries() {			
		for(int i = 0; i < sponsorEntries.size(); i++){
			sponsorEntries.get(i).printSponsorEntry();
		}
	}
	
	public void resetPassword() { // 21.12
		String password,passwordA, passwordW;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Altes Passwort\nEingabe: ");
			passwordA = scan.nextLine();
			if(!passwordA.equals(this.getPassword())) {
				System.out.println("Falsches Passwort");
			}
		}while(!passwordA.equals(this.getPassword()));
		do {
			System.out.println("Neues Passwort\nEingabe: ");
			password = scan.nextLine();
			System.out.println("Neues Passwort bestätigen\nEingabe: ");
			passwordW = scan.nextLine();
			if(!password.equals(passwordW)) {
				System.out.println("Passwoerter stimmen nicht ueberein");
			}
		}while(!password.equals(passwordW));
		this.setPassword(password);
		System.out.println("Passwort wurde zurueckgesetzt");
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


	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<SponsorEntry> getSponsorEntries() { //21.12
		return sponsorEntries;
	}

	public void setSponsorEntries(ArrayList<SponsorEntry> sponsorEntries) { //21.12
		this.sponsorEntries = sponsorEntries;
	}

	public static int getCounterId() {
		return counterId;
	}

	public static void setCounterId(int counterId) {
		User.counterId = counterId;
	}
	
	

}
