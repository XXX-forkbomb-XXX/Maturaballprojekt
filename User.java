import java.util.ArrayList;
import java.util.Scanner;		//19.12

public class User {
	private String firstname;
	private String surname;
	private String username;
	private String password;
	private int id;
	public static int counterId = 0; 
	private ArrayList<SponsorEntry> sponsorEntries = new ArrayList<SponsorEntry>();
	public ArrayList<Task> tasks = new ArrayList<Task>();
	
	public User() {
		
	}
	
	public User(String firstname, String surname, String username, String password) {
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.id = counterId;
		counterId++;
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
	
	public void printTasks() {
		for(int i = 0; i < tasks.size(); i++) {
			tasks.get(i).printTask();
		}
	}
	
	public void addSponsorEntry() {
		Scanner s = new Scanner(System.in);
		System.out.println("Gib Name, Adresse, Email, Nummer des Unternehmens, sowie den Betrag ein");
		SponsorEntry se = new SponsorEntry(SponsorEntry.counterId, new Company (s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine()), s.nextFloat());
		sponsorEntries.add(se);
		Project.sponsorEntries.add(se);
		SponsorEntry.counterId++;
	}
	
	public void deleteSponsorEntry() {
		User u = Project.searchUser();
		SponsorEntry se = Project.searchSponsorEntry(u);
		for(int i = 0; i < Project.sponsorEntries.size(); i++) {
			if(Project.sponsorEntries.get(i).getId() == se.getId()) {
				se = Project.sponsorEntries.get(i);
				Project.sponsorEntries.set(i, null);
				Project.sponsorEntries.remove(i);
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
	
	public void printSponsorEntries() {			//19.12
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

}
