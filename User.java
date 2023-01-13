import java.util.ArrayList;
import java.util.Scanner;		
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String firstname;
	private String surname;
	private String username;
	private String password;
	private int id;
	private static int counterId = 1000; 
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
				+ "Name: " + this.getFirstname() + " " + this.getSurname() + "\n");
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
		if(u.getId() == 0) {
			return;
		}
		SponsorEntry se = Project.searchSponsorEntry(u);
		if(se.getId() == 0) {
			return;
		}
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
	
	public boolean controlRegex(String password) {
	    String PASSWORD_PATTERN =
	            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
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
			do {
				System.out.println("Neues Passwort\nEingabe: ");
				password = scan.nextLine();
				if(!controlRegex(password)) {
					System.out.println("Das Passwort stimmt nicht mit folgender Regex ueberein:\n"
							+ "\tDas Passwort muss mindestens eine Zahl enthalten\n"
							+ "\tDas Passwort muss mindestens einen Großbuchstaben, sowie eine Kleinbuchstaben enthalten\n"
							+ "\tDas Passwort muss ein Sonderzeichen enthalten"
							+ "\tDas Passwort muss eine Laenge von 8 bis 20 Zeichen besitzen");
				}
			}while(!controlRegex(password));
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

	public String toString() {
		return(firstname + ";" + surname + ";" + username + ";" + password + ";" + id);
	}

}