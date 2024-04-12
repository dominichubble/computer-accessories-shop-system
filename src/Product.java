public abstract class Product {
    private int barcode;
    private String brand;
    private ProductCategory category;
    private String color;
    private ConnectivityType connectivity;
    private double originalCost;
    private int quantityInStock;
    private double retailPrice;

    /**
     * Constructs a new Product with the specified attributes.
     *
     * @param barcode Unique 6-digit number that identifies a product.
     * @param brand Manufacturer or brand name.
     * @param color Color description of the product.
     * @param connectivity Type of connectivity (WIRED or WIRELESS).
     * @param quantityInStock Number of units available in stock.
     * @param originalCost Cost price of the product.
     * @param retailPrice Selling price of the product.
     * @param category Category of the product (e.g., KEYBOARD, MOUSE).
     */
    public Product(int barcode, String brand, String color, ConnectivityType connectivity,
                   int quantityInStock, double originalCost, double retailPrice, ProductCategory category) {
        this.barcode = barcode;
        this.brand = brand;
        this.color = color;
        this.connectivity = connectivity;
        this.quantityInStock = quantityInStock;
        this.originalCost = originalCost;
        this.retailPrice = retailPrice;
        this.category = category;
    }

    // Getters
    public int getBarcode() {
        return barcode;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public ConnectivityType getConnectivity() {
        return connectivity;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public abstract String toString();
}
