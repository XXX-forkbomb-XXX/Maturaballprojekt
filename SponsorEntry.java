import java.util.Scanner;

public class SponsorEntry {
	private Company company;		//19.12
	private float amount;
	
	public SponsorEntry(Company company, float amount) {
		this.company = company;
		this.amount = amount;
	}
	
	public SponsorEntry createSponsor() { //19.12
		Scanner s = new Scanner(System.in);
		Company c = null;
		c.createCompany();
		System.out.printf("\nBeitrag : ");
		
		SponsorEntry se = new SponsorEntry(c, s.nextFloat());
		return se;
	}
	
	public void printSponsorEntry() {				//19.12
		System.out.println("Unternehmen: " + this.company.getName() + "\n"
				+ "Addresse: " + this.company.getAddress() + "\n"
				+ "Email: " + this.company.getEmail() + "\n"
				+ "Nummer: " + this.company.getNumber() + "\n"
				+ "Betrag: " + this. + "\n"
				+ "Bereits bezahlt: " + this.costs.isAlreadyPaid() + "\n"
				+ "Fertig: " + this.finished + "\n");
	}
}
