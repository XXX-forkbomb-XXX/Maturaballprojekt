
public class Task {
	int id;
	String name;
	String description;
	boolean finished;
	Costs costs;
	
	public Task(int id, String name, String description, boolean finished, Costs costs){
		this.id = id;
		this.name = name;
		this.description = description;
		this.finished = finished;
		this.costs = costs;
	}

}
