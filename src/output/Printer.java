package output;

import java.io.PrintStream;

public class Printer extends OutputDevice {
    public Printer() {
        super();
    }

    public Printer(PrintStream stream) {
        super(stream);
    }
}
