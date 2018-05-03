package database;

public class Product {
    private final String name;
    private final int price;
    private final String barcode;


    public Product(String name, int price, String barcode) {
        this.name = name;
        this.price = price;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; Price: " + price;
    }
}
