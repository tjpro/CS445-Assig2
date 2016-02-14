/*
Tyler Protivnak
Assig2B Driver
CS445 
Feb 12, 2016
*/

import java.io.*;

public class Assig2B
{
	public static void main(String [] args) throws IOException
	{
		//String inFile = args[0];
		for(int run = 0; run < 3; run++){
			if(run==0){						//StringBuilder run
				System.out.println("Testing Stringbuilder");
				
				double start = System.nanoTime();
				
				StringBuilder s = new StringBuilder();
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				int currChar;
				int charCount = 0;
				
				while ((currChar = br.read()) != -1) {
					s.append((char)currChar);
					charCount++;
				}
				br.close();
				
				while (s.length()!=0) {
					s.delete(0,1);
				}
				
				BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
				while ((currChar = br2.read()) != -1) {
					s.insert((s.length()/2),(char)currChar);
				}
				br2.close();
				
				double end = System.nanoTime();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Operation: " + ((end-start)/charCount));
				
			}
			else if(run==1){				//MyStringBuilder run
				System.out.println("\nTesting MyStringbuilder");
				
				double start = System.nanoTime();
				
				MyStringBuilder s = new MyStringBuilder();
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				int currChar;
				int charCount = 0;
				
				while ((currChar = br.read()) != -1) {
					s.append((char)currChar);
					charCount++;
				}
				br.close();
				
				while (s.length()!=0) {
					s.delete(0,1);
				}
				
				BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
				while ((currChar = br2.read()) != -1) {
					s.insert((s.length()/2),(char)currChar);
				}
				br2.close();
				
				double end = System.nanoTime();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Operation: " +((end-start)/charCount));
				
			}
			else{							//String run
				System.out.println("\nTesting String");
				
				double start = System.nanoTime();
				
				String s = "";
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				int currChar;
				int charCount = 0;
				
				while ((currChar = br.read()) != -1) {
					s.concat((String)(String.valueOf((char)currChar)));
					charCount++;
				}
				br.close();
				
				while (s.length()!=0) {
					s = s.substring(0, 0) + s.substring(1);
				}
				
				BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
				while ((currChar = br2.read()) != -1) {
					s = s.substring(0, (s.length()/2)) + String.valueOf((char)currChar) + s.substring((s.length()/2));
				}
				br2.close();
				
				double end = System.nanoTime();
				System.out.println("Elapsed Time: "+(end-start));
				System.out.println("Average Time per Operation: " +((end-start)/charCount));
				
			}
		}
	}
}