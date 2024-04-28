public class Mouse extends Product {
    private DeviceType deviceType;
    private int buttons;  // Number of buttons

    public Mouse(int barcode, String brand, String color, ConnectivityType connectivity,
                 int quantityInStock, double originalCost, double retailPrice, 
                 ProductCategory category, DeviceType deviceType, int buttons) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.deviceType = deviceType;
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return "Mouse{" +
               "barcode=" + getBarcode() +
               ", brand='" + getBrand() + '\'' +
               ", color='" + getColor() + '\'' +
               ", connectivity=" + getConnectivity() +
               ", quantityInStock=" + getQuantityInStock() +
               ", originalCost=" + getOriginalCost() +
               ", retailPrice=" + getRetailPrice() +
               ", category=" + getCategory() +
               ", deviceType='" + deviceType + '\'' +
               ", buttons=" + buttons +
               '}';
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public int getAdditionalInfo() {
        return buttons;
    }

    public void setAdditionalInfo(int buttons) {
        this.buttons = buttons;
    }

}
