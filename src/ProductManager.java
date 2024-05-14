import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductManager {

	List<Product> products;

	public ProductManager() {
		products = StockReader.readStockFile("data/Stock.txt");
	}

	public void populateTable(JTable table, List<Product> products) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Product product : products) {
			String deviceType = "";
			String additionalInfo = "";

			if (product instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) product;
				deviceType = keyboard.getDeviceType().toString();
				additionalInfo = keyboard.getAdditionalInfo().toString();
			} else if (product instanceof Mouse) {
				Mouse mouse = (Mouse) product;
				deviceType = mouse.getDeviceType().toString();
				additionalInfo = Integer.toString(mouse.getAdditionalInfo());
			}

			model.addRow(new Object[] { product.getBarcode(), product.getCategory(), deviceType, product.getBrand(),
					product.getColor(), product.getConnectivity(), product.getQuantityInStock(),
					product.getRetailPrice(), additionalInfo });

		}
	}

	public void adminPopulateTable(JTable table, List<Product> products) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Product product : products) {
			String deviceType = "";
			String additionalInfo = "";

			if (product instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) product;
				deviceType = keyboard.getDeviceType().toString();
				additionalInfo = keyboard.getAdditionalInfo().toString();
			} else if (product instanceof Mouse) {
				Mouse mouse = (Mouse) product;
				deviceType = mouse.getDeviceType().toString();
				additionalInfo = Integer.toString(mouse.getAdditionalInfo());
			}

			model.addRow(new Object[] { product.getBarcode(), product.getCategory(), deviceType, product.getBrand(),
					product.getColor(), product.getConnectivity(), product.getQuantityInStock(),
					product.getOriginalCost(), product.getRetailPrice(), additionalInfo });

		}
	}

	public void fastSearch(JTable table, String search) {
		if (!ErrorHandler.checkIfBarcodeIs6Digits(Integer.parseInt(search))
				|| !ErrorHandler.checkIfBarcodeExists(Integer.parseInt(search))) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Clear existing data

		for (Product product : products) {
			if (product.getBarcode() == Integer.parseInt(search)) {
				String deviceType = "";
				String additionalInfo = "";

				if (product instanceof Keyboard) {
					Keyboard keyboard = (Keyboard) product;
					deviceType = keyboard.getDeviceType().toString();
					additionalInfo = keyboard.getAdditionalInfo().toString();
				} else if (product instanceof Mouse) {
					Mouse mouse = (Mouse) product;
					deviceType = mouse.getDeviceType().toString();
					additionalInfo = Integer.toString(mouse.getAdditionalInfo());
				}

				model.addRow(new Object[] { product.getBarcode(), product.getCategory(), deviceType, product.getBrand(),
						product.getColor(), product.getConnectivity(), product.getQuantityInStock(),
						product.getRetailPrice(), additionalInfo });
			}
		}
	}

	public void miceSearch(JTable table, String search) {
		if (!ErrorHandler.checkIfInputIsNumeric(search)) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Clear existing data

		for (Product product : products) {
			if (product instanceof Mouse) {
				Mouse mouse = (Mouse) product;
				if (mouse.getAdditionalInfo() == Integer.parseInt(search)) {
					String deviceType = mouse.getDeviceType().toString();
					String additionalInfo = Integer.toString(mouse.getAdditionalInfo());

					model.addRow(new Object[] { product.getBarcode(), product.getCategory(), deviceType,
							product.getBrand(), product.getColor(), product.getConnectivity(),
							product.getQuantityInStock(), product.getRetailPrice(), additionalInfo });
				}
			}

		}
	}

}
