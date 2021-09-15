package maze_package;
import java.util.*;


public class GenerateMaze extends GUI {
	private static int row;
	private static int column;
	private static int maze_size;
	private static char [] maze;
	
	
	private static void InitializeMaze (int row1,int column1)
	{
		row=row1;
		column=2*column1;
		maze_size=row*column;
		maze=new char[maze_size];
		for(int i=0;i<maze_size;i++)
		{
			if(i%2==0)
			{
				maze[i]='_';
			}
			else
			{
				maze[i]='|';
			}
		}
	}
	
	public static char getMazeElement(int position)
	{
		return maze[position];
	}
	
	public static void createMaze(int MazeRow, int MazeColumn)
	{
		InitializeMaze (MazeRow,MazeColumn);
		Stack<Integer> stack=new Stack<>();
		stack.removeAllElements();
		int k=0;
		int current=0;
		boolean flag=true;
		DisjSets room=new DisjSets(MazeRow*MazeColumn);
		Random random=new Random();
		while(flag)
		{
			if(k==0)
			{
				stack.removeAllElements();
				stack.push(k);
			}
		k++;	
			if(!stack.empty())
			{
				current=stack.lastElement();
				ArrayList<Integer> arr=new ArrayList<>();
				if((current-MazeColumn)>0 && room.find(current)!=room.find(current-MazeColumn))
				{
					arr.add(current-MazeColumn);
					//System.out.println(" root of current "+room.find(current)+"["+room.s[room.find(current)]+"] "+" root of next "+room.find(current-MazeColumn)+"["+room.s[room.find(current-MazeColumn)]+"] ");
				}
				if(current%MazeColumn!=0 && room.find(current)!=room.find(current-1))
				{
					arr.add(current-1);
					//System.out.println(" root of current "+room.find(current)+"["+room.s[room.find(current)]+"] "+" root of next "+room.find(current-1)+"["+room.s[room.find(current-1)]+"] ");
				}
				if(((current+1)%MazeColumn)!=0 && room.find(current)!=room.find(current+1))
				{
					arr.add(current+1);
					//System.out.println(" root of current "+room.find(current)+"["+room.s[room.find(current)]+"] "+" root of next "+room.find(current+1)+"["+room.s[room.find(current+1)]+"] ");
				}
				if((current+MazeColumn)<(MazeRow*MazeColumn) && room.find(current)!=room.find(current+MazeColumn))
				{
					arr.add(current+MazeColumn);
					//System.out.println(" root of current "+room.find(current)+"["+room.s[room.find(current)]+"] "+" root of next "+room.find(current+MazeColumn)+"["+room.s[room.find(current+MazeColumn)]+"] ");
				}
				if(arr.size()>0)
				{
					int i=arr.get(random.nextInt(arr.size()));
					stack.push(i);
					if (i==current-MazeColumn)
					 {
						maze[i*2]=' ';
						room.union(room.find(current),room.find(i));
						//stack.push(i);
						arr.clear();
						//System.out.println("up "+i+" current "+current+" stacked "+stack.peek()+ " root of current "+room.find(current)+" root of next "+room.find(i));
						
					 }
					if (i==current-1)
					{
						maze[(current*2)-1]=' ';
						room.union(room.find(current),room.find(i));;
						//stack.push(i);
						arr.clear();
						//System.out.println("left "+i+" current "+current+" stacked "+stack.peek() + " root of current "+room.find(current)+" root of next "+room.find(i));
						
					}
					if (i==current+1)
					{
						maze[(current*2)+1]=' ';
						room.union(room.find(current),room.find(i));;
						//stack.push(i);
						arr.clear();
						//System.out.println("right "+i+" current "+current+" stacked "+stack.peek()+ " root of current "+room.find(current)+" root of next "+room.find(i));
						
					}
					if (i==current+MazeColumn)
					{
						maze[(current*2)]=' ';
						room.union(room.find(current),room.find(i));;
						//stack.push(i);
						arr.clear();
						//System.out.println("down "+i+" current "+current+" stacked "+stack.peek()+ " root of current "+room.find(current)+" root of next "+room.find(i));
						
					}				
					
				}
				else
				{
					int p=stack.pop();
					
				}
				
				
			}
			else
			{
				flag=false;
			}
		}
		
		
	}
	
	public static void showMaze()
	{
		for(int k=0;k<column/2;k++)
			System.out.print(" _");
		System.out.println();
		for(int i=0;i<row;i++)
		{
			for(int j=-1;j<column;j++)
			{
				if(j<0)
				{
					if(i==0)
					{
						System.out.print(' ');
					}
					else
						System.out.print('|');
				}
				else if(i==row-1&&j==column-1)
				{
					;
				}
				else
				{
					System.out.print(getMazeElement((i*column)+j));
				}
				
			}
			System.out.println();
		}
	}
	
	
	

	// Test main; all finds on same output line should be identical
    public static void main( String [ ] args )
    {
       
    	
    	
    	int maze_row=0;
    	int maze_column=0;
    	
		
		Scanner type = new Scanner(System.in);
		
		
		do
			{
			try 
			
			{
				System.out.print("Please enter number of row between 1 and 20: ");
				maze_row = Integer.valueOf(type.next());
			}
			catch (NumberFormatException e)
			{
				//System.out.println("Input didn't match with the requirement: Try Again");
				maze_row=21;
				
			}
			}
		while(maze_row>20 || maze_row<1);
		do
		{
		try 
		
		{
			System.out.print("Please enter number of column between 1 and 20: ");
			maze_column = Integer.valueOf(type.next());
		}
		catch (NumberFormatException e)
		{
			//System.out.println("Input didn't match with the requirement: Try Again");
			maze_column=21;
				
		}
		}
	while(maze_column>20 ||maze_column<1);
		
		type.close();
		System.out.println();
    	createMaze(maze_row,maze_column);
    	showMaze();
    	
    	
    	
    	
    	
    	
    	
    	
    	/* int NumElements = 128;
        int NumInSameSet = 16;

        DisjSets ds = new DisjSets( NumElements );
        int set1, set2;

        for( int k = 1; k < NumInSameSet; k *= 2 )
        {
            for( int j = 0; j + k < NumElements; j += 2 * k )
            {
                set1 = ds.find( j );
                set2 = ds.find( j + k );
                ds.union( set1, set2 );
            }
        }

        for( int i = 0; i < NumElements; i++ )
        {
            System.out.print( ds.find( i )+ "*" );
            if( i % NumInSameSet == NumInSameSet - 1 )
                System.out.println( );
        }
        System.out.println( );*/
   }

    

}
