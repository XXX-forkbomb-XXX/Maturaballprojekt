import java.util.Scanner;

public class SponsorEntry {
	public static int counterId = 1000;
	private int id;
	private Company company;
	private User user;
	private float amount;
	
	public SponsorEntry() {
		this.id = counterId;
		counterId++;		
	}
	
	public SponsorEntry(int id, Company company, User user, float amount) {
		this.id = counterId;
		this.company = company;
		this.user = user;
		this.amount = amount;
		counterId++;
	}
	
	public void createSponsorEntry() {
		
		Scanner s = new Scanner(System.in);
		
		Company company = new Company();
		company.createCompany();
		this.setCompany(company);
		this.user = Project.getCurrentUser();
		System.out.printf("Daten des Sponsoreintrags: \nMenge: ");
		this.setAmount(s.nextFloat());
	}


	public void printSponsorEntry() {			
		System.out.println("Sponsoreintrag-ID: " + this.getId() + "\n"
				+ "Unternehmen: " + this.company.getName() + "\n"
				+ "Addresse: " + this.company.getAddress() + "\n"
				+ "Email: " + this.company.getEmail() + "\n"
				+ "Nummer: " + this.company.getNumber() + "\n"
				+ "Verantwortlicher User: " + this.user.getFirstname() + " " + this.user.getSurname() + " (" + this.user.getUsername() + ")\n"
				+ "Betrag: " + this.amount + "\n");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String toString() {
		return(id + ";" + company.toString() + user.toString() + ";" + amount );
	}	
	
}