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
		User user = new User();
		boolean correct = false;
		do {

			user = createUser();
			if(usernameAlreadyExists(user.getUsername())) {
				System.out.println("Benutzername ist bereits vergeben");				
			}else {
				correct = true;
			}
		}while(!correct);
		Project.getUsers().add(user);
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
		for(int i = 0; i < u.getUserTasks().size(); i++) {
			for(int j = 0; i < Project.getUserTasks().size(); j++) {
				if(Project.getUserTasks().get(j).equals(u.getUserTasks().get(i))) {
					Project.getUserTasks().remove(j);
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
		tmp = UserTask.createUserTask();
		if(tmp.getId() != 0) {
			tmp.getUser().addUserTask(tmp);
			Project.getUserTasks().add(tmp);
		}
	}
	
	public void editUserTask(UserTask userTask) {
		Scanner scan = new Scanner(System.in);
		String confirm;
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
			scan.nextLine();
			switch(auswahl) {
				case 1: 
					System.out.println("Geben Sie die neue Namen ein\nEingabe:");
					String name = scan.nextLine();
					System.out.println("Moechten Sie den Namen von '" + userTask.getName() + "' zu '" + name + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": userTask.setName(name); System.out.println("Name wurde zu '" + name + "' geandert"); break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 2:
					System.out.println("Geben Sie die neue Beschreibung ein\nEingabe:");
					String description = scan.nextLine();
					System.out.println("Moechten Sie den Namen von '" + userTask.getDescription() + "' zu '" + description + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": userTask.setDescription(description); System.out.println("Beschreibung wurde zu '" + description + "' geandert"); break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 3: 
					boolean finished = false;
					String finishedS;
					do {
						System.out.println("Ist die Aufgabe beendet[w/f]?\nEingabe:");
						finishedS = scan.nextLine();
						switch(finishedS) {
							case "w": finished = true; break;
							case "f": finished = false; break;
							default: System.out.println("Falsche Eingabe"); break;
						}
					}while(!finishedS.equals("w") && !finishedS.equals("f"));
					System.out.println("Moechten Sie Fertig von '" + userTask.isFinished() + "' zu '" + finished + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": userTask.setFinished(finished); System.out.println("Fertig wurde zu '" + finished + "' geandert"); break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 4: userTask.getCosts().editCosts(); break;
				case 5: userTask.changeUser(); break;
				case 0: break;
				default: System.out.println("Falsche Eingabe"); break;
			}
		}while(auswahl != 0);
	}
	
	public void deleteUserTask() {
		UserTask t = Project.searchUserTask();
		if(t.getId() == 0) {
			return;
		}
		User u = t.getUser();;
		for(int i = 0; i < Project.getUserTasks().size(); i++) {
			if(Project.getUserTasks().get(i).getId() == t.getId()) {
				t = Project.getUserTasks().get(i);
				Project.getUserTasks().set(i, null);
				Project.getUserTasks().remove(i);
			}
		}
		
		for(int i = 0; i < u.getUserTasks().size(); i++) {
			if(u.getUserTasks().get(i) == t) {
				u.getUserTasks().set(i, null);
				u.getUserTasks().remove(i);
			}
		}
		
		System.out.println("Aufgabe wurde geloescht");
	}
	
	public void addCompanyTask() {
		CompanyTask t = new CompanyTask();
		t = CompanyTask.createCompanyTask();
		Project.getCompanyTasks().add(t);
	}
	
	public void editCompanyTask(CompanyTask companyTask) {
		Scanner scan = new Scanner(System.in);
		String confirm;
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
			scan.nextLine();
			switch(auswahl) {
				case 1: 
					System.out.println("Geben Sie die neue Namen ein\nEingabe:");
					String name = scan.nextLine();
					System.out.println("Moechten Sie den Namen von '" + companyTask.getName() + "' zu '" + name + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": companyTask.setName(name); System.out.println("Name wurde zu '" + name + "' geandert"); break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 2:
					System.out.println("Geben Sie die neue Beschreibung ein\nEingabe:");
					String description = scan.nextLine();
					System.out.println("Moechten Sie den Namen von '" + companyTask.getDescription() + "' zu '" + description + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": companyTask.setDescription(description); System.out.println("Beschreibung wurde zu '" + description + "' geandert"); break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 3: 
					boolean finished = false;
					String finishedS;
					do {
						System.out.println("Ist die Aufgabe beendet[w/f]?\nEingabe:");
						finishedS = scan.nextLine();
						switch(finishedS) {
							case "w": finished = true; break;
							case "f": finished = false; break;
							default: System.out.println("Falsche Eingabe"); break;
						}
					}while(!finishedS.equals("w") && !finishedS.equals("f"));
					System.out.println("Moechten Sie Fertig von '" + companyTask.isFinished() + "' zu '" + finished + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": companyTask.setFinished(finished); System.out.println("Fertig wurde zu '" + finished + "' geandert"); break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 4: companyTask.getCosts().editCosts(); break;
				case 5: companyTask.changeCompany(); break;
				case 0: break;
				default: System.out.println("Falsche Eingabe"); break;
			}
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