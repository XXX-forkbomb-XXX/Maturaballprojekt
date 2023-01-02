import java.util.ArrayList;
import java.util.Scanner;



public class Project {
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<SponsorEntry> sponsorEntries = new ArrayList<SponsorEntry>();
	private static ArrayList<Task> tasks = new ArrayList<Task>();
	private static ArrayList<Task> companyTasks = new ArrayList<Task>();
	private static User currentUser;
	
	public static User searchUser() {
		Scanner scan = new Scanner(System.in);
		User tmp = new User();
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
	
	public static SponsorEntry searchSponsorEntry(User u) {
		Scanner scan = new Scanner(System.in);
		SponsorEntry tmp = new SponsorEntry();
		boolean sponsorEntryFound = false;
		do {
			System.out.println("Geben Sie die Sponsoreintrag-Id ein\nEingabe:");
			int id = scan.nextInt();
			for(int i = 0; i < sponsorEntries.size(); i++) {
				if(sponsorEntries.get(i).getId() == id) {
					tmp = sponsorEntries.get(i);
					sponsorEntryFound = true;
				}
			}
			if(!sponsorEntryFound) {
				System.out.println("Sponsoreintrag wurde nicht gefunden");
			}
		}while(!sponsorEntryFound);	
		return tmp;
	}
	
	public static Task searchTask() {
		Scanner scan = new Scanner(System.in);
		Task tmp = new Task();
		boolean taskFound = false;
		do {
			System.out.println("Geben Sie die Task-Id ein\nEingabe:");
			int id = scan.nextInt();
			for(int i = 0; i < tasks.size(); i++) {
				if(tasks.get(i).getId() == id) {
					tmp = tasks.get(i);
					taskFound = true;
				}
			}
			if(!taskFound) {
				System.out.println("Aufgabe wurde nicht gefunden");
			}
		}while(!taskFound);	
		return tmp;
	}
	
	public static Task searchTask(User u) {
		Scanner scan = new Scanner(System.in);
		Task tmp = new Task();
		boolean taskFound = false;
		do {
			System.out.println("Geben Sie die Task-Id ein\nEingabe:");
			int id = scan.nextInt();
			for(int i = 0; i < tasks.size(); i++) {
				if(tasks.get(i).getId() == id) {
					tmp = tasks.get(i);
					taskFound = true;
				}
			}
			if(!taskFound) {
				System.out.println("Aufgabe wurde nicht gefunden");
			}
		}while(!taskFound);	
		return tmp;
	}
	
	public static Task searchCompanyTask() {
		Scanner scan = new Scanner(System.in);
		Task tmp = new Task();
		boolean taskFound = false;
		do {
			System.out.println("Geben Sie die Task-Id ein\nEingabe:");
			int id = scan.nextInt();
			for(int i = 0; i < companyTasks.size(); i++) {
				if(companyTasks.get(i).getId() == id) {
					tmp = companyTasks.get(i);
					taskFound = true;
				}
			}
			if(!taskFound) {
				System.out.println("Firmenbezogene Aufgabe wurde nicht gefunden");
			}
		}while(!taskFound);	
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
		User tmp = new User();
		boolean correct = false;
		do{
			tmp = searchUser();
			correct = checkPassword(tmp);
		}while(!correct);
		System.out.println("\nWillkommen " + tmp.getUsername());
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
	
	public static void printAllCompanyTasks() {
		for(int i = 0; i < companyTasks.size(); i++) {
			companyTasks.get(i).printTask();
		}
	}
	
	public static float calculateAlreadyPaidCosts(){
		float sum = 0; 
		for(int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getCosts().isAlreadyPaid()){
				sum += tasks.get(i).getCosts().getAmount();
			}
		}
		for(int i = 0; i < companyTasks.size(); i++) {
			if(tasks.get(i).getCosts().isAlreadyPaid()){
				sum += companyTasks.get(i).getCosts().getAmount();
			}
		}
		return sum;
	}
	
	public static float calculateUpcomingCosts(){
		float sum = 0; 
		for(int i = 0; i < tasks.size(); i++) {
			if(!tasks.get(i).getCosts().isAlreadyPaid()){
				sum += tasks.get(i).getCosts().getAmount();
			}
		}
		for(int i = 0; i < companyTasks.size(); i++) {
			if(!tasks.get(i).getCosts().isAlreadyPaid()){
				sum += companyTasks.get(i).getCosts().getAmount();
			}
		}
		return sum;
	}
	
	public static float calculateIncome(){
		float sum = 0; 
		for(int i = 0; i < sponsorEntries.size(); i++) {
			sum += sponsorEntries.get(i).getAmount();
		}
		return sum;
	}
	
	public static float calculateTotal() {
		return calculateIncome() - calculateUpcomingCosts() - calculateAlreadyPaidCosts();
	}
	
	public static void printProject() {
		System.out.println("Bereitsbezahlte Kosten: " + calculateAlreadyPaidCosts() + "\n"
				+ "Bevorstehende Kosten: " + calculateAlreadyPaidCosts() + "\n"
				+ "Einnahmen: " + calculateIncome() + "\n\n"
				+ "Gesamt: " + calculateTotal() + "\n");
	}
	
	public static void main (String []args) {
		Scanner scan = new Scanner(System.in);
		Admin mosjula = new Admin("Julian", "Moser", "mosjula", "8362");
		Admin grualea = new Admin("Alex", "Gruber", "grualea", "83f32");
		
		
		users.add(mosjula);
		users.add(grualea);
		
		while(true) {
			currentUser = logIn();
			int auswahl = 1;
			while(auswahl != 0 && currentUser != null) { //21.12
				printProject();
				System.out.println("Was moechten Sie tun?\n"
						+ "\t1) Meine Aufgaben ausgeben\n"
						+ "\t2) Meine Sponsoreintraege ausgeben\n"	//19.12
						+ "\t3) Sponsoreintrag erstellen\n" 			//19.12
						+ "\t4) Sponsoreintrag loeschen\n"
						+ "\t5) Passwort zuruecksetzen\n"
						+ "\t6) Firmenbezogene Aufgaben ausgeben"); //21.12
				if (currentUser instanceof Admin) {
					System.out.println(""
							+ "\t11) Alle Benutzer ausgeben\n"
							+ "\t12) Benutzer erstellen\n"
							+ "\t13) Benutzer loeschen\n"
							+ "\t14) Aufgaben eines Benutzers ausgeben\n"
							+ "\t15) Aufgaben aller Benutzer ausgeben\n"
							+ "\t16) Aufgabe erstellen\n"
							+ "\t17) Aufgabe loeschen\n"
							+ "\t18) Firmenbezogene Aufgabe erstellen\n"
							+ "\t19) Firmenbezogene Aufgabe loeschen");
				}
				System.out.println("\t0) Ausloggen\nEingabe: ");
				auswahl = scan.nextInt();
				switch(auswahl) {
					case 1: currentUser.printTasks(); break;
					case 2: currentUser.printSponsorEntries(); break;		//
					case 3: currentUser.addSponsorEntry(); break;		//
					case 4: currentUser.deleteSponsorEntry(); break;
					case 5: currentUser.resetPassword(); break; //21.12
					case 6: printAllCompanyTasks(); break;
				}
				if(currentUser instanceof Admin) {
					switch(auswahl) {
						case 11: printUsers(); break;
						case 12: ((Admin) currentUser).creatUser(); break;
						case 13: ((Admin) currentUser).deleteUser(); break;
						case 14: searchUser().printTasks(); break;
						case 15: printAllTasks(); break;
						case 16: ((Admin) currentUser).giveTask(); break;
						case 17: ((Admin) currentUser).deleteTask(); break;
						case 18: ((Admin) currentUser).addCompanyTask(); break;
						case 19: ((Admin) currentUser).deleteCompanyTask(); break;
					}
				}
				System.out.println();
			}
		}
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static void setUsers(ArrayList<User> users) {
		Project.users = users;
	}

	public static ArrayList<SponsorEntry> getSponsorEntries() {
		return sponsorEntries;
	}

	public static void setSponsorEntries(ArrayList<SponsorEntry> sponsorEntries) {
		Project.sponsorEntries = sponsorEntries;
	}

	public static ArrayList<Task> getTasks() {
		return tasks;
	}

	public static void setTasks(ArrayList<Task> tasks) {
		Project.tasks = tasks;
	}

	public static ArrayList<Task> getCompanyTasks() {
		return companyTasks;
	}

	public static void setCompanyTasks(ArrayList<Task> companyTask) {
		Project.companyTasks = companyTask;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		Project.currentUser = currentUser;
	}
	
	
}
