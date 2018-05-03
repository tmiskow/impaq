import input.Scanner;
import database.Database;
import database.Product;
import output.Display;
import output.Printer;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private static final String EXIT_COMMAND = "exit";

    private Scanner scanner;
    private Database database;
    private Display display;
    private Printer printer;

    private int totalSum = 0;
    private List<Product> scannedProducts = new ArrayList<>();
    private boolean saleInProgress = true;

    public Sale(List<Product> products, Database database, Display display, Printer printer) {
        this.database = database;
        this.scanner = new Scanner(products);
        this.display = display;
        this.printer = printer;
    }

    private void processSingleProduct(String input) {
        switch (input) {
            case "":
                display.print("Invalid bar-code");
                break;
            case EXIT_COMMAND:
                saleInProgress = false;
                break;
            default:
                Product product = database.selectProduct(input);

                if (product != null) {
                    display.print(product);
                    scannedProducts.add(product);
                    totalSum += product.getPrice();
                } else {
                    display.print("Product not found");
                }
                break;
        }
    }

    private void printSummary() {
        for (Product product : scannedProducts) {
            printer.print(product);
        }

        printer.print("Total sum: " + totalSum);
        display.print("Total sum: " + totalSum);
    }

    public void perform() {
        while (saleInProgress) {
            String input = scanner.next();
            processSingleProduct(input);
        }

        printSummary();
    }
}
