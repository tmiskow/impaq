package database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, Product> map = new HashMap<>();

    public Database(Collection<Product> products) {
        for (Product product : products) {
            map.put(product.getBarcode(), product);
        }
    }

    public Product selectProduct(String barcode) {
        return map.getOrDefault(barcode, null);
    }
}
