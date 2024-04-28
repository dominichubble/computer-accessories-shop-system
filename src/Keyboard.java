public class Keyboard extends Product {
    private DeviceType deviceType;
    private Layout layout;

    public Keyboard(int barcode, String brand, String color, ConnectivityType connectivity,
                    int quantityInStock, double originalCost, double retailPrice, 
                    ProductCategory category, DeviceType deviceType, Layout layout) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.deviceType = deviceType;
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
               "barcode=" + getBarcode() +
               ", brand='" + getBrand() + '\'' +
               ", color='" + getColor() + '\'' +
               ", connectivity=" + getConnectivity() +
               ", quantityInStock=" + getQuantityInStock() +
               ", originalCost=" + getOriginalCost() +
               ", retailPrice=" + getRetailPrice() +
               ", category=" + getCategory() +
               ", deviceType='" + deviceType + '\'' +
               ", layout='" + layout + '\'' +
               '}';
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Layout getAdditionalInfo() {
        return layout;
    }

    public void setAdditionalInfo(Layout layout) {
        this.layout = layout;
    }
}
