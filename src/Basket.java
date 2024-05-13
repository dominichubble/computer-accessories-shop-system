import java.util.ArrayList;
import java.util.List;

public class Basket {
	private List<List<Product>> items = new ArrayList<>();

    public void add(List<Product> productList) {
        items.add(productList);
    }
    
    public List<List<Product>> getItems() {
        return items;
    }

    public void removeItem(Product product) {
        items.removeIf(itemList -> !itemList.isEmpty() && itemList.get(0).equals(product));
    }

}
