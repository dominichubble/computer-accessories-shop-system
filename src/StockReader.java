import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StockReader {
	public static List<Product> readStockFile(String filename) {
		List<Product> products = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(", ");

				int barcode = Integer.parseInt(parts[0]);
				String category = parts[1];
				String type = parts[2];
				String brand = parts[3];
				String color = parts[4];
				ConnectivityType connectivity = ConnectivityType.valueOf(parts[5].toUpperCase());
				int quantity = Integer.parseInt(parts[6]);
				double cost = Double.parseDouble(parts[7]);
				double price = Double.parseDouble(parts[8]);
				String additionalInfo = parts[9];

				// Inside StockReader.java
				Product product;
				if (category.equalsIgnoreCase("keyboard")) {
					Layout layout = Layout.valueOf(additionalInfo.toUpperCase());
					DeviceType deviceType = DeviceType.valueOf(type.toUpperCase()); // Convert String to DeviceType
					product = new Keyboard(barcode, brand, color, connectivity, quantity, cost, price,
							ProductCategory.valueOf(category.toUpperCase()), deviceType, layout);
				} else if (category.equalsIgnoreCase("mouse")) {
					int buttons = Integer.parseInt(additionalInfo); // Convert additionalInfo to integer for buttons
					DeviceType deviceType = DeviceType.valueOf(type.toUpperCase());
					product = new Mouse(barcode, brand, color, connectivity, quantity, cost, price,
							ProductCategory.valueOf(category.toUpperCase()), deviceType, buttons);
				} else {
					continue; // Skip if the category is neither keyboard nor mouse
				}

				products.add(product);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sortProductsByPrice(products);
		return products;
	}

	private static void sortProductsByPrice(List<Product> products) {
		products.sort(Comparator.comparingDouble(Product::getRetailPrice));
	}
}
