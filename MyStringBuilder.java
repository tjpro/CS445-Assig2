// CS 0445 Spring 2016
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
			length = s.length();
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		if (s == null || s.length == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s[0]);
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode(s[i]);
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
			length = s.length;
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		
		if (b == null || b.length == 0) // Special case for empty String
		{					 			  // or null reference
			return this;
		}
		else{
			
			CNode currNode = new CNode(b.firstC.data);
			CNode temp = b.firstC.next;
			CNode front = currNode;
			
			while(true){
				CNode newNode = new CNode(temp.data);
				currNode.next = newNode;
				currNode = newNode;
				
				if(temp.next == null){
					break;
				}
				temp = temp.next;
			}
			lastC.next = front;
			lastC = currNode;
			length+=b.length;
			return this;
		}
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			return this;
		}
		else
		{
			boolean firstPt = false;
			if(this.firstC == null){
				this.firstC = new CNode(s.charAt(0));
				this.lastC = this.firstC;
				firstPt = true;
			}
			
			CNode currNode = lastC;
			int r = 0;
			if(firstPt){
				r++;
			}
			
			for (int i = r; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
			length+=s.length();
		}
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		if (c == null || c.length == 0) // Special case for empty String
		{					 			  // or null reference
			return this;
		}
		else
		{
			boolean firstPt = false;
			if(this.firstC == null){
				this.firstC = new CNode(c[0]);
				this.lastC = this.firstC;
				firstPt = true;
			}
			CNode currNode = lastC;
			int r = 0;
			if(firstPt){
				r++;
			}
			
			for (int i = r; i < c.length; i++){
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
			length+=c.length;
		}
		return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{

		if (c == '\u0000'){					 			  
			return this;
		}
		else
		{
			if(this.firstC == null){
				this.firstC = new CNode(c);
				this.lastC = this.firstC;
			}
			
			CNode currNode = lastC;
			CNode newNode = new CNode(c);
			currNode.next = newNode;
			currNode = newNode;
			length++;
			lastC = currNode;
		}	
		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index<0||index>length){
			throw new IndexOutOfBoundsException();
		}
		else{
			int i = 0;
			CNode currNode = firstC;
			while(true){
				if((index)==i){
					break;
				}
				currNode = currNode.next;
				i++;
			}
			return currNode.data;
		}
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		if(start<0||start>length){
			return this;
		}
		else if(end<0){
			throw new IndexOutOfBoundsException();						
		}
		else if(start>=end){
			return this;
		}
		else if(start == 0 && end>= length){
			firstC=null;
			lastC = null;
			length = 0;
			return this;
		}
		else{
			int i = 0;
			CNode currNode1 = firstC;
			CNode front = firstC;
			while(true){
				if((start)==i){
					break;
				}
				front = currNode1;
				currNode1 = currNode1.next;
				i++;
			}
			CNode currNode2 = currNode1;
			CNode back = currNode1;
			
			while(true){
				if((end)==i||currNode2.next==null){
					break;
				}
				currNode2 = currNode2.next;
				back = currNode2;
				i++;
			}
			front.next = back;
			if(start == 0){
				firstC = currNode2;
				length-=end;
			}
			
			if(end > length){
				lastC = currNode1;
				lastC.next = null;
				length=start;
			}
			return this;
		}
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(index<0||index>length){
			return this;
		}
		else if(index == 0){
			firstC = firstC.next;
			return this;
		}
		else if(index == length-1){
			int i = 0;
			CNode currNode = firstC;
			while(true){
				if(currNode.next == lastC){
					currNode.next = null;
					lastC = currNode;
					break;
				}
				currNode = currNode.next;
			}
			length--;
		}
		else{
			int i = 0;
			CNode currNode = firstC;
			while(true){
				if((index-1)==i){
					currNode.next = currNode.next.next;
					break;
				}
				currNode = currNode.next;
				i++;
			}
			length--;
		}
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		int j = 0;
		CNode currNode = firstC;
		boolean test = false;
		while(true){
			CNode temp = currNode;
			
			if(length==j || currNode==null){
				break;
			}
			
			if(temp.data == str.charAt(0)){
				for(int i = 1; i<str.length();i++){
					temp = temp.next;
					if(temp == null || temp.data != str.charAt(i)){
						test = false;
						break;
					}
					else{
						test = true;
					}
				}
			}
			if(test){
				return j;
			}
			currNode = currNode.next;
			j++;
		}
		return -1;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{																//FIX COUNTING
		if(offset<0||offset>length){
			return this;
		}
		
		else if(offset == length){
			append(str);
		}
		
		else if(this.firstC == null){
			this.firstC = new CNode(str.charAt(0));
			this.lastC = this.firstC;
			CNode currNode = lastC;

			for (int i = 1; i < str.length(); i++)
			{
				CNode newNode = new CNode(str.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
		}
		
		else if(offset == 0){
			CNode tempNode = firstC;
			this.firstC = new CNode(str.charAt(0));
			CNode currNode = firstC;
			
			for (int j = 1; j < str.length(); j++)
			{
				CNode newNode = new CNode(str.charAt(j));
				currNode.next = newNode;
				currNode = newNode;
			}
			currNode.next = tempNode;
		}
		
		else{
			CNode tempNode;
			int i = 0;
			CNode currNode = firstC;
			while(true){
				if((offset-1)==i){
					tempNode = currNode.next;
					break;
				}
				currNode = currNode.next;
				i++;
			}
			
			for (int j = 0; j < str.length(); j++)
			{
				CNode newNode = new CNode(str.charAt(j));
				currNode.next = newNode;
				currNode = newNode;
			}
			currNode.next = tempNode;
		}
		length+=str.length();
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if(offset<0||offset>length){
			return this;
		}
		
		else if(offset == length){
			if(this.firstC == null){
				this.firstC = new CNode(c);
				this.lastC = this.firstC;
			}
			
			CNode currNode = lastC;
			CNode newNode = new CNode(c);
			currNode.next = newNode;
			currNode = newNode;
			length++;
			lastC = currNode;
		}
		
		else if(this.firstC == null){
			this.firstC = new CNode(c);
			this.lastC = this.firstC;
		}
		
		else if(offset == 0){
			CNode tempNode = firstC;
			this.firstC = new CNode(c);
			CNode currNode = firstC;
			currNode.next = tempNode;
		}
		
		else{
			CNode tempNode = new CNode(c);
			int i = 0;
			CNode currNode = firstC;
			while(true){
				if((offset-1)==i){
					tempNode.next = currNode.next;
					currNode.next = tempNode;
					break;
				}
				currNode = currNode.next;
				i++;
			}
		}
		length++;
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		if(offset<0||offset>length){
			return this;
		}
		
		else if(offset == length){
			append(c);
		}
		
		else if(this.firstC == null){
			this.firstC = new CNode(c[0]);
			this.lastC = this.firstC;
			CNode currNode = lastC;

			for (int i = 1; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
		}
		
		else if(offset == 0){
			CNode tempNode = firstC;
			this.firstC = new CNode(c[0]);
			CNode currNode = firstC;
			
			for (int j = 1; j < c.length; j++)
			{
				CNode newNode = new CNode(c[j]);
				currNode.next = newNode;
				currNode = newNode;
			}
			currNode.next = tempNode;
		}
		
		else{
			CNode tempNode;
			int i = 0;
			CNode currNode = firstC;
			while(true){
				if((offset-1)==i){
					tempNode = currNode.next;
					break;
				}
				currNode = currNode.next;
				i++;
			}
			
			for (int j = 0; j < c.length; j++)
			{
				CNode newNode = new CNode(c[j]);
				currNode.next = newNode;
				currNode = newNode;
			}
			currNode.next = tempNode;
		}
		length+=c.length;
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}


	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.
	public MyStringBuilder replace(int start, int end, String str)
	{
		if(start<0||start>length){
			return this;
		}
		else if(end<0){
			throw new IndexOutOfBoundsException();						///////UPDATE LENGTH
		}
		else if(start>=end){
			return this;
		}
		else if(start == 0 && end>= length){
			firstC=null;
			lastC = null;
			length = 0;
			firstC = new CNode(str.charAt(0));
			length = 1;
			CNode currNode = firstC;
			for (int i = 1; i < str.length(); i++)
			{
				CNode newNode = new CNode(str.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
			length = str.length();
			return this;
		}
		else{
			int i = 0;
			CNode currNode1 = firstC;
			CNode front = firstC;
			while(true){
				if((start)==i){
					break;
				}
				front = currNode1;
				currNode1 = currNode1.next;
				i++;
			}
			CNode currNode2 = currNode1;
			CNode back = currNode1;
			
			CNode tempNode = new CNode(str.charAt(0));
			CNode currNode = tempNode;
			for (int j = 1; j < str.length(); j++)
			{
				CNode newNode = new CNode(str.charAt(j));
				currNode.next = newNode;
				currNode = newNode;
			}			
			
			while(true){
				if(end==i||currNode2.next==null){
					break;
				}
				currNode2 = currNode2.next;
				back = currNode2;
				i++;
			}
			
			if(end > length-1){
				lastC = currNode;
				lastC.next = null;
			}
			else{
				currNode.next = back;
			}

			if(start == 0){
				firstC = tempNode;
			}
			else{
				front.next = tempNode;
			}
			length+=str.length();
			length-=(start-end);
		}
		return this;
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		if (firstC == null) {					 			  
			return ("");
		}
		else{
			int i = 0;
			char[] build = new char[length];
			CNode currNode = firstC;
			while(true){
				if(i==length||currNode==null){
					break;
				}
				build[i] = currNode.data;
				currNode = currNode.next;
				i++;
			}
			char [] sub = new char[end-start];
			for(int j = 0; j<sub.length;j++){
				sub[j] = build[start+j];
			}
			return new String(sub);
		}
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		if (firstC == null) {					 			  
			return ("");
		}
		else{
			int i = 0;
			char[] build = new char[length];
			CNode currNode = firstC;
			while(true){
				if(i==length||currNode==null){
					break;
				}
				build[i] = currNode.data;
				currNode = currNode.next;
				i++;
			}
			return new String(build);
		}
	}
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
