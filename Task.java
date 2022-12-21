
public class Task {
	public static int counterId = 0;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Costs getCosts() {
		return costs;
	}

	public void setCosts(Costs costs) {
		this.costs = costs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private int id;
	private String name;
	private String description;
	private boolean finished;
	private Costs costs;
	private User user;
	
	public Task() {
		
	}
	
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
