import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Basket {
	private List<List<Product>> items = new ArrayList<>();

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

			// add the item to the basket
			for (Product product : products) {
				if (product.getBarcode() == barcode) {
					List<Product> productList = new ArrayList<>();
					for (int i = 0; i < quantity; i++) {
						productList.add(product);
						product.setQuantityInStock(product.getQuantityInStock() - 1);
					}
					items.add(productList);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
		}
		JOptionPane.showMessageDialog(null, "Item added to basket", "Success", JOptionPane.INFORMATION_MESSAGE);
		return products;
	}

	public void removeItem(Product product) {
		items.removeIf(itemList -> !itemList.isEmpty() && itemList.get(0).equals(product));
	}

	public void clearBasket() {
		if (items.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Basket is already empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// add the items back to the stock
		for (List<Product> itemList : items) {
			if (!itemList.isEmpty()) {
				Product item = itemList.get(0);
				item.setQuantityInStock(item.getQuantityInStock() + itemList.size());
			}
		}
		JOptionPane.showMessageDialog(null, "Basket cleared", "Success", JOptionPane.INFORMATION_MESSAGE);
		items.clear();
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (List<Product> itemList : items) {
			if (!itemList.isEmpty()) {
				totalPrice += itemList.get(0).getRetailPrice() * itemList.size();
			}
		}
		return totalPrice;
	}

	public List<List<Product>> getItems() {
		return items;
	}
}