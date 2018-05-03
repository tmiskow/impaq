package output;

import java.io.PrintStream;

public class Display extends OutputDevice {
    public Display() {
        super();
    }

    public Display(PrintStream stream) {
        super(stream);
    }
}
