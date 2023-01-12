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
	
	public void createUserTask() {
		super.createTask();
		User user = Project.searchUser();
		if(user.getId() == 0) {
			return;
		}
		this.setUser(user);
	}
	
	public void printUserTask() {
		super.printTask();
		System.out.println("Verantwortlicher User: " + this.user.getFirstname() + " " + this.user.getSurname() + " (" + this.user.getUsername() + ")");
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
}
