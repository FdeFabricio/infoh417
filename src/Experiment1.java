import experiment.SequentialReading;

import java.io.IOException;

public class Experiment1 {
    /**
     * Experiment 1: Sequential Reading
     *
     * @param args filePath -> string
     *             stream_type -> int (between 1 and 4)
     *             buffer_size -> int (optional if stream_type 1 or 2)
     */
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        int streamType = Integer.parseInt(args[1]);
        SequentialReading experiment = new SequentialReading();
        if (streamType == 1 || streamType == 2) {
            experiment.run(streamType, filePath);
        } else {
            int bufferSize = Integer.parseInt(args[2]);
            experiment.run(streamType, filePath, bufferSize);
        }
    }
}
