public interface PaymentMethod {
    Receipt processPayment(double amount, String fullAddress);
}
