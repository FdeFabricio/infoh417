import experiment.SequentialReading;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SequentialReading experiment1 = new SequentialReading();
        System.out.println(experiment1.length1("input/kind_type.csv"));
    }
}