package experiment;

import io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombinedReadWrite {
    private List<InputStream> inputs = new ArrayList<>();
    private OutputStream output;

    private void rrmerge() throws IOException {
        while (this.inputs.size() > 0) {
            List<InputStream> toRemove = new ArrayList<>();
        	for (int i = 0; i < this.inputs.size(); i++) {
                InputStream input = this.inputs.get(i);
            	if (!input.end_of_stream()) {
                    this.output.writeln(input.readln());
                } else {
                    toRemove.add(input);
                }
            }
        	this.inputs.removeAll(toRemove);
        }
        this.output.close();
    }

    // none is buffered
    public void run(List<String> files, String outputFile, int inputStream, int outputStream) throws IOException {
        switch (inputStream) {
            case 1:
                for (String filePath : files) {
                    InputStream1 input = new InputStream1();
                    input.open(filePath);
                    this.inputs.add(input);
                }
                break;
            case 2:
                for (String filePath : files) {
                    InputStream2 input = new InputStream2();
                    input.open(filePath);
                    this.inputs.add(input);
                }
                break;
            default:
                throw new IllegalArgumentException("wrong argument");
        }

        switch (outputStream) {
            case 1:
                this.output = new OutputStream1();
                break;
            case 2:
                this.output = new OutputStream2();
                break;
            default:
                throw new IllegalArgumentException("wrong argument");
        }

        output.create(outputFile);
        this.rrmerge();
    }

	// only input is buffered
	public void run(List<String> files, int inputStream, int outputStream, String outputFile, int inputB) throws IOException {
		switch (inputStream) {
			case 3:
				for (String filePath : files) {
					InputStream3 input = new InputStream3(inputB);
					input.open(filePath);
					this.inputs.add(input);
				}
				break;
			case 4:
				for (String filePath : files) {
					InputStream4 input = new InputStream4(inputB);
					input.open(filePath);
					this.inputs.add(input);
				}
				break;
			default:
				throw new IllegalArgumentException("wrong argument");
		}

		switch (outputStream) {
			case 1:
				this.output = new OutputStream1();
				break;
			case 2:
				this.output = new OutputStream2();
				break;
			default:
				throw new IllegalArgumentException("wrong argument");
		}

		output.create(outputFile);
		this.rrmerge();
	}

	// only output is buffered
    public void run(List<String> files, String outputFile, int inputStream, int outputStream, int outputB) throws IOException {
		switch (inputStream) {
			case 1:
				for (String filePath : files) {
					InputStream1 input = new InputStream1();
					input.open(filePath);
					this.inputs.add(input);
				}
				break;
			case 2:
				for (String filePath : files) {
					InputStream2 input = new InputStream2();
					input.open(filePath);
					this.inputs.add(input);
				}
				break;
			default:
				throw new IllegalArgumentException("wrong argument");
		}

		switch (outputStream) {
			case 3:
				this.output = new OutputStream3(outputB);
				break;
			case 4:
				this.output = new OutputStream4(outputB);
				break;
			default:
				throw new IllegalArgumentException("wrong argument");
		}

		output.create(outputFile);
		this.rrmerge();
    }

	// both buffered
	public void run(List<String> files, String outputFile, int inputStream, int outputStream, int inputB, int outputB) throws IOException {
        switch (inputStream) {
            case 3:
                for (String filePath : files) {
                    InputStream3 input = new InputStream3(inputB);
                    input.open(filePath);
                    this.inputs.add(input);
                }
                break;
            case 4:
                for (String filePath : files) {
                    InputStream4 input = new InputStream4(inputB);
                    input.open(filePath);
                    this.inputs.add(input);
                }
                break;
            default:
                throw new IllegalArgumentException("wrong argument");
        }

        switch (outputStream) {
            case 3:
                this.output = new OutputStream3(outputB);
                break;
            case 4:
                this.output = new OutputStream4(outputB);
                break;
            default:
                throw new IllegalArgumentException("wrong argument");
        }

        output.create(outputFile);
        this.rrmerge();
    }
}
