import java.util.Scanner;

public class Task {
	private static int counterId = 0;
	private int id;
	private String name;
	private String description;
	private boolean finished;
	private Costs costs;
	private User user;
	private Company company;

	public Task() {
		this.id = counterId;
		counterId++;
	}
	
	public Task(String name, String description, boolean finished, Costs costs, User user){
		this.name = name;
		this.description = description;
		this.finished = finished;
		this.costs = costs;
		this.user = user;
		this.id = counterId;
		counterId++;
	}
	
	public Task(String name, String description, boolean finished, Costs costs, Company company) {		//21.12
		this.name = name;
		this.description = description;
		this.finished = finished;
		this.costs = costs;
		this.company = company;
		this.id = counterId;
		counterId++;
	}
	
	public void createTask(Company company) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Daten der Aufgabe: \nName: ");
		this.setName(s.nextLine());
		System.out.printf("Beschreibung: ");
		this.setDescription(s.nextLine());
		Costs costs = new Costs();
		costs.createCosts();
		this.setCosts(costs);
		this.setCompany(company);
	}
	
	public void createTask(User user) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Daten der Aufgabe: \nName: ");
		this.setName(s.nextLine());
		System.out.printf("Beschreibung: ");
		this.setDescription(s.nextLine());
		Costs costs = new Costs();
		costs.createCosts();
		this.setCosts(costs);
		this.setUser(user);
	}
	
	public void printTask() {
		System.out.println("Task-ID: " + this.id + "\n"
				+ "Name: " + this.name + "\n"
				+ "Beschreibung: " + this.description);
		if(company == null) {
			System.out.println("Verantwortlicher User: " + this.user.getFirstname() + " " + this.user.getSurname() + " (" + this.user.getUsername() + ")");
		}
		else {
			System.out.println("Daten Firma:\nName: " + this.company.getName() + "\n"
					+ "Addresse: " + this.company.getAddress() + "\n"
					+ "Email: " + this.company.getEmail() + "\n"
					+ "Nummer: " + this.company.getNumber());
		}
		System.out.println("Kosten: " + this.costs.getAmount() + "\n"
				+ "Bereits bezahlt: " + this.costs.isAlreadyPaid() + "\n"
				+ "Fertig: " + this.finished + "\n");
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
		Task.counterId = counterId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
