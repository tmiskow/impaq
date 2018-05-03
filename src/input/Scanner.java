package input;

import database.Product;

import java.util.List;
import java.util.ListIterator;

public class Scanner {
    private List<Product> products;
    private ListIterator<Product> iterator;

    public Scanner(List<Product> products) {
        this.products = products;
        this.iterator = products.listIterator();
    }

    public String next() {
        if (iterator.hasNext()) {
            return iterator.next().getBarcode();
        } else {
            return "exit";
        }
    }
}
