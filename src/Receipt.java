
public class Receipt {
	private String personalInfo;
	private double amount;
	private Address fullAddress;

	/**
	 * Constructs a new Receipt with the specified attributes.
	 *
	 * @param personalInfo Text of the receipt.
	 * @param amount       Amount of the payment.
	 * @param fullAddress  Full address of the user.
	 */
	public Receipt(String personalInfo, double amount, Address fullAddress) {
		this.personalInfo = personalInfo;
		this.amount = amount;
		this.fullAddress = fullAddress;
	}

	public String getReceiptTxt() {
		return "Receipt for: " + personalInfo + "\nAmount: " + amount + "\nAddress: " + fullAddress.toString() + "\nDate: "
				+ java.time.LocalDate.now();
	}

}
