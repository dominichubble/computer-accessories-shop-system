
public class Receipt {
	private String personalInfo;
	private double amount;
	private String fullAddress;

	/**
	 * Constructs a new Receipt with the specified attributes.
	 *
	 * @param personalInfo  Text of the receipt.
	 * @param amount      Amount of the payment.
	 * @param fullAddress Full address of the user.
	 */
	public Receipt(String personalInfo, double amount, String fullAddress) {
		this.personalInfo = personalInfo;
		this.amount = amount;
		this.fullAddress = fullAddress;
	}

	public String getReceiptTxt() {
		return "Receipt: " + personalInfo + "\nAmount: " + amount + "\nAddress: " + fullAddress + "\nDate: " + java.time.LocalDate.now() + "\nTime: " + java.time.LocalTime.now() + "\n";
	}


}
