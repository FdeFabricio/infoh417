import experiment.CombinedReadWrite;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Experiment3 {
    /**
     * Experiment 3: Combined Reading and Write
     *
     * @param args outputFile -> string
     *             input_stream_type -> int (between 1 and 4)
     *             output_stream_type -> int (between 1 and 4)
     *             input_buffer_size -> int (0 if input_stream_type 1 or 2)
     *             output_buffer_size -> int (0 if output_stream_type 1 or 2)
     *             inputFiles -> List<string>
     */
    public static void main(String[] args) throws IOException {
        String outputFilePath = args[0];
        int inputStreamType = Integer.parseInt(args[1]);
        int outputStreamType = Integer.parseInt(args[2]);
        int inputB = Integer.parseInt(args[3]);
        int outputB = Integer.parseInt(args[4]);
        List<String> inputFilePaths = Arrays.asList(Arrays.copyOfRange(args, 5, args.length));

        CombinedReadWrite experiment = new CombinedReadWrite();
        if (inputStreamType == 1 || inputStreamType == 2) {
            if (outputStreamType == 1 || outputStreamType == 2) {
                experiment.run(inputFilePaths, outputFilePath, inputStreamType, outputStreamType);
            } else {
                experiment.run(inputFilePaths, outputFilePath, inputStreamType, outputStreamType, outputB);
            }
        } else {
            if (outputStreamType == 1 || outputStreamType == 2) {
                experiment.run(inputFilePaths, inputStreamType, outputStreamType, outputFilePath, inputB);
            } else {
                experiment.run(inputFilePaths, outputFilePath, inputStreamType, outputStreamType, inputB, outputB);
            }
        }
    }
}
