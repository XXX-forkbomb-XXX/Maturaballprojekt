import java.util.Scanner;

public abstract class Task {
	protected int id;
	private String name;
	private String description;
	private boolean finished;
	private Costs costs;
	private static int counterId = 1000;

	public Task() {
		this.id = counterId;
		counterId++;
	}
	
	public Task(String name, String description, boolean finished, Costs costs) {
		this.name = name;
		this.description = description;
		this.finished = finished;
		this.costs = costs;
	}
	
	public void createTask() {
		
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Daten der Aufgabe: \nName: ");
		this.setName(s.nextLine());
		System.out.printf("Beschreibung: ");
		this.setDescription(s.nextLine());
		Costs costs = new Costs();
		costs.createCosts();
		this.setCosts(costs);
	}
	
	public void printTask() {
		System.out.println("Task-ID: " + this.id + "\n"
				+ "Name: " + this.name + "\n"
				+ "Beschreibung: " + this.description);

		System.out.println("Kosten: " + this.costs.getAmount() + "\n"
				+ "Bereits bezahlt: " + this.costs.isAlreadyPaid() + "\n"
				+ "Fertig: " + this.finished);
	}
	
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

	public String toString() {
		return id + ";" + name + ";" + description + ";" + finished + ";" + costs.toString();
	}
	
}