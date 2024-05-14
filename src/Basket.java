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

    public void remove(List<Product> productList) {
        items.remove(productList);

    }

}
