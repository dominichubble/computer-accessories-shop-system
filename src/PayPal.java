
public class PayPal implements PaymentMethod {
	String email;

	@Override
	public Receipt processPayment(double amount, String fullAddress) {
		return new Receipt(email, amount, fullAddress);
	}

	public PayPal(String email) {
		this.email = email;
	}

}
