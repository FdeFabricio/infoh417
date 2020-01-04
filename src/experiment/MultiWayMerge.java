package experiment;

import io.InputStream;
import io.InputStream2;
import io.OutputStream;
import io.OutputStream2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class MultiWayMerge {
    private final String OUTPUT_FOLDER = "output/merge/";
    private Queue<String> subfilesQueue = new LinkedList<>();

    public void run(String filePath, String outputFilePath, int k, int M, int d) throws IOException {
        this.sort(filePath, k, M);
        this.merge(k, d, outputFilePath);
    }

    private void sort(String filePath, int k, int M) throws IOException {
        Queue<String> sortbuf = new PriorityQueue<>((e1, e2) -> { // priority queue bases on the k-th column
            String item1[] = e1.split(",");
            String item2[] = e2.split(",");
            String k1, k2;
            try {
                k1 = item1[k - 1].trim();
                k2 = item2[k - 1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("ERROR: k is greater than the number of columns");
            }
            return k1.compareTo(k2);
        });

        InputStream inputStream = new InputStream2();
        OutputStream outputStream = new OutputStream2();
        inputStream.open(filePath);
        String line;  // save current read line
        int memoryLeft = M;
        int subfileCount = 0;

        while (!inputStream.end_of_stream()) {
            line = inputStream.readln();
            if (memoryLeft < line.length()) { //check if we have enough buffer for the next tuple or not
                String outputFileName = OUTPUT_FOLDER + "s" + subfileCount + ".txt";
                outputStream.create(outputFileName);
                while (sortbuf.size() > 0) {
                    outputStream.writeln(sortbuf.poll());
                }
                subfilesQueue.add(outputFileName);
                outputStream.close();
                memoryLeft = M;     // reset the buffer to be the original size
                subfileCount++;
            }
            sortbuf.add(line);
            memoryLeft -= line.length();
        }

        // this outputs the remaining lines of the buffer into another subfile
        String outputFileName = OUTPUT_FOLDER + "s" + subfileCount + ".txt";
        outputStream.create(outputFileName);
        while (sortbuf.size() > 0) {
            outputStream.writeln(sortbuf.poll());
        }
        subfilesQueue.add(outputFileName);
        outputStream.close();
    }

    private void merge(int k, int d, String outputFilePath) throws IOException {
        int subfilesCount = 0;
        List<InputStream> inputStreams;
        Queue<String> mergingPQ = new PriorityQueue<>((e1, e2) -> {
            String[] item1 = e1.split(",");
            String[] item2 = e2.split(",");
            String k1 = item1[k].trim(); // because the first index is used for merging
            String k2 = item2[k].trim();
            return k1.compareTo(k2);
        });

        while (this.subfilesQueue.size() > 1) {
            inputStreams = new ArrayList<>();
            OutputStream output = new OutputStream2();
            String outputFileName = OUTPUT_FOLDER + "m" + subfilesCount + ".txt";
            output.create(outputFileName);

            // read the d subfiles
            for (int i = 0; i < d; i++) {
                if (this.subfilesQueue.isEmpty()) {
                    break;
                }
                InputStream inputStream = new InputStream2();
                inputStream.open(this.subfilesQueue.poll());
                inputStreams.add(inputStream);
            }

            // add first line of each subfile to merging queue
            for (int j = 0; j < inputStreams.size(); j++) {
                InputStream subfile = inputStreams.get(j);
                mergingPQ.add(j + "," + subfile.readln());
            }

            while (!mergingPQ.isEmpty()) {
                String[] smallestRecords = mergingPQ.poll().split(",");
                int smallestIndex = Integer.parseInt(smallestRecords[0]);
                String smallestLine = String.join(",", Arrays.copyOfRange(smallestRecords, 1, smallestRecords.length));
                output.writeln(smallestLine);
                if (!inputStreams.get(smallestIndex).end_of_stream()) {
                    mergingPQ.add(smallestIndex + "," + inputStreams.get(smallestIndex).readln());
                }
            }

            subfilesQueue.add(outputFileName);
            subfilesCount++;
            output.close();
        }

        Files.move(Paths.get(this.subfilesQueue.poll()), Paths.get(outputFilePath));
    }
}
