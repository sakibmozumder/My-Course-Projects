import java.util.*;
import java.io.*;
@SuppressWarnings("unused")
public class WordPuzzle {

	static MyHashTable<String> dictWord = new MyHashTable<>( );
	static MyHashTable<String> dictPref = new MyHashTable<>( );
	static MyHashTable<String> PuzzleWord = new MyHashTable<>( );
	static ArrayList<String> PuzzleWordList = new ArrayList<>( );
	
	
	
	private static boolean isPrefix(String prefix)
	{
		return dictPref.contains(prefix);
	}
	
	private static boolean isWord(String word)
	{
		return dictWord.contains(word);
	}
	
	//load dictionary to a hash table
	private static void loadTable(MyHashTable<String> x, String FileName)
	{
		try
	       {
				FileReader fr = new FileReader(FileName);
				BufferedReader br = new BufferedReader(fr);	            

	            String prefix;
	            
	            while((prefix = br.readLine()) != null)
	            {           	
	            	
					x.insert(prefix);
	            	
	            }
	            br.close();
	           
	        }
	        catch(IOException e)
	        {
	            System.out.println("File not found");
	        }
	}
	
	
	//Load prefixes to a hash table
	private static void makePrefix(MyHashTable<String> x, String FileName)
	{
		try
	       {
	            FileReader fr = new FileReader(FileName);
	            BufferedReader br = new BufferedReader(fr);

	            String dictionary;
	            
	            while((dictionary = br.readLine()) != null)
	            {
	            	
	            	for(int i=0;i<dictionary.length();i++) 
	            	{
	            		char[] prefix = new char[i+1];	
	            		for (int j=0;j<i+1;j++) 
	            		{
	            			prefix [j] = dictionary.charAt(j);		            			            			
	            		}
	            		String pref=new String(prefix);
	            		x.insert(pref);
	            		  		
	            	}
	            	
	            }
	            br.close();
	        }
	        catch(IOException e)
	        {
	            System.out.println("File not found");
	        }
	}
	
