import java.util.Scanner;

public class Admin extends User{

	public Admin(String firstname, String surname, String username, String password, int id) {
		super(firstname, surname, username, password, id);
	}

	public void creatUser() {
		Scanner s = new Scanner(System.in);
		System.out.println("Gib einen Vorname, Nachnamen, Benutzername und Passwort ein");
		User tmp = new User(s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), User.id);
		User.id++;
		Project.users.add(tmp);
	}
	
	public void deleteUser(User u) {
		for(int i = 0; i < Project.users.size(); i++) {
			if(Project.users.get(i).getUsername().equals(u.getUsername())) {
				Project.users.remove(i);
				break;
			}
		}
	}
	
	public Task giveTask(User u) {
		Scanner s = new Scanner(System.in);
		System.out.println("Gib Name, eine Beschreibung und die Kosten ein");
		Task tmp = new Task(Task.counterId, s.nextLine(), s.nextLine(), false, new Costs(s.nextFloat(), false), u);
		u.addTask(tmp);
		Project.tasks.add(tmp);
		
		return tmp;
	}

}
