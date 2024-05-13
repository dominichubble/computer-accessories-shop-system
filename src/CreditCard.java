
public class CreditCard implements PaymentMethod {
	private String cardNumber;
	private String securityNumber;

	@Override
	public Receipt processPayment(double amount, Address fullAddress) {
		return new Receipt(cardNumber, amount, fullAddress);
	}

	public CreditCard(String cardNumber, String securityNumber) {
		this.cardNumber = cardNumber;
		this.securityNumber = securityNumber;
	}

}
