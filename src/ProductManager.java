import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductManager {

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

}
