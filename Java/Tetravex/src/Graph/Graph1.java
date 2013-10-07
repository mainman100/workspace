package Graph;
import java.util.Scanner;
public class Graph1
{
	private long trials,error=0;
	private int size,maxNum,desteniated;
	private square tiles[];
	private lists2 adjLists[];
	private int TilesPos[];
	private boolean TilesVisited[];
	private Board solution;
	public Graph1(Board c)
	{
		size=c.getsize();
		maxNum=size*size;
		tiles=new square[maxNum];//square list
		adjLists=new lists2[maxNum];
		TilesPos=new int[maxNum];
		TilesVisited=new boolean[maxNum];
		for(int i=0;i<maxNum;i++)
			TilesVisited[i]=false;
		solution=new Board(size);//New empty Board
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)//Initialize square list
				tiles[i*size+j]=(square)c.getMixedBoard()[i][j];
		for(int i=0;i<adjLists.length;i++)
		{
			adjLists[i]=new lists2();//make new adjacent list of 4 lists
			for(int i1=0;i1<tiles.length;i1++)//Calculate the size of the lists
				if(i1!=i)
				{
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
			adjLists[i].up=new int[adjLists[i].upCount];
			adjLists[i].upCount=0;
			adjLists[i].down=new int[adjLists[i].downCount];
			adjLists[i].downCount=0;
			adjLists[i].right=new int[adjLists[i].rightCount];
			adjLists[i].rightCount=0;
			adjLists[i].left=new int[adjLists[i].leftCount];
			adjLists[i].leftCount=0;//make new adjacent list of 4 lists
			for(int i1=0;i1<tiles.length;i1++)//initialize the four lists
				if(i1!=i)
				{
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
		int upCount=0;
		int rightCount=0;
		int leftCount=0;
		int downCount=0;
		for(int i=0;i<maxNum;i++)
		{
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
		for(int i=0;i<maxNum;i++)
		{
			if(adjLists[i].up.length==0)
				desteniated++;
			if(adjLists[i].down.length==0)
				desteniated++;
			if(adjLists[i].left.length==0)
				desteniated++;
			if(adjLists[i].right.length==0)
				desteniated++;
		}
		int start=0;
		int[]result=null;
		square q1=c.getSolvedBoard()[0][0];//if you have the first tile
		for(int t=0;t<maxNum;t++)
			if(tiles[t]==q1)
			{
				if(tiles[t].isUp())
					upCount--;
				if(tiles[t].isLeft())
					leftCount--;
				TilesVisited[t]=true;
				TilesPos[0]=t;
				start=1;
				break;
			}
			System.out.println("Started ");
			result=search(TilesVisited,start,TilesPos,upCount);
			
	/*	for(int i=0;i<tiles.length;i++)//remember to change directions counts
		{
			TilesPos[0]=i;
			TilesVisited[i]=true;
			start=1;
			result=search(TilesVisited,1,TilesPos);
			if(result!=null)
				break;
			TilesVisited[i]=false;
		}*/
		int Count=0;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				solution.getMixedBoard()[i][j]=tiles[result[Count]];
				Count++;
			}
	}
	private int[] search(boolean TilesVisited[],int currentSquare,int TilesPos[],long upCount)
	{
		   int checkedTile=-1,aboveTile=-1,leftTile=-1;
			//TilesPos[i]=j means tile j is in position i
	       if(currentSquare == maxNum) //Done
	           return TilesPos;
	       if(currentSquare>=size)//We are not in the first row
	       {
			   if(upCount>0)
			   {
				   error++;
		    	   return null;				   
			   }
			   aboveTile=TilesPos[currentSquare-size];//get above tile
	    	   int i=0;
		       for(i=0;i<adjLists[aboveTile].down.length;i++)//scan all tiles that belongs to the down list of the upper tile
		   	   {
		    	   trials++;
		    	   checkedTile=adjLists[aboveTile].down[i];//index of checked tile
		    	   if(TilesVisited[checkedTile]==true)//tile was used
		    	   {
		    		   error++;
		    		   continue;		    		   
		    	   }
		    	   if(currentSquare%size!=0&&tiles[checkedTile].isLeft())//A left tile not in left
		    	   {
		    		   error++;
		    		   continue;
		    	   }
		    	   if((currentSquare+1)%size!=0&&tiles[checkedTile].isRight())//A right tile not in right
		    	   {
		    		   error++;
		    		   continue;
		    	   }
		    	   if(currentSquare/size!=0&&tiles[checkedTile].isUp())//An up tile not in up
		    	   {
		    		   error++;
		    		   continue;
		    	   }
		    	   if(currentSquare/size!=size-1&&tiles[checkedTile].isDown())//A down tile not in down
		    	   {
		    		   error++;
		    		   continue;
		    	   }
		    	   if(currentSquare%size!=0)//We are not in the first column
			    	   leftTile=TilesPos[currentSquare-1];//get left tile
		    	   if(currentSquare%size==0||tiles[leftTile].getRight()==tiles[checkedTile].getLeft())
		    	   {
		        	   TilesPos[currentSquare]=checkedTile;//push tile
		        	   TilesVisited[checkedTile]=true;
		        	   int result[]=search(TilesVisited,currentSquare+1,TilesPos,upCount);
		        	   if(result!=null)
		        		   return result;
		        	   TilesVisited[checkedTile]=false; 
		    	   }
		   	   }
		       return null;//There is now down tiles
	       }
	       else//We are in the first row
	       {
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
		    	   {
		    		   error++;
		    		   continue;
		    	   }
		    	   if((currentSquare+1)%size!=0&&tiles[checkedTile].isRight())//A right tile not in right
		    	   {
		    		   error++;
		    		   continue;
		    	   }
		    	   if(currentSquare/size!=size-1&&tiles[checkedTile].isDown())//A down tile not in down
		    	   {
		    		   error++;
		    		   continue;
		    	   }
		    	   if(tiles[checkedTile].isUp())
		    		   temp=upCount-1;
		    	   trials++;
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
	public long getTrials() 
	{
		return trials;
	}
	public long getError() 
	{
		return error;
	}
	public int getDesteniated() 
	{
		return desteniated;
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
		Graph1 g=new Graph1(c);
		g.getSolution().display();
		long end=System.currentTimeMillis();
		System.out.println("Elapsed time ="+(end-start)/1000+" second");
		System.out.println("Number of trials ="+g.getTrials());
		System.out.println("Number of destinied squares positions ="+g.getDesteniated());
		System.out.println("Number of rejected states "+g.getError());
	}
}