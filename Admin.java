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
	
	public void giveTask() {
		User u = Project.searchUser();
		Task tmp = new Task();
		tmp.createTask(u);
		u.addTask(tmp);
		Project.getTasks().add(tmp);
	}
	
	public void deleteTask() {
		User u = Project.searchUser();
		Task t = Project.searchTask(u);
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
	
	public void addCompanyTask() {				//21.12
		Company c = new Company();
		c.createCompany();
		Task t = new Task();
		t.createTask(c);
		
		Project.getCompanyTasks().add(t);
	}

	
	public void deleteCompanyTask() {
		Task t = Project.searchCompanyTask();
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
