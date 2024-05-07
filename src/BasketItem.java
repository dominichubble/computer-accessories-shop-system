
public class BasketItem {
	private int barcode;
	private int quantity;
	private double price;

	public BasketItem(int barcode, int quantity, double price) {
		this.barcode = barcode;
		this.quantity = quantity;
		this.price = price;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static Boolean checkIfInStock(int barcode, int quantity) {
		int quantityInStock = StockReader.getQuantityInStock(barcode);
		if (quantityInStock < quantity) {
			return false;
		}
		return true;
	}

}
