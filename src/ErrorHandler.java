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

	public static Boolean checkIfBrandIsValid(String brand) {
		if (!brand.matches("^[a-zA-Z]*$")) {
			JOptionPane.showMessageDialog(null, "The brand must be alphabetic", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static Boolean checkIfColorIsValid(String color) {
		if (!color.matches("^[a-zA-Z]*$")) {
			JOptionPane.showMessageDialog(null, "The color must be alphabetic", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static Boolean checkIfCostIsGreaterThanZero(double cost) {
		if (cost <= 0) {
			JOptionPane.showMessageDialog(null, "The cost must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static Boolean checkIfPriceIsGreaterThanZero(double price) {
		if (price <= 0) {
			JOptionPane.showMessageDialog(null, "The price must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static Boolean checkIfKeyboardAdditionalInfoIsValid(String additionalInfo) {
		// check if the additional info is from enum Layout class e.g US, UK
		Layout layout;
		try {
			layout = Layout.valueOf(additionalInfo);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "The layout must be US or UK", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static Boolean checkIfMouseAdditionalInfoIsValid(String additionalInfo) {
		// check if the additional info is numeric
		if (!additionalInfo.matches("^[0-9]*$")) {
			JOptionPane.showMessageDialog(null, "The additional info must be numeric", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

    public static Boolean checkIfBarcodeIsPresent(int barcode) {
        if (StockReader.checkIfBarcodeExists(barcode)) {
            JOptionPane.showMessageDialog(null, "The barcode already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static Boolean checkIfInputIsNumeric(String input) {
        if (!input.matches("^[0-9]*$")) {
            JOptionPane.showMessageDialog(null, "The input must be numeric", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }



}