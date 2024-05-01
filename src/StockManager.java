import java.io.*;
import java.util.*;

public class StockManager {
    private List<Product> products;
    private String filePath;

    public StockManager(String filePath) {
        this.filePath = filePath;
        this.products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Product product = parseProduct(line);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the stock file: " + e.getMessage());
        }
    }

    private Product parseProduct(String line) {
        String[] data = line.split(", ");
        if (data.length == 10) {
            int barcode = Integer.parseInt(data[0]);
            String category = data[1];
            DeviceType type = DeviceType.valueOf(data[2].toUpperCase());
            String brand = data[3];
            String color = data[4];
            ConnectivityType connectivity = ConnectivityType.valueOf(data[5].toUpperCase());
            int quantity = Integer.parseInt(data[6]);
            double cost = Double.parseDouble(data[7]);
            double price = Double.parseDouble(data[8]);
            String additionalInfo = data[9];

            if ("keyboard".equalsIgnoreCase(category)) {
                return new Keyboard(barcode, brand, color, connectivity, quantity, cost, price,
                                    ProductCategory.KEYBOARD, type, Layout.valueOf(additionalInfo.toUpperCase()));
            } else if ("mouse".equalsIgnoreCase(category)) {
                return new Mouse(barcode, brand, color, connectivity, quantity, cost, price,
                                 ProductCategory.MOUSE, type, Integer.parseInt(additionalInfo));
            }
        }
        return null;
    }

    public void addOrUpdateProduct(Product product) {
        int index = findProductIndexByBarcode(product.getBarcode());
        if (index != -1) {
            products.set(index, product); // Update existing product
        } else {
            products.add(product); // Add new product if not found
        }
        saveProducts();
    }

    private int findProductIndexByBarcode(int barcode) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getBarcode() == barcode) {
                return i;
            }
        }
        return -1;
    }

    private void saveProducts() {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (Product product : products) {
                writer.println(productToFileFormat(product));
            }
        } catch (IOException e) {
            System.err.println("Error writing to the stock file: " + e.getMessage());
        }
    }

    private String productToFileFormat(Product product) {
        String category = product.getCategory().toString().toLowerCase();
        String type;
        String additionalInfo;

        if (product instanceof Keyboard) {
            Keyboard keyboard = (Keyboard) product;
            type = keyboard.getDeviceType().name(); // Convert enum DeviceType to String
            additionalInfo = keyboard.getAdditionalInfo().name(); // Convert enum Layout to String
        } else if (product instanceof Mouse) {
            Mouse mouse = (Mouse) product;
            type = mouse.getDeviceType().name().toLowerCase(); // Convert enum DeviceType to String
            additionalInfo = Integer.toString(mouse.getAdditionalInfo());
        } else {
            // Default or error handling
            type = "unknown";
            additionalInfo = "unknown";
        }

        return String.format("%d, %s, %s, %s, %s, %s, %d, %.2f, %.2f, %s",
                             product.getBarcode(),
                             category,
                             type,
                             product.getBrand(),
                             product.getColor(),
                             product.getConnectivity().toString().toLowerCase(),
                             product.getQuantityInStock(),
                             product.getOriginalCost(),
                             product.getRetailPrice(),
                             additionalInfo);
    }



}
