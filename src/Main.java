import experiment.CombinedReadWrite;
import experiment.RandomReading;
import experiment.SequentialReading;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import io.*;
public class Main {
    public static void main(String[] args) throws IOException {
//        SequentialReading experiment1 = new SequentialReading();
//        System.out.println(experiment1.length1("input/kind_type.csv"));
//        RandomReading experiment2 = new RandomReading();
//        System.out.println(experiment2.randjump1("input/kind_type.csv",200));
        InputStream1 ins1 = new InputStream1();
        OutputStream1 ons1 = new OutputStream1();
        List<String> f = new ArrayList<>();
        f.add("input/kind_type.csv");
        f.add("input/company_type.csv");
        String outputPath = "output/test.csv";
        CombinedReadWrite rw = new CombinedReadWrite(ins1, ons1, f, 0, outputPath);
        rw.rrmerge();
    }
}