import java.util.Scanner;

public class Admin extends User{

	public Admin(String firstname, String surname, String username, String password) {
		super(firstname, surname, username, password);
	}
	
	public static boolean usernameAlreadyExists(String username) { //21.12
		boolean alreadyExists = false; 
		for(int i = 0; i < Project.getUsers().size(); i++){
			if(Project.getUsers().get(i).getUsername().equals(username)) {
				alreadyExists = true;
			}
		}
		return alreadyExists;
	}

	public void creatUser() { //21.12
		Scanner s = new Scanner(System.in);
		User tmp = new User();
		boolean correct = false;
		do {
			System.out.println("Gib einen Vorname, Nachnamen, Benutzername und Passwort ein");
			tmp = new User(s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine());
			if(usernameAlreadyExists(tmp.getUsername())) {
				System.out.println("Benutzername ist bereits vergeben");				
			}else {
				correct = true;
			}
		}while(!correct);
		Project.getUsers().add(tmp);
	}
	
	public void deleteUser() { // 21.12
		User u = Project.searchUser();
		if(u.getId() == 0) {
			return;
		}
		if(u == this) {
			Project.setCurrentUser(null);
		}
		for(int i = 0; i < u.getSponsorEntries().size(); i++) {
			for(int j = 0; i < Project.getSponsorEntries().size(); j++) {
				if(Project.getSponsorEntries().get(i).equals(u.getSponsorEntries().get(i))) {
					Project.getSponsorEntries().remove(j);
				}
			}
		}
		for(int i = 0; i < u.getTasks().size(); i++) {
			for(int j = 0; i < Project.getTasks().size(); j++) {
				if(Project.getTasks().get(j).equals(u.getTasks().get(i))) {
					Project.getTasks().remove(j);
				}
			}
		}
		for(int i = 0; i < Project.getUsers().size(); i++) {
			if(Project.getUsers().get(i).getUsername().equals(u.getUsername())) {
				Project.getUsers().set(i, null);
				Project.getUsers().remove(i);
			}
		}
		System.out.println("Benutzer wurde geloescht");
	}
	
	public void addUserTask() {
		UserTask tmp = new UserTask();
		tmp.createUserTask();
		User u = tmp.getUser();
		u.addTask(tmp);
		Project.getTasks().add(tmp);
	}
	
	public void editUserTask() {
		Scanner scan = new Scanner(System.in);
		UserTask userTask = Project.searchUserTask();
		if(userTask.getId() == 0) {
			return;
		}
		int auswahl = 0;
		do {
			userTask.printTask();
			System.out.println("Was moechten Sie aendern?\n"
					+ "\t1) Name\n"
					+ "\t2) Beschreibung\n"
					+ "\t3) Fertig\n"
					+ "\t4) Kosten\n"
					+ "\t5) Benutzer\n"		
					+ "\t0) Exit\nEingabe: ");
			auswahl = scan.nextInt();
			/*
			switch(auswahl) {
				case 1: printUsers(); break;
				case 2: ((Admin) currentUser).creatUser(); break;
				case 3: ((Admin) currentUser).deleteUser(); break;
				case 0: break;
				default: System.out.println("Falsche Eingabe"); break;
			*/
		}while(auswahl != 0);
	}
	
	public void deleteUserTask() {
		UserTask t = Project.searchUserTask();
		if(t.getId() == 0) {
			return;
		}
		User u = t.getUser();;
		for(int i = 0; i < Project.getTasks().size(); i++) {
			if(Project.getTasks().get(i).getId() == t.getId()) {
				t = Project.getTasks().get(i);
				Project.getTasks().set(i, null);
				Project.getTasks().remove(i);
			}
		}
		
		for(int i = 0; i < u.getTasks().size(); i++) {
			if(u.getTasks().get(i) == t) {
				u.getTasks().set(i, null);
				u.getTasks().remove(i);
			}
		}
		
		System.out.println("Aufgabe wurde geloescht");
	}
	
	public void addCompanyTask() {
		CompanyTask t = new CompanyTask();
		t.createCompanyTask();
		Project.getCompanyTasks().add(t);
	}
	
	public void editCompanyTask() {
		Scanner scan = new Scanner(System.in);
		CompanyTask companyTask = Project.searchCompanyTask();
		if(companyTask.getId() == 0) {
			return;
		}
		int auswahl = 0;
		do {
			companyTask.printTask();
			System.out.println("Was moechten Sie aendern?\n"
					+ "\t1) Name\n"
					+ "\t2) Beschreibung\n"
					+ "\t3) Fertig\n"
					+ "\t4) Kosten\n"
					+ "\t5) Firma\n"		
					+ "\t0) Exit\nEingabe: ");
			auswahl = scan.nextInt();
			/*
			switch(auswahl) {
				case 1: printUsers(); break;
				case 2: ((Admin) currentUser).creatUser(); break;
				case 3: ((Admin) currentUser).deleteUser(); break;
				case 0: break;
				default: System.out.println("Falsche Eingabe"); break;
			*/
		}while(auswahl != 0);
	}

	
	public void deleteCompanyTask() {
		Task t = Project.searchCompanyTask();
		if(t.getId() == 0) {
			return;
		}
		for(int i = 0; i < Project.getCompanyTasks().size(); i++) {
			if(Project.getCompanyTasks().get(i).getId() == t.getId()) {
				t = Project.getCompanyTasks().get(i);
				Project.getCompanyTasks().set(i, null);
				Project.getCompanyTasks().remove(i);
			}
		}
		
		System.out.println("Firmenbezogene Aufgabe wurde geloescht");
	}
}
