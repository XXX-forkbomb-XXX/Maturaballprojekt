import java.util.Scanner;

public class UserTask extends Task{
	private User user;
	private static int counterId = 1000;
	
	public UserTask() {
		this.id = counterId;
		counterId++;
	}
	
	public UserTask(String name, String description, boolean finished, Costs costs, User user) {
		super(name, description, finished, costs);
		this.id = counterId;
		this.user = user;
		counterId++;
	}
	
	public static UserTask createUserTask() {
		Task t = Task.createTask();
		User user = User.createUser();
		UserTask uT = new UserTask(t.getName(), t.getDescription(), t.isFinished(), t.getCosts(), user);
		return uT;
	}
	
	public void printUserTask() {
		super.printTask();
		System.out.println("Verantwortlicher User: " + this.user.getFirstname() + " " + this.user.getSurname() + " (" + this.user.getUsername() + ")\n");
	}
	
	public void changeUser() {	
		System.out.println("Geben Sie die neuen Verantwortliche Benutzer ein");
		User user = Project.searchUser();
		if(user.getId() == 0) {
			return;
		}
		Scanner scan = new Scanner(System.in);
		String confirm;
		System.out.println("Moechten Sie den Verantwortlichen Benutzer von '" + this.getUser().getFirstname() + " " + this.getUser().getSurname() + " (" + this.getUser().getUsername() + ")' zu " + user.getFirstname() + " " + user.getSurname() + " (" + user.getUsername() + ")' aendern[w/f]");
		confirm = scan.nextLine();
		switch(confirm) {
			case "w": 
				User oldUser = this.getUser();
				for(int i = 0; i < oldUser.getTasks().size(); i++) {
					if(oldUser.getTasks().get(i) == this) {
						oldUser.getTasks().set(i, null);
						oldUser.getTasks().remove(i);
					}
				}
				this.setUser(user); 
				user.addTask(this);
				System.out.println("Der Verantwortlichen Benutzer wurde auf '" + this.getUser().getFirstname() + " " + this.getUser().getSurname() + ")' geandert"); 
				break;
			case "f": break;
			default: System.out.println("Falsche Eingabe"); break;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static int getCounterId() {
		return counterId;
	}

	public static void setCounterId(int counterId) {
		UserTask.counterId = counterId;
	}
	
	public String toString() {
		return(super.toString() + ";" + user.toString());
	}
}