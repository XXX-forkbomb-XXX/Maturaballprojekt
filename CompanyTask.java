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
	
	public static CompanyTask createCompanyTask() {
		Task t = Task.createTask();
		Company company = Company.createCompany();
		CompanyTask cT = new CompanyTask(t.getName(), t.getDescription(), t.isFinished(), t.getCosts(), company);
		return cT;
	}
	
	public void printTask() {
		super.printTask();
		System.out.println("Daten Firma:\nName: " + this.company.getName() + "\n"
				+ "Addresse: " + this.company.getAddress() + "\n"
				+ "Email: " + this.company.getEmail() + "\n"
				+ "Nummer: " + this.company.getNumber() + "\n");
	}
	
	public void changeCompany() {	
		System.out.println("Geben Sie die Daten der neuen Firma ein:");
		Company company = new Company();
		company.createCompany();
				
		Scanner scan = new Scanner(System.in);
		String confirm;
		System.out.println("Moechten Sie die Firma von '" + this.getName() + "' zu " + company.getName() + "' aendern[w/f]");
		confirm = scan.nextLine();
		switch(confirm) {
			case "w": 
				this.setCompany(company);
				System.out.println("Die Firma wurde auf '" + this.getName() + "' geandert"); 
				break;
			case "f": break;
			default: System.out.println("Falsche Eingabe"); break;
		}
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

	public String toString() {
		return(super.toString() + ";" + company.toString());
	}	
	
}