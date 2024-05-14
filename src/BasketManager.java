import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BasketManager {
	private Basket basket;

	public BasketManager() {
		basket = new Basket();
	}

	public List<Product> addItem(List<Product> products, int barcode, int quantity) {
		try {
			// check if the barcode exists
			if (!StockReader.checkIfBarcodeExists(barcode)) {
				JOptionPane.showMessageDialog(null, "The barcode does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				return products;
			}
			// check if the quantity is greater than 0
			if (quantity <= 0) {
				JOptionPane.showMessageDialog(null, "The quantity must be greater than 0", "Error",
						JOptionPane.ERROR_MESSAGE);
				return products;
			}
			// check if the quantity is in stock
			if (StockReader.getQuantityInStock(barcode) < quantity) {
				JOptionPane.showMessageDialog(null, "The quantity is not in stock", "Error", JOptionPane.ERROR_MESSAGE);
				return products;
			}

			// check if the product is already in the basket
			for (List<Product> itemList : basket.getItems()) {
				if (!itemList.isEmpty() && itemList.get(0).getBarcode() == barcode) {
					// increase the quantity of the product in the basket
					for (int i = 0; i < quantity; i++) {
						itemList.add(itemList.get(0));
						itemList.get(0).setQuantityInStock(itemList.get(0).getQuantityInStock() - 1);
					}
					return products;
				}
			}

			// add the item to the basket
			for (Product product : products) {
				if (product.getBarcode() == barcode) {

					List<Product> productList = new ArrayList<>();
					for (int i = 0; i < quantity; i++) {
						productList.add(product);
						product.setQuantityInStock(product.getQuantityInStock() - 1);
					}
					basket.add(productList);
				}
			}
			JOptionPane.showMessageDialog(null, "Item added to basket", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return products;
	}

	public void removeItem(List<Product> products, int barcode, int quantity) {
		try {
			// check if the barcode exists
			if (!StockReader.checkIfBarcodeExists(barcode)) {
				JOptionPane.showMessageDialog(null, "The barcode does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// check if the quantity is greater than 0
			if (quantity <= 0) {
				JOptionPane.showMessageDialog(null, "The quantity must be greater than 0", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// check if barcode is 6 digits
			if (String.valueOf(barcode).length() != 6) {
				JOptionPane.showMessageDialog(null, "The barcode must be 6 digits", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// check if the product is in the basket
			for (List<Product> itemList : basket.getItems()) {
				if (!itemList.isEmpty() && itemList.get(0).getBarcode() == barcode) {
					// decrease the quantity of the product in the basket
					for (int i = 0; i < quantity; i++) {
						itemList.remove(itemList.size() - 1);
						// add the item back to the stock
						for (Product product : products) {
							if (product.getBarcode() == barcode) {
								product.setQuantityInStock(product.getQuantityInStock() + 1);
							}
						}
					}
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "Item removed from basket", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void clearBasket() {
		if (basket.getItems().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Basket is already empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// add the items back to the stock
		for (List<Product> itemList : basket.getItems()) {
			if (!itemList.isEmpty()) {
				Product item = itemList.get(0);
				item.setQuantityInStock(item.getQuantityInStock() + itemList.size());
			}
		}
		JOptionPane.showMessageDialog(null, "Basket cleared", "Success", JOptionPane.INFORMATION_MESSAGE);
		basket.getItems().clear();
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (List<Product> itemList : basket.getItems()) {
			if (!itemList.isEmpty()) {
				totalPrice += itemList.get(0).getRetailPrice() * itemList.size();
			}
		}
		return totalPrice;
	}

	public List<List<Product>> getItems() {
		return basket.getItems();
	}

	public void soldItems() {
		basket.getItems().clear();

	}

	public void viewBasket() {
		if (basket.getItems().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Basket is empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (List<Product> itemList : basket.getItems()) {
			if (!itemList.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Barcode: " + itemList.get(0).getBarcode() + "\nCategory: " + itemList.get(0).getCategory()
								+ "\nBrand: " + itemList.get(0).getBrand() + "\nColor: " + itemList.get(0).getColor()
								+ "\nConnectivity: " + itemList.get(0).getConnectivity() + "\nQuantity in stock: "
								+ itemList.get(0).getQuantityInStock() + "\nRetail price: "
								+ itemList.get(0).getRetailPrice() + "\nQuantity: " + itemList.size(),
						"Basket", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		JOptionPane.showMessageDialog(null, "Total price: " + getTotalPrice(), "Basket",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void populateTable(JTable basketTable) {
		if (basket.getItems().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Basket is empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultTableModel model = (DefaultTableModel) basketTable.getModel();
		model.setRowCount(0); // Clear existing data

		for (List<Product> productList : basket.getItems()) {
			if (!productList.isEmpty()) {
				Product product = productList.get(0);
				model.addRow(new Object[] { product.getBarcode(), productList.size(), product.getRetailPrice() });
			}
		}

	}

}