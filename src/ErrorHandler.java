import java.util.List;

import javax.swing.JOptionPane;

public class ErrorHandler {
	public static Boolean checkIfBarcodeExists(int barcode) {
		if (!StockReader.checkIfBarcodeExists(barcode)) {
			JOptionPane.showMessageDialog(null, "The barcode does not exist", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static Boolean checkIfQuantityIsGreaterThanZero(int quantity) {
		if (quantity <= 0) {
			JOptionPane.showMessageDialog(null, "The quantity must be greater than 0", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static Boolean checkIfQuantityIsInStock(int barcode, int quantity) {
		if (StockReader.getQuantityInStock(barcode) < quantity) {
			JOptionPane.showMessageDialog(null, "The quantity is not in stock", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}



	public static Boolean checkIfBarcodeIs6Digits(int barcode) {
		if (String.valueOf(barcode).length() != 6) {
			JOptionPane.showMessageDialog(null, "The barcode must be 6 digits", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

    public static Boolean checkIfBasketIsEmpty(List<List<Product>> items) {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The basket is empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}