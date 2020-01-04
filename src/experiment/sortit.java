package experiment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class sortit {
	
	static int numofsubf=1; //what is latest file rank in
	static Queue<String> namesfq = new LinkedList<>(); //to save the name of all subfile in queue

	public static void main(String[] args) throws IOException {
		File f = new File("/Users/chiau/Desktop/SA/new/link_type.csv");  
		int k=2,M=60,d=4;
		sort(f,k,M);
		merge(f,k,d);
	}
	
	public static void merge(File orif, int orik, int orid) throws IOException {
		
		Queue<String> pq = new PriorityQueue<String>(new Comparator<String>() {	 //create pq to sort the kth column
	        public int compare(String e1,String e2) {
	        	String item1[] = e1.split(",");
	        	String item2[] = e2.split(",");
	        	String  k1= item1[orik].trim(); 	// because the first character is an index, do not need to -1
	        	String  k2= item2[orik].trim();
	            return k1.compareTo(k2); 	//given the smallest one
	        }
		});
		
		
		int cursubf=numofsubf-1; //check how many subfiles are there in the queue currently
		if(orid>cursubf) {
			orid=cursubf;
		}
		
		String[] name=new String[orid];		//save the name of file which we have already polled from q
		String line=""; 		// save the line which we read in the file
		
		FileReader[] fr=new FileReader[orid]; 	//create d numbers of filereader
		BufferedReader[] br = new BufferedReader[orid]; 
		boolean initial=true; 	//to check if this is the 1st time to put the 1st of each file or not
		int tuplefrom; // the smallest tuple from which subfile
		int leftsf=orid; // save the left subfile number in the priority queue
		String writeinsf; //this string is going to be written to the subfile
		
		
		do {		
			
			leftsf=Math.min(namesfq.size(), orid);  // chech how many subfiles are left in the queue
			initial=true; 
			
				//start to merge subfile
			
				do {
					//start to add a record from each file to priority queue
					if(initial) {
						
						if (orid>leftsf) { // reset the orid if the number of files are less than d in the queue
							orid=leftsf;
						}
						
						for(int numf=0;numf<orid;numf++) {	
							
							try {	
								name[numf]=namesfq.poll();  // use name array to save the subfile name which we have already polled from queue	
								fr[numf]=new FileReader("/Users/chiau/Desktop/SA/new/"+name[numf]);
								br[numf]=new BufferedReader(fr[numf]); //read the file
								line=br[numf].readLine();		
								String chtype=String.valueOf(numf); //since the priority queue can only accepted string type
								pq.add(chtype+","+line);	
							}catch (IOException e) {
								System.out.println(e);	
							}
							
						}
						initial=false; //after putting the 1st record from each file, we set it to false
					}
					//finish to add a record from each file to priority queue
		
					String small=pq.poll();  // use var. small to save the smallest tuple in the subfile		
					writeinsf=writein(orif,small.substring(2),numofsubf); //write the new subfile into disk(orif to get the original file name)		
					tuplefrom=Integer.parseInt(small.substring(0,1)); //save where is this smallest tuple from
		
					if((line=br[tuplefrom].readLine())!= null){  // check if there are any tuple in this subfile or not
						pq.add(tuplefrom+","+line);  // add the next tuple to the priority queue	
					}else {
						br[tuplefrom].close(); // if there are no tuples in the subfile, we close the buffer
						leftsf--; //the number of left subfiles in queue should be -1
					}
				
				}while(leftsf>0); // check are there any other tuples in the priority queue, if so, keep merging until no tuples needed to be merged
				
				//end of merging subfile
		
			namesfq.add(writeinsf); //after mergeing, we add the name of new subfile which we created to the queue
			numofsubf++; // the no. of subfile should +1 
		
		}while(namesfq.size()>1); // check if there are more than one file in queue or not, if so, we take d subfiles from queue until there is only "one" file in the queue
		
	}
	
	
	public static void sort(File orif, int orik, int oriM) {
		
		Queue<String> sortbuf = new PriorityQueue<String>(new Comparator<String>() { //sortbuf is using priority queue to sort tuples in every subfile
	        public int compare(String e1,String e2) {
	        	String item1[] = e1.split(",");
	        	String item2[] = e2.split(",");
	        	String  k1= item1[orik-1].trim();  // since the index of array starts from 0, orik need to -1
	        	String  k2= item2[orik-1].trim();
	            return k1.compareTo(k2); //return the smallest one 
	        }
		});
		
		String next="";  // save the tuple which we read 
		String nsf="";  // save the name of subfile temporarily
		int dupM=oriM; // dupM means the left buffer in M
		int count=0; //count how many tuples should we write in the subfile
		boolean flag=true; // store the last tuples in the buffer 
		
		FileReader fr;
		
		try {
			
			 fr=new FileReader(orif);
			 BufferedReader br=new BufferedReader(fr); //read the file
			 
			 	do {
			 		while((next=br.readLine())!= null) {
				
			 			if(dupM<next.length()) { //check if we have enough buffer for the next tuple or not  
			 				
			 				for(int start=0;start<count;start++) { //iterate to put tuples into subfile
							 nsf=writein(orif,sortbuf.poll(),numofsubf);  
			 				}
			 				
			 				namesfq.add(nsf);
			 				dupM=oriM;	 // reset the buffer to be the original size
			 				count=0;	// reset the number of count 
			 				numofsubf++;
			 			}
			 			
			 			sortbuf.add(next);
			 			dupM-=next.length();  
			 			count++;	 
			 		}
				 
			 		for(int last=0;last<count;last++) { //the end of the file, but we need to store all left tuples in buffer to subfile
			 			nsf=writein(orif,sortbuf.poll(),numofsubf);   
			 		}
			 		
			 		namesfq.add(nsf);
			 		numofsubf++;
			 		flag=false;
				 
			}while(flag);
  			  
		 }catch (IOException e) {
			   System.out.println(e);
		 }
	}
	
	public static String writein(File wf, String in,int numofsf) throws IOException {
		
		BufferedWriter fw = null;
			
		try {
			
			FileOutputStream sf=new FileOutputStream("/Users/chiau/Desktop/SA/new/sub"+numofsf+wf.getName(),true);
			fw = new BufferedWriter(new OutputStreamWriter(sf,"UTF-8"));
			fw.append(in);	
			fw.newLine();
			fw.flush(); 
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		fw.close();
		
		return "sub"+numofsf+wf.getName();
		
	}

}
