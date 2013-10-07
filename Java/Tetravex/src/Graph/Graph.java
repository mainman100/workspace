package Graph;
import java.util.Scanner;
public class Graph
{
	private int size,maxNum;//Counters
	private square tiles[];//Array of tiles objects
	private lists adjLists[];//Array of adjacent lists of each tile
	private int TilesPos[];//TilesPos[i]=j means that tile j lies in square i
	private boolean TilesVisited[];//TilesVisited[i]=true means tile number i was visited
	private Board solution;
	private static int entered;
	public Graph(Board c)
	{
		size=c.getsize();
		maxNum=size*size;//maxNum is the number of tiles
		tiles=new square[maxNum];//square list
		adjLists=new lists[maxNum];
		TilesPos=new int[maxNum];
		TilesVisited=new boolean[maxNum];
		for(int i=0;i<maxNum;i++)
			TilesVisited[i]=false;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)//Initialize square list
				tiles[i*size+j]=(square)c.getMixedBoard()[i][j];
		for(int i=0;i<adjLists.length;i++)
		{
			adjLists[i]=new lists();//make new adjacent list of 4 lists
			for(int i1=0;i1<tiles.length;i1++)
				if(i1!=i)
				{	//Calculate the length of each adjacent list
					if(tiles[i].getUp()==tiles[i1].getDown())
						adjLists[i].upCount++;
					if(tiles[i].getDown()==tiles[i1].getUp())
						adjLists[i].downCount++;
					if(tiles[i].getRight()==tiles[i1].getLeft())
						adjLists[i].rightCount++;
					if(tiles[i].getLeft()==tiles[i1].getRight())
						adjLists[i].leftCount++;
				}
		}
		for(int i=0;i<adjLists.length;i++)
		{
			//Initialize each adjacent list
			adjLists[i].up=new int[adjLists[i].upCount];
			adjLists[i].upCount=0;//Re initialize the counter 
			adjLists[i].down=new int[adjLists[i].downCount];
			adjLists[i].downCount=0;
			adjLists[i].right=new int[adjLists[i].rightCount];
			adjLists[i].rightCount=0;
			adjLists[i].left=new int[adjLists[i].leftCount];
			adjLists[i].leftCount=0;//make new adjacent list of 4 lists
			for(int i1=0;i1<tiles.length;i1++)//initialize the four lists
				if(i1!=i)
				{	//Add tiles to adjacent lists
					if(tiles[i].getUp()==tiles[i1].getDown())
						adjLists[i].up[adjLists[i].upCount++]=i1;
					if(tiles[i].getDown()==tiles[i1].getUp())
						adjLists[i].down[adjLists[i].downCount++]=i1;
					if(tiles[i].getRight()==tiles[i1].getLeft())
						adjLists[i].right[adjLists[i].rightCount++]=i1;
					if(tiles[i].getLeft()==tiles[i1].getRight())
						adjLists[i].left[adjLists[i].leftCount++]=i1;
				}
		}
		int upCount=0;//Re initialize counters
		int rightCount=0;
		int leftCount=0;
		int downCount=0;
		for(int i=0;i<maxNum;i++)
		{	//Determine which tiles are uniquely up,down,left,right
			if(adjLists[i].up.length==0)
			{
				tiles[i].mustBeUp(true);
				upCount++;
			}
			if(adjLists[i].down.length==0)
			{
				tiles[i].mustBeDown(true);
				downCount++;
			}
			if(adjLists[i].right.length==0)
			{
				tiles[i].mustBeRight(true);
				rightCount++;
			}
			if(adjLists[i].left.length==0)
			{
				tiles[i].mustBeLeft(true);
				leftCount++;
			}
		}
		int start=0;//Current start position
		int[]result=null;//Initialize the result array
		square q1=c.getSolvedBoard()[0][0];//Get the correct corner(solvedBoard is an instance variable that holds an image of the solution after generating the board)
		for(int t=0;t<maxNum;t++)
			if(tiles[t]==q1)//If it is the corner
			{
				if(tiles[t].isUp())
					upCount--;//If it is unique up,decrement the number of uniquely up squares 
				if(tiles[t].isLeft())
					leftCount--;
				TilesVisited[t]=true;//Mark it as visited
				TilesPos[0]=t;
				start=1;//Start the search from square 1
				break;
			}
			System.out.println("Started ");
			result=search(TilesVisited,start,TilesPos,upCount);
		int Count=0;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				c.getMixedBoard()[i][j]=tiles[result[Count]];
				Count++;
			}
	}
	private int[] search(boolean TilesVisited[],int currentSquare,int TilesPos[],long upCount)
	{
			entered++;
		   int checkedTile=-1,aboveTile=-1,leftTile=-1;
	       if(currentSquare == maxNum) //We are done
	           return TilesPos;
	       if(currentSquare>=size)//We are not in the first row
	       {
			   if(upCount>0)//If there are still some uniquely up squares,this can not happen so it is a dead end
		    	   return null;				   
			   aboveTile=TilesPos[currentSquare-size];//Get the above tile
	    	   int i=0;
		       for(i=0;i<adjLists[aboveTile].down.length;i++)//scan all tiles that belongs to the down list of the upper tile
		   	   {
		    	   checkedTile=adjLists[aboveTile].down[i];//Index of checked tile
		    /*	   if(TilesVisited[checkedTile]==true)//Tile was used
		    		   continue;		    		   
		    	   if(currentSquare%size!=0&&tiles[checkedTile].isLeft())//A left tile not in the left most column
		    		   continue;
		    	   if((currentSquare+1)%size!=0&&tiles[checkedTile].isRight())//A right tile not in right most column
		    		   continue;
		    	   if(currentSquare/size!=0&&tiles[checkedTile].isUp())//An up tile not in up most column
		    		   continue;
		    	   if(currentSquare/size!=size-1&&tiles[checkedTile].isDown())//A down tile not in down most column
		    		   continue;
		    	   if(currentSquare%size!=0)//We are not in the first column*/
			    	   leftTile=TilesPos[currentSquare-1];//Get the index of the left tile
		    	   if(currentSquare%size==0||tiles[leftTile].getRight()==tiles[checkedTile].getLeft())//If we are in the first column or ...
		    	   {
		        	   TilesPos[currentSquare]=checkedTile;//Set the current square to hold the current tile
		        	   TilesVisited[checkedTile]=true;//Mark it as visited
		        	   int result[]=search(TilesVisited,currentSquare+1,TilesPos,upCount);//Do the recursive call
		        	   if(result!=null)//If the result is not a dead end
		        		   return result;
		        	   TilesVisited[checkedTile]=false; //Re set the flags 
		    	   }
		   	   }
		       return null;//There is no unvisited tiles in the adjacent List
	       }
	       else//We are in the first row
	       {
	    	   //Almost the same like the previous
	 	   	   leftTile=TilesPos[currentSquare-1];
	    	   int i=0;
	    	   long temp=upCount;
		       for(i=0;i<adjLists[leftTile].right.length;i++)//scan all tiles that belongs to the down list of the upper tile
		   	   {
		    	   if(!(size-currentSquare+1>=upCount))//The number of remaining squares must be >= number of up count
		    		   return null;
		    	   checkedTile=adjLists[leftTile].right[i];//index of checked tile
		    	   if(TilesVisited[checkedTile]==true)//tile was used
		    		   continue;
		    	   if(currentSquare%size!=0&&tiles[checkedTile].isLeft())//A left tile not in left
		    		   continue;
		    	   if((currentSquare+1)%size!=0&&tiles[checkedTile].isRight())//A right tile not in right
		    		   continue;
		    	   if(currentSquare/size!=size-1&&tiles[checkedTile].isDown())//A down tile not in down
		    		   continue;
		    	   if(tiles[checkedTile].isUp())
		    		   temp=upCount-1;
	        	   TilesPos[currentSquare]=checkedTile;//push tile
	        	   TilesVisited[checkedTile]=true;
	        	   int result[]=search(TilesVisited,currentSquare+1,TilesPos,temp);
	        	   if(result!=null)
	        		   return result;
	        	   TilesVisited[checkedTile]=false; 
		   	   }
		       return null;//No match
	       }
	}
	public Board getSolution() 
	{
		return solution;
	}
	public static void main(String[]args)
	{
		//Why does he take time when the number on the square is<999999
		System.out.println("Enter the size of the board");
		Scanner scan=new Scanner(System.in);
		Board c=new Board(scan.nextInt());
		long start=System.currentTimeMillis();
		Graph g=new Graph(c);
		System.out.println("*************");
		c.display();
		System.out.println("Entered "+entered+" times");
		long end=System.currentTimeMillis();
		System.out.println("Elapsed time ="+(end-start)/1000+" second");
	}
}