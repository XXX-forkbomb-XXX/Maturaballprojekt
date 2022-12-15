import java.util.ArrayList;
import java.util.Scanner;



public class Project {
	private float income;
	private float spending;
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<SponsorEntry> sponsorEntries = new ArrayList<SponsorEntry>();
	public static ArrayList<Task> tasks = new ArrayList<Task>();
	private static User currentUser;
	
	public static User searchUser() {
		Scanner scan = new Scanner(System.in);
		User tmp = new User("", "", "", "", 0);
		boolean userFound = false;
		do {
			System.out.println("Geben Sie den Benutzernamen ein\nEingabe:");
			String username = scan.nextLine();
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getUsername().equals(username)) {
					tmp = users.get(i);
					userFound = true;
				}
			}
			if(!userFound) {
				System.out.println("Benutzername wurde nicht gefunden");
			}
		}while(!userFound);	
		return tmp;
	}
	
	public static boolean checkPassword(User tmp) {
		Scanner scan = new Scanner(System.in);
		boolean correct = false;
		String password = "";
		for(int i = 0; i < 5 & !correct & !password.equals("exit"); i++) {
			System.out.println("Geben Sie das Passwort ein\nEingabe:");
			password = scan.nextLine();
			if(tmp.getPassword().equals(password)) {
				correct = true;
			}else if (password.equals("exit")){
				System.out.println("");
			}else {
				System.out.println("Falsche Eingabe\nVersuche uebrig: " + (5-i-1) + "\n");
			}
		}
		return correct;
	}
	
	public static User logIn() {
		Scanner scan = new Scanner(System.in);
		User tmp = new User("", "", "", "", 0);
		boolean correct = false;
		do{
			tmp = searchUser();
			correct = checkPassword(tmp);
		}while(!correct);
		System.out.println("Willkommen " + tmp.getUsername());
		return tmp;
	}
	
	public static void printUsers(){
		System.out.println("");
		for (int i = 0; i <users.size(); i++) {
			users.get(i).printUser();
		}
	}
	
	public static void printAllTasks() {
		for(int i = 0; i < tasks.size(); i++) {
			tasks.get(i).printTask();
		}
	}
	
	public static void main (String []args) {
		Scanner scan = new Scanner(System.in);
		Admin mosjula = new Admin("Julian", "Moser", "mosjula", "8362", User.id);
		User grualea = new User("Alex", "Gruber", "grualea", "83f32", User.id);
		
		
		users.add(mosjula);
		users.add(grualea);
		
		while(true) {
			currentUser = logIn();
			
			if (currentUser instanceof Admin) {
				System.out.println("Du bist ein Admin");
			}else if (currentUser instanceof User) {
				System.out.println("Du bist ein User");			
			}
			int auswahl = 1;
			while(auswahl != 0) {
				System.out.println("Was moechten Sie tun\n"
						+ "\t1) \n"
						+ "\t2) \n"
						+ "\t3) \n"
						+ "\t4) ");
				if (currentUser instanceof Admin) {
					System.out.println(""
							+ "\t11) Alle Benutzer ausgeben\n"
							+ "\t12) Benutzer erstellen\n"
							+ "\t13) Benutzer loeschen\n"
							+ "\t14) Aufgabe geben\n"
							+ "\t15) Aufgaben eines Benutzers ausgeben\n"
							+ "\t16) Aufgaben aller Benutzer ausgeben");
				}
				System.out.println("\t0) Ausloggen\nEingabe: ");
				auswahl = scan.nextInt();
				switch(auswahl) {
					case 11: printUsers(); break;
					case 12: ((Admin) currentUser).creatUser(); break;
					case 13: ((Admin) currentUser).deleteUser(searchUser()); break;
					case 14: ((Admin) currentUser).giveTask(searchUser()); break;
					case 15: searchUser().printTask(); break;
					case 16: printAllTasks(); break;
				}
			}
		}
	}
}
