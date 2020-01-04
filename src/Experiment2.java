import experiment.RandomReading;

import java.io.IOException;

public class Experiment2 {
    /**
     * Experiment 2: Random Reading
     *
     * @param args filePath -> string
     *             stream_type -> int (between 1 and 4)
     *             random_jumps -> int (j)
     *             buffer_size -> int (optional if stream_type 1 or 2)
     */
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        int streamType = Integer.parseInt(args[1]);
        int random_jumps = Integer.parseInt(args[2]);
        RandomReading experiment = new RandomReading();
        if (streamType == 1 || streamType == 2) {
            experiment.run(filePath, streamType, random_jumps);
        } else {
            int bufferSize = Integer.parseInt(args[3]);
            experiment.run(filePath, streamType, random_jumps, bufferSize);
        }
    }
}
