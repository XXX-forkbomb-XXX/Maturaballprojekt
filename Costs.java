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

	public void createCosts() {
		
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Daten der Kosten: \nMenge: ");
		this.setAmount(s.nextFloat());
		String string;
		do {
			System.out.printf("Bereits Bezahlt[w/f]: ");
			string = s.nextLine();
			switch(string) {
				case "w": this.setAlreadyPaid(true); break;
				case "f": this.setAlreadyPaid(false); break;
				default: System.out.println("Falsche Eingabe");
			}
		}while(string.equals("w")|| string.equals("f"));
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

}
