import java.util.Scanner;

public class Costs {
	private float amount;
	private boolean alreadyPaid;
	
	public Costs(float amount, boolean alreadyPaid) {
		this.amount = amount;
		this.alreadyPaid = alreadyPaid;
	}
	
	public Costs() {
		
	}

	public static Costs createCosts() {
		
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Daten der Kosten: \nMenge: ");
		float amount = s.nextFloat();
		String string;
		boolean isAlreadyPaid = false;
		do {
			System.out.printf("Bereits bezahlt[w/f]: ");
			string = s.next();
			switch(string) {
				case "w": isAlreadyPaid = true; break;
				case "f": isAlreadyPaid = false; break;
				default: System.out.println("Falsche Eingabe");
			}
		}while(!string.equals("w") && !string.equals("f"));
		Costs costs = new Costs(amount, isAlreadyPaid);
		return costs;
	}
	
	public void printCosts() {
		System.out.println(
				"Menge: " + this.getAmount() + "\n"
				+ "Bereits bezahlt: " + this.isAlreadyPaid() + "\n");
	}
	
	public void editCosts() {
		Scanner scan = new Scanner(System.in);
		String confirm;
		int auswahl = 0;
		do {
			this.printCosts();
			System.out.println("Was moechten Sie aendern?\n"
					+ "\t1) Menge\n"
					+ "\t2) Bereits bezahlt\n"	
					+ "\t0) Exit\nEingabe: ");
			auswahl = scan.nextInt();
			scan.nextLine();
			switch(auswahl) {
				case 1: 
					System.out.println("Geben Sie die neuen Menge ein\nEingabe:");
					float amount = scan.nextFloat();
					System.out.println("Moechten Sie die Menge von '" + this.getAmount() + "' zu '" + amount + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": 
							this.setAmount(amount); 
							System.out.println("Menge wurde zu '" + amount + "' geandert"); 
							break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 2: 
					boolean alreadyPaid = false;
					String alreadyPaidS;
					do {
						System.out.println("Sind die Kosten bereits bezahlt[w/f]?\nEingabe:");
						alreadyPaidS = scan.nextLine();
						switch(alreadyPaidS) {
							case "w": alreadyPaid = true; break;
							case "f": alreadyPaid = false; break;
							default: System.out.println("Falsche Eingabe"); break;
						}
					}while(!alreadyPaidS.equals("w") && !alreadyPaidS.equals("f"));
					System.out.println("Moechten Sie Bereits bezahlt von '" + this.isAlreadyPaid() + "' zu '" + alreadyPaid + "' aendern[w/f]");
					confirm = scan.nextLine();
					switch(confirm) {
						case "w": this.setAlreadyPaid(alreadyPaid); System.out.println("Bereits bezahlt wurde zu '" + alreadyPaid + "' geandert"); break;
						case "f": break;
						default: System.out.println("Falsche Eingabe"); break;
					}
					break;
				case 0: break;
				default: System.out.println("Falsche Eingabe"); break;
			}
		}while(auswahl != 0);
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isAlreadyPaid() {
		return alreadyPaid;
	}

	public void setAlreadyPaid(boolean alreadyPaid) {
		this.alreadyPaid = alreadyPaid;
	}

	public String toString() {
		return(amount + ";" + alreadyPaid);
	}
	
}