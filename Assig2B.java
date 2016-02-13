/*
Tyler Protivnak
Assig2B Driver
CS445 
Feb 12, 2016
*/

public class Assig2B
{
	public static void main(String [] args)
	{
		//String inFile = args[0];
		for(int run = 0; run < 3; run++){
			if(run==0){						//StringBuilder run
				System.out.println("Testing Stringbuilder");
				
			}
			else if(run==1){				//MyStringBuilder run
				System.out.println("Testing MYStringbuilder");
				
			}
			else{							//String run
				System.out.println("Testing String");
				
			}
		}
	}
}