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
		company = Company.createCompany();
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
	
	public void changeUser() {	
		System.out.println("Geben Sie die neuen Verantwortliche Benutzer ein");
		User user = Project.searchUser();
		if(user.getId() == 0) {
			return;
		}
		Scanner scan = new Scanner(System.in);
		String confirm;
		System.out.println("Moechten Sie den Verantwortlichen Benutzer von '" + this.getUser().getFirstname() + " " + this.getUser().getSurname() + " (" + this.getUser().getUsername() + ")' zu " + user.getFirstname() + " " + user.getSurname() + " (" + user.getUsername() + ")' aendern[w/f]");
		confirm = scan.nextLine();
		switch(confirm) {
			case "w": 
				User oldUser = this.getUser();
				for(int i = 0; i < oldUser.getUserTasks().size(); i++) {
					if(oldUser.getSponsorEntries().get(i) == this) {
						oldUser.getSponsorEntries().set(i, null);
						oldUser.getSponsorEntries().remove(i);
					}
				}
				this.setUser(user); 
				user.getSponsorEntries().add(this);
				System.out.println("Der Verantwortlichen Benutzer wurde auf '" + this.getUser().getFirstname() + " " + this.getUser().getSurname() + ")' geandert"); 
				break;
			case "f": break;
			default: System.out.println("Falsche Eingabe"); break;
		}
	}
	
	public void changeCompany() {	
		System.out.println("Geben Sie die Daten der neuen Firma ein:");
		Company company = new Company();
		company = Company.createCompany();
				
		Scanner scan = new Scanner(System.in);
		String confirm;
		System.out.println("Moechten Sie die Firma von '" + this.getCompany() + "' zu " + company.getName() + "' aendern[w/f]");
		confirm = scan.nextLine();
		switch(confirm) {
			case "w": 
				this.setCompany(company);
				System.out.println("Die Firma wurde auf '" + this.getCompany() + "' geandert"); 
				break;
			case "f": break;
			default: System.out.println("Falsche Eingabe"); break;
		}
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
		return(id + ";" + company.toString() + ";" + user.toString() + ";" + amount );
	}	
	
}