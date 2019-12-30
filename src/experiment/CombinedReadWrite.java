package experiment;
import io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CombinedReadWrite {
	private List<InputStream> lins = new ArrayList<InputStream>();
	private OutputStream ons;
	private List<String> readFiles = new ArrayList<>();
	private String outputFile;
	
	public CombinedReadWrite(InputStream ins, OutputStream ons, List<String> f , int b, String outputPath) throws IOException {
		super();
		this.readFiles = f;
		for (int i = 0; i < readFiles.size(); i++) {
			String file = readFiles.get(i);
			if (ins instanceof InputStream1) {
				this.lins.add(new InputStream1());
			}
			else if (ins instanceof InputStream2) {
				this.lins.add(new InputStream2());
			}
			else if (ins instanceof InputStream3) {
				this.lins.add(new InputStream3(b));
			}
			else this.lins.add(new InputStream4(b));
			
			this.lins.get(i).open(file);
		}
		this.ons = ons;
		this.outputFile = outputPath;
	}
	
	public void rrmerge() throws IOException {
		ons.create(this.outputFile);
		String line;
		
		while(this.lins.size()!=0)
		{
		for (int i = 0; i < this.lins.size(); i++) {
			 if(!this.lins.get(i).end_of_stream()) {
				 line = this.lins.get(i).readln();
					ons.writeln(line); 
			 }
			 else {
				 this.lins.remove(i);}
			 
		}
		}
		ons.close();
	}
	}
