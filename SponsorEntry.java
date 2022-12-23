import java.util.Scanner;

public class SponsorEntry {
	public static int counterId = 0;
	private int id;
	private Company company;
	private float amount;
	
	public SponsorEntry() {
		
	}
	
	public SponsorEntry(int id, Company company, float amount) {
		this.id = id;
		this.company = company;
		this.amount = amount;
	}


	public void printSponsorEntry() {				//19.12
		System.out.println("Sponsoreintrag-ID: " + this.getId() + "\n"
				+ "Unternehmen: " + this.company.getName() + "\n"
				+ "Addresse: " + this.company.getAddress() + "\n"
				+ "Email: " + this.company.getEmail() + "\n"
				+ "Nummer: " + this.company.getNumber() + "\n"
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
