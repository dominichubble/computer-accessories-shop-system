
public class PayPal implements PaymentMethod {
	private String email;

	@Override
	public Receipt processPayment(double amount, Address fullAddress) {
		return new Receipt(email, amount, fullAddress);
	}

	public PayPal(String email) {
		this.email = email;
	}

}