	//Create a Puzzle out of Random Characters
	private static char[][] GeneratePuzzle(int row, int column)
	{
		
		
		
			char [][] randomChar = new char[row][column];
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<column;j++) 
				{
					Random rnd = new Random();
					char c = (char) ('a' + rnd.nextInt(26));
					randomChar[i][j]=c;
					System.out.print(randomChar[i][j]+" ");				
				}
				System.out.println();
			}
			return randomChar;
		
	}
	
	private static void getPuzzleWords(char Puzzle[][], int row, int column)
	{
		VerticalLtoR(Puzzle, row, column);
		VerticalRtoL(Puzzle, row, column);
		HorizontalUtoD(Puzzle, row, column);
		HorizontalDtoU(Puzzle, row, column);
		DiagonalLR(Puzzle, row, column); //both, from up to down and reverse
		DiagonalRL(Puzzle, row, column); //both, from up to down and reverse
	}
	
	
	
	
	//code snippet for left to right vertical word search
	private static void VerticalLtoR(char Puzzle[][], int row, int column)
	{
		String [] PuzzleRows=new String[row];
		for(int i=0; i<row; i++)
		{
			char[] sub = new char[column];
			for (int j=0; j<column;j++)
			{
				sub[j]= Puzzle[i][j];
			}
			PuzzleRows[i]=new String(sub);
			//System.out.println(PuzzleRows[i]);
			sub=null;
		}
		
		for(int k=0;k<row;k++)
		{
			for(int l=0; l<column;l++) 
			{
				
				for (int m=l;m<column;m++)
				{
					if(isPrefix(PuzzleRows[k].substring(l,m+1)))
						{
							if (isWord(PuzzleRows[k].substring(l,m+1)))
							{
								
								if(!PuzzleWord.contains(PuzzleRows[k].substring(l,m+1)))
								{
									System.out.println("("+k+","+l+") to ("+k+","+m+"): "+PuzzleRows[k].substring(l,m+1));
									PuzzleWord.insert(PuzzleRows[k].substring(l,m+1));
									
								}
								else
								{
									if(!PuzzleWordList.contains(PuzzleRows[k].substring(l,m+1)))
									{
										PuzzleWordList.add(PuzzleRows[k].substring(l,m+1));
									}
								}
									
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
			}
			
		}
		
		PuzzleRows=null;
	}
	
	
	
	//code snippet for right to left vertical word search
	private static void VerticalRtoL(char[][]Puzzle,int row, int column)
	{
		String [] PuzzleRowsRev=new String[row];
		for(int i=0; i<row; i++)
		{
			char[] temp = new char[column];
			for (int j=column; j>0;j--)
			{
				temp[column-j]= Puzzle[i][j-1];
			}
			PuzzleRowsRev[i]=new String(temp);
			//System.out.println(PuzzleRowsRev[i]);
			temp=null;
		}
		
		
		for(int k1=0;k1<row;k1++)
		{
			for(int l1=0; l1<column;l1++) 
			{
				for (int m1=l1+1;m1<column;m1++)
				{
					if(isPrefix(PuzzleRowsRev[k1].substring(l1,m1+1)))
						{
							if (isWord(PuzzleRowsRev[k1].substring(l1,m1+1)))
							{
								if(!PuzzleWord.contains(PuzzleRowsRev[k1].substring(l1,m1+1)))
								{
									System.out.println("("+(k1)+","+(column-l1-1)+") to ("+(k1)+","+(column-m1-1)+"): "+PuzzleRowsRev[k1].substring(l1,m1+1));
									PuzzleWord.insert(PuzzleRowsRev[k1].substring(l1,m1+1));
									
									
								}
								else
								{
									if(!PuzzleWordList.contains(PuzzleRowsRev[k1].substring(l1,m1+1)))
									{
										PuzzleWordList.add(PuzzleRowsRev[k1].substring(l1,m1+1));
									}
								}
									
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
			}
			
		}
		PuzzleRowsRev=null;
	}
	
	
	
	
	//code snippet for top to bottom horizontal word search
	private static void HorizontalUtoD (char Puzzle[][], int row, int column)
	{
		String [] PuzzleCol=new String[column];
		for(int i=0; i<column; i++)
		{
			char[] temp = new char[row];
			for (int j=0; j<row;j++)
			{
				temp[j]= Puzzle[j][i];
			}
			PuzzleCol[i]=new String(temp);
			//System.out.println(PuzzleCol[i]);
			temp=null;
		}
		
		
		for(int k1=0;k1<column;k1++)
		{
			for(int l1=0; l1<row;l1++) 
			{
				for (int m1=l1+1;m1<row;m1++)
				{
					if(isPrefix(PuzzleCol[k1].substring(l1,m1+1)))
						{
							if (isWord(PuzzleCol[k1].substring(l1,m1+1)))
							{
								if(!PuzzleWord.contains(PuzzleCol[k1].substring(l1,m1+1)))
								{
									System.out.println("("+(l1)+","+(k1)+") to ("+(m1)+","+(k1)+"): "+PuzzleCol[k1].substring(l1,m1+1));
									PuzzleWord.insert(PuzzleCol[k1].substring(l1,m1+1));
									
									
								}
								else
								{
									if(!PuzzleWordList.contains(PuzzleCol[k1].substring(l1,m1+1)))
									{
										PuzzleWordList.add(PuzzleCol[k1].substring(l1,m1+1));
									}
								}
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
			}
			
		}
		PuzzleCol=null;
	}
	
	
	
	
	//code snippet for bottom to top vertical word search
	private static void HorizontalDtoU (char Puzzle[][], int row, int column)
	{
		String [] PuzzleColRev=new String[column];
		for(int i=0; i<column; i++)
		{
			char[] temp = new char[row];
			for (int j=row; j>0;j--)
			{
				temp[row-j]= Puzzle[j-1][i];
			}
			PuzzleColRev[i]=new String(temp);
			//System.out.println(PuzzleColRev[i]);
			temp=null;
		}
		
		
		for(int k1=0;k1<column;k1++)
		{
			for(int l1=0; l1<row;l1++) 
			{
				for (int m1=l1+1;m1<row;m1++)
				{
					if(isPrefix(PuzzleColRev[k1].substring(l1,m1+1)))
						{
							if (isWord(PuzzleColRev[k1].substring(l1,m1+1)))
							{
								if(!PuzzleWord.contains(PuzzleColRev[k1].substring(l1,m1+1)))
								{
									System.out.println("("+(row-l1-1)+","+(k1)+") to ("+(row-m1-1)+","+(k1)+"): "+PuzzleColRev[k1].substring(l1,m1+1));
									PuzzleWord.insert(PuzzleColRev[k1].substring(l1,m1+1));
									
									
								}
								else 
								{
									if(!PuzzleWordList.contains(PuzzleColRev[k1].substring(l1,m1+1)))
									{
										PuzzleWordList.add(PuzzleColRev[k1].substring(l1,m1+1));
									}
								}
									
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
			}
			
		}
		PuzzleColRev=null;
	}
	
	
	
	
	//code snippet for left to right top to bottom and vice-versa diagonal word search
	private static void DiagonalLR (char Puzzle[][], int row, int column)
	{
		String [] lrud=new String[row+column-3];
		String [] lrudRev = new String[lrud.length];
		for(int i=0; i<column-1; i++)
		{
			String temp = "";
			int x=0;
			int y=i;
			while ((x<row) && (y<column))
			{
				temp= temp.concat(String.valueOf(Puzzle[x][y]));
				x++;
				y++;
			}
			lrud[i]=temp;
			lrudRev[i]=new StringBuilder(lrud[i]).reverse().toString();
			//System.out.println(lrud[i]+"   "+lrudRev[i]);
			temp=null;
		}
		
		for(int i=1; i<row-1; i++)
		{
			int j=column-2+i;
			String temp = "";
			int x=i;
			int y=0;
			while ((x<row) && (y<column))
			{
				temp= temp.concat(String.valueOf(Puzzle[x][y]));
				x++;
				y++;
			}
			lrud[j]=temp;
			lrudRev[j]=new StringBuilder(lrud[j]).reverse().toString();
			//System.out.println(lrud[j]+"   "+lrudRev[j]);
			temp=null;
		}
		
		//System.out.println("LRUD size= "+lrud.length);
		for(int k1=0;k1<lrud.length;k1++)
		{
			for(int l1=0; l1<lrud[k1].length();l1++) 
			{
				//try 
				//{
					
				
				for (int m1=l1+1;m1<lrud[k1].length();m1++) 
				{
					
					//System.out.println("k1="+k1+" l1="+l1+" m1="+m1);
					if(isPrefix(lrud[k1].substring(l1,m1+1)))
						{
							if (isWord(lrud[k1].substring(l1,m1+1)))
							{
								if(!PuzzleWord.contains(lrud[k1].substring(l1,m1+1)))
								{									
									PuzzleWord.insert(lrud[k1].substring(l1,m1+1));
									
									if(k1<column-1)
									{
										System.out.println("("+(l1)+","+(k1+l1)+") to ("+(m1)+","+(k1+m1)+"): "+lrud[k1].substring(l1,m1+1));
									}
									else
									{
										System.out.println("("+(k1-column+2+l1)+","+(l1)+") to ("+(k1-column+2+m1)+","+(m1)+"): "+lrud[k1].substring(l1,m1+1));
									}
									
								}
								else
								{
									if(!PuzzleWordList.contains(lrud[k1].substring(l1,m1+1)))
									{
										PuzzleWordList.add(lrud[k1].substring(l1,m1+1));
									}
								}
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
				}
				//catch(NullPointerException e)
				//{
					//break;
				//}
			//}
			
		}
		for(int k1=0;k1<lrudRev.length;k1++)
		{
			for(int l1=0; l1<lrudRev[k1].length();l1++) 
			{
				//try 
				//{
					
				
				for (int m1=l1+1;m1<lrudRev[k1].length();m1++) 
				{
					
					//System.out.println("k1="+k1+" l1="+l1+" m1="+m1);
					if(isPrefix(lrudRev[k1].substring(l1,m1+1)))
						{
							if (isWord(lrudRev[k1].substring(l1,m1+1)))
							{
								if(!PuzzleWord.contains(lrudRev[k1].substring(l1,m1+1)))
								{
									
									PuzzleWord.insert(lrudRev[k1].substring(l1,m1+1));
									
									if(k1<column-1)
									{
										System.out.println("("+(lrudRev[k1].length()-1-l1)+","+(lrudRev[k1].length()-1-l1+k1)+") to ("+(lrudRev[k1].length()-1-m1)+","+(lrudRev[k1].length()-1+k1-m1)+"): "+lrudRev[k1].substring(l1,m1+1));
									}
									else
									{
										System.out.println("("+ (lrudRev[k1].length()-1-l1+(k1-column+2))+","+(lrudRev[k1].length()-1-l1)+") to ("+(lrudRev[k1].length()-1-m1+(k1-column+2))+","+(lrudRev[k1].length()-1-m1)+"): "+lrudRev[k1].substring(l1,m1+1));
									}
								}
								else
								{
									if(!PuzzleWordList.contains(lrudRev[k1].substring(l1,m1+1)))
									{
										PuzzleWordList.add(lrudRev[k1].substring(l1,m1+1));
									}
								}
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
				}
				//catch(NullPointerException e)
				//{
					//break;
				//}
			//}
			
		}
		
		lrud=null;
		lrudRev=null;
	}
	
	
	
	
	
	
	//code snippet for right to left top to bottom and vice-versa diagonal word search
	private static void DiagonalRL (char Puzzle[][], int row, int column)
	{
		String [] rlud=new String[row+column-3];
		String [] rludRev = new String[rlud.length];
		for(int i=1; i<column; i++)
		{
			String temp = "";
			int x=0;
			int y=i;
			while ((x<row) && (y>=0))
			{
				temp= temp.concat(String.valueOf(Puzzle[x][y]));
				x++;
				y--;
			}
			rlud[i-1]=temp;
			rludRev[i-1]=new StringBuilder(rlud[i-1]).reverse().toString();
			//System.out.println(rlud[i]+"   "+rludRev[i]);
			temp=null;
		}
		
		for(int i=1; i<row-1; i++)
		{
			int j=column-2+i;
			String temp = "";
			int x=i;
			int y=column-1;
			while ((x<row) && (y>=0))
			{
				temp= temp.concat(String.valueOf(Puzzle[x][y]));
				x++;
				y--;
			}
			rlud[j]=temp;
			rludRev[j]=new StringBuilder(rlud[j]).reverse().toString();
			//System.out.println(rlud[j]+"   "+rludRev[j]);
			temp=null;
		}
		
		for(int k1=0;k1<rlud.length;k1++)
		{
			for(int l1=0; l1<rlud[k1].length();l1++) 
			{
				//try 
				//{
					
				
				for (int m1=l1+1;m1<rlud[k1].length();m1++) 
				{
					
					//System.out.println("k1="+k1+" l1="+l1+" m1="+m1);
					if(isPrefix(rlud[k1].substring(l1,m1+1)))
						{
							if (isWord(rlud[k1].substring(l1,m1+1)))
							{
								if(!PuzzleWord.contains(rlud[k1].substring(l1,m1+1)))
								{									
									PuzzleWord.insert(rlud[k1].substring(l1,m1+1));
									
									if(k1<column-1)
									{
										System.out.println("("+(l1)+","+(k1-l1+1)+") to ("+(m1)+","+(k1-m1+1)+"): "+rlud[k1].substring(l1,m1+1));
									}
									else
									{
										System.out.println("("+(k1-column+2+l1)+","+(column-1-l1)+") to ("+(k1-column+2+m1)+","+(column-1-m1)+"): "+rlud[k1].substring(l1,m1+1));
									}
									
								}
								else
								{
									if(!PuzzleWordList.contains(rlud[k1].substring(l1,m1+1)))
									{
										PuzzleWordList.add(rlud[k1].substring(l1,m1+1));
									}
								}
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
				}
				//catch(NullPointerException e)
				//{
					//break;
				//}
			//}
			
		}
		for(int k1=0;k1<rludRev.length;k1++)
		{
			for(int l1=0; l1<rludRev[k1].length();l1++) 
			{
				//try 
				//{
					
				
				for (int m1=l1+1;m1<rludRev[k1].length();m1++) 
				{
					
					//System.out.println("k1="+k1+" l1="+l1+" m1="+m1);
					if(isPrefix(rludRev[k1].substring(l1,m1+1)))
						{
							if (isWord(rludRev[k1].substring(l1,m1+1)))
							{
								if(!PuzzleWord.contains(rludRev[k1].substring(l1,m1+1)))
								{
									
									PuzzleWord.insert(rludRev[k1].substring(l1,m1+1));
									
									if(k1<column-1)
									{
										System.out.println("("+(rludRev[k1].length()-1-l1)+","+(k1+2-rludRev[k1].length()+l1)+") to ("+(rludRev[k1].length()-1-m1)+","+(k1+2-rludRev[k1].length()+m1)+"): "+rludRev[k1].substring(l1,m1+1));
									}
									else
									{
										System.out.println("("+ (rludRev[k1].length()-1-l1+(k1-column+2))+","+(column-rludRev[k1].length()+l1)+") to ("+(rludRev[k1].length()-1-m1+(k1-column+2))+","+(column-rludRev[k1].length()+m1)+"): "+rludRev[k1].substring(l1,m1+1));
									}
								}
								else
								{
									if(!PuzzleWordList.contains(rludRev[k1].substring(l1,m1+1)))
									{
										PuzzleWordList.add(rludRev[k1].substring(l1,m1+1));
									}
								}
							}
							else
								;//do nothing
						}
					else
					{
						break;
					}
					
				}
				}
				//catch(NullPointerException e)
				//{
					//break;
				//}
			//}
			
		}
		
		rlud=null;
		rludRev=null;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception
	{
		
		double StartMain=System.currentTimeMillis();
		int row = 0,column = 0;
		boolean exception=false;
		
		Scanner type = new Scanner(System.in);
		
		
		do
			{
			try 
			
			{
				System.out.print("Please enter row length between 1 and 20: ");
				row = Integer.valueOf(type.next());
			}
			catch (NumberFormatException e)
			{
				//System.out.println("Input didn't match with the requirement: Try Again");
				row=21;
				
			}
			}
		while(row>20);
		do
		{
		try 
		
		{
			System.out.print("Please enter column height between 1 and 20: ");
			column = Integer.valueOf(type.next());
		}
		catch (NumberFormatException e)
		{
			//System.out.println("Input didn't match with the requirement: Try Again");
			column=21;
				
		}
		}
	while(column>20);
		
		type.close();
		System.out.println();
		char Puzzle[][]= GeneratePuzzle(row,column);
		
		double startTime = System.currentTimeMillis( );
					
		
		loadTable(dictWord, "dictionary.txt");
		makePrefix(dictPref,"dictionary.txt");
		
		System.out.println("\nThe words are: \n");
		getPuzzleWords(Puzzle,row,column);
		System.out.println("\nTotal word count: "+PuzzleWord.theSize+"\n");
		System.out.println("Following is the list of word(s) that have duplicates:");
		for(int loop=0;loop<PuzzleWordList.size();loop++)
		{
			System.out.println(PuzzleWordList.get(loop));
		}
        double endTime = System.currentTimeMillis( );
        
        
        System.out.println("\n\nTime Analysis:");
        System.out.println( "Time taken by the user to generate puzzle: " + ((startTime - StartMain)/1000) + " second" );
        System.out.println( "Time taken by the program to find the words: " + ((endTime - startTime)/1000) + " second" );
        System.out.println( "Total time taken: " + ((endTime - StartMain)/1000) + " second" );
        
        
        dictWord.makeEmpty();
		dictPref.makeEmpty();
		PuzzleWord.makeEmpty();
		PuzzleWordList.clear();
        
      		
		/*Ignore
  		 * Just Junk
  		 * 
  		 * try
  	       {
  	            FileReader fr2 = new FileReader("prefix.txt");
  	            BufferedReader br2 = new BufferedReader(fr2);

  	            String prefix;
  	            
  	            while((prefix = br2.readLine()) != null)
  	            {           	
  	            	
  					dictPref.insert(prefix);	
  	            	
  	            }
  	            br2.close();
  	           
  	        }
  	        catch(IOException e)
  	        {
  	            System.out.println("File not found");
  	        }
  	        try
       {
            FileReader fr = new FileReader("dictionary.txt");
            //File file = new File("prefix.txt");
            BufferedReader br = new BufferedReader(fr);
            //BufferedWriter wr = new BufferedWriter(new FileWriter(file));

            String dictionary;
            
            while((dictionary = br.readLine()) != null)
            {
            	
            	for(int i=0;i<dictionary.length();i++) 
            	{
            		char[] prefix = new char[i+1];	
            		for (int j=0;j<i+1;j++) 
            		{
            			prefix [j] = dictionary.charAt(j);		            			            			
            		}
            		String pref=new String(prefix);
            		dictPref.insert(pref);
            		//wr.write(pref);
            		//wr.newLine();
            		
            	
            	}
            	
				//dictWord.insert(dictionary);
            	
            	
            	
            }
            br.close();
            //wr.close();
        }
        catch(IOException e)
        {
            System.out.println("File not found");
        }
  	        *
  	        *
  	        *
  	        */
  		
  //loadTable(dictPref, "prefix.txt");

	}

}
