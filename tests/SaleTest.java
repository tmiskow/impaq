import database.Database;
import database.Product;
import org.junit.Before;
import org.junit.Test;
import output.Display;
import output.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SaleTest {
    private Display display;
    private ByteArrayOutputStream displayContent;

    private Printer printer;
    private ByteArrayOutputStream printerContent;

    private List<Product> oneValidProductList;
    private List<Product> oneInvalidProductList;
    private List<Product> threeValidProductsList;

    @Before
    public void setUp() {
        displayContent = new ByteArrayOutputStream();
        display = new Display(new PrintStream(displayContent));

        printerContent= new ByteArrayOutputStream();
        printer = new Printer(new PrintStream(printerContent));

        oneValidProductList = new ArrayList<>();
        oneValidProductList.add(new Product("Milk", 3, "1111111111"));

        oneInvalidProductList = new ArrayList<>();
        oneInvalidProductList.add(new Product("Milk", 3, ""));

        threeValidProductsList = new ArrayList<>();
        threeValidProductsList.add(new Product("Milk", 3, "1111111111"));
        threeValidProductsList.add(new Product("Cabbage", 4, "2222222222"));
        threeValidProductsList.add(new Product("Chicken", 5, "3333333333"));
    }

    @Test
    public void noProducts() {
        List<Product> products = Collections.emptyList();
        Database database = new Database(Collections.emptyList());

        Sale sale = new Sale(products, database, display, printer);
        sale.perform();

        String expectedOutput = "Total sum: 0\n";
        assertEquals(expectedOutput, displayContent.toString());
        assertEquals(expectedOutput, printerContent.toString());
    }

    @Test
    public void productNotFound() {
        List<Product> products = oneValidProductList;
        Database database = new Database(Collections.emptyList());

        Sale sale = new Sale(products, database, display, printer);
        sale.perform();

        String expectedDisplayOutput = "Product not found\nTotal sum: 0\n";
        assertEquals(expectedDisplayOutput, displayContent.toString());

        String expectedPrinterOutput = "Total sum: 0\n";
        assertEquals(expectedPrinterOutput, printerContent.toString());
    }

    @Test
    public void oneValidProduct() {
        List<Product> products = oneValidProductList;;
        Database database = new Database(products);

        Sale sale = new Sale(products, database, display, printer);
        sale.perform();

        String expectedOutput = "Name: Milk; Price: 3\nTotal sum: 3\n";
        assertEquals(expectedOutput, displayContent.toString());
        assertEquals(expectedOutput, printerContent.toString());
    }

    @Test
    public void invalidProduct() {
        List<Product> products = oneInvalidProductList;
        Database database = new Database(products);

        Sale sale = new Sale(products, database, display, printer);
        sale.perform();

        String expectedDisplayOutput = "Invalid bar-code\nTotal sum: 0\n";
        assertEquals(expectedDisplayOutput, displayContent.toString());

        String expectedPrinterOutput = "Total sum: 0\n";
        assertEquals(expectedPrinterOutput, printerContent.toString());
    }

    @Test
    public void multipleValidProducts() {
        List<Product> products = threeValidProductsList;
        Database database = new Database(products);

        Sale sale = new Sale(products, database, display, printer);
        sale.perform();

        String expectedOutput = "Name: Milk; Price: 3\n" +
                "Name: Cabbage; Price: 4\n" +
                "Name: Chicken; Price: 5\n" +
                "Total sum: 12\n";
        assertEquals(expectedOutput, displayContent.toString());
        assertEquals(expectedOutput, printerContent.toString());
    }
}