import experiment.CombinedReadWrite;
import experiment.MultiWayMerge;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Experiment4 {
    /**
     * Experiment 4: Multi-way merge
     *
     * @param args inputFile -> string
     *             outputFile -> string
     *             k -> int (k-th column to be sorted)
     *             M -> int (buffer/memory size)
     *             d -> int (number of files to be merged per time)
     */
    public static void main(String[] args) throws IOException {
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        int k = Integer.parseInt(args[2]);
        int M = Integer.parseInt(args[3]);
        int d = Integer.parseInt(args[4]);
        MultiWayMerge experiment = new MultiWayMerge();
        experiment.run(inputFilePath, outputFilePath, k, M, d);
    }
}
