
public class Costs {
	private float amount;			//19.12
	private boolean alreadyPaid;
	
	public Costs(float amount, boolean alreadyPaid) {
		this.amount = amount;
		this.alreadyPaid = alreadyPaid;
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
