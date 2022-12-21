import java.util.Scanner;

public class Admin extends User{

	public Admin(String firstname, String surname, String username, String password) {
		super(firstname, surname, username, password);
	}
	
	public static boolean usernameAlreadyExists(String username) { //21.12
		boolean alreadyExists = false; 
		for(int i = 0; i < Project.users.size(); i++){
			if(Project.users.get(i).getUsername().equals(username)) {
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
		Project.users.add(tmp);
	}
	
	public void deleteUser() { // 21.12
		User u = Project.searchUser();
		for(int i = 0; i < u.getSponsorEntries().size(); i++) {
			for(int j = 0; i < Project.sponsorEntries.size(); j++) {
				if(Project.sponsorEntries.get(i).equals(u.getSponsorEntries().get(i))) {
					Project.sponsorEntries.remove(j);
				}
			}
		}
		for(int i = 0; i < u.getTasks().size(); i++) {
			for(int j = 0; i < Project.tasks.size(); j++) {
				if(Project.tasks.get(j).equals(u.getTasks().get(i))) {
					Project.tasks.remove(j);
				}
			}
		}
		for(int i = 0; i < Project.users.size(); i++) {
			if(Project.users.get(i).getUsername().equals(u.getUsername())) {
				Project.users.set(i, null);
				Project.users.remove(i);
			}
		}
		System.out.println("Benutzer wurde geloescht");
	}
	
	public void giveTask() {
		User u = Project.searchUser();
		Scanner s = new Scanner(System.in);
		System.out.println("Gib Name, eine Beschreibung und die Kosten ein");
		Task tmp = new Task(s.nextLine(), s.nextLine(), false, new Costs(s.nextFloat(), false), u);
		u.addTask(tmp);
		Project.tasks.add(tmp);
	}
	
	public void deleteTask() {
		User u = Project.searchUser();
		Task t = Project.searchTask(u);
		for(int i = 0; i < Project.tasks.size(); i++) {
			if(Project.tasks.get(i).getId() == t.getId()) {
				t = Project.tasks.get(i);
				Project.tasks.set(i, null);
				Project.tasks.remove(i);
			}
		}
		
		for(int i = 0; i < u.tasks.size(); i++) {
			if(u.tasks.get(i) == t) {
				u.tasks.set(i, null);
				u.tasks.remove(i);
			}
		}
		
		System.out.println("Aufgabe wurde geloescht");
	}
	
	public void addCompanyTask() {				//21.12
		Company c = new Company();
		c.createCompany();
		Scanner s = new Scanner(System.in);
		System.out.println("Gib Name, Beschreibung und Kosten ein:");
		Task tmp = new Task(s.nextLine(), s.nextLine(), false, new Costs(s.nextFloat(), false), c);

		Project.companyTask.add(tmp);
	}

	
	public void deleteCompanyTask() {
		Task t = Project.searchTask();
		for(int i = 0; i < Project.tasks.size(); i++) {
			if(Project.tasks.get(i).getId() == t.getId()) {
				t = Project.tasks.get(i);
				Project.tasks.set(i, null);
				Project.tasks.remove(i);
			}
		}
		
		System.out.println("Firmenbezogene Aufgabe wurde geloescht");
	}
}
