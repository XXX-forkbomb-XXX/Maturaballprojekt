import java.util.Scanner;

public class CompanyTask extends Task{
	private Company company;
	private static int counterId = 1000;
	
	public CompanyTask() {
		this.id = counterId;
		counterId++;
	}
	
	public CompanyTask(String name, String description, boolean finished, Costs costs, Company company) {
		super(name, description, finished, costs);
		this.company = company;
		this.id = counterId;
		counterId++;
	}
	
	public void createCompanyTask() {
		super.createTask();
		Company company = new Company();
		company.createCompany();
		this.setCompany(company);
	}
	
	public void printCompanyTask() {
		super.printTask();
		System.out.println("Daten Firma:\nName: " + this.company.getName() + "\n"
				+ "Addresse: " + this.company.getAddress() + "\n"
				+ "Email: " + this.company.getEmail() + "\n"
				+ "Nummer: " + this.company.getNumber());
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static int getCounterId() {
		return counterId;
	}

	public static void setCounterId(int counterId) {
		CompanyTask.counterId = counterId;
	}
}
