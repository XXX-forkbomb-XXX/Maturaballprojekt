import java.util.ArrayList;

public class User {
	String firstname;
	String surname;
	String passwort;
	ArrayList<SponsorEntry> sponsorEntries = new ArrayList<SponsorEntry>();
	ArrayList<Task> taks = new ArrayList<Task>();
	
	public User(String firstname, String surname) {
		this.firstname = firstname;
		this.surname = surname;
	}	

}
