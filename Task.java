
public class Task {
	public static int counterId = 0;
	private int id;
	private String name;
	private String description;
	private boolean finished;
	private Costs costs;
	private User user;
	
	public Task(int id, String name, String description, boolean finished, Costs costs, User user){
		this.id = id;
		this.name = name;
		this.description = description;
		this.finished = finished;
		this.costs = costs;
		this.user = user;
	}
	
	public void printTask() {
		System.out.println("Task-ID: " + this.id + "\n"
				+ "Name: " + this.name + "\n"
				+ "Beschreibung: " + this.description + "\n"
				+ "Verantwortlicher User: " + this.user.getFirstname() + " " + this.user.getSurname() + "\n"
				+ "Kosten: " + this.costs.getAmount() + "\n"
				+ "Bereits bezahlt: " + this.costs.isAlreadyPaid() + "\n"
				+ "Fertig: " + this.finished + "\n");
	}

}
