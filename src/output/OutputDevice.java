package output;

import java.io.PrintStream;

public abstract class OutputDevice {
    private PrintStream stream;

    public OutputDevice() {
        this(System.out);
    }

    public OutputDevice(PrintStream stream) {
        this.stream = stream;
    }

    public void print(Object object) {
        stream.println(object);
    }
}
