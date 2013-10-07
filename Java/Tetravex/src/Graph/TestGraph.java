package Graph;
public class TestGraph
{//try counts one by one
	static long count,enter,Count,error=0;
	int size;
	int maxNum;
	square tiles[];
	lists2 adjLists[];
	int TilesPos[];
	boolean TilesVisited[];
	Board stack;
	public int fact(int n)
	{
		if(n==0)
			return 1;
		return n*fact(n-1);
	}
	public TestGraph(Board c)
	{
		size=c.getsize();
		maxNum=size*size;
		tiles=new square[maxNum];//square list
		adjLists=new lists2[maxNum];
		TilesPos=new int[maxNum];
		TilesVisited=new boolean[maxNum];
		for(int i=0;i<maxNum;i++)
			TilesVisited[i]=false;
		stack=new Board(size);//New empty Board
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
				enter++;
			if(adjLists[i].down.length==0)
				enter++;
			if(adjLists[i].left.length==0)
				enter++;
			if(adjLists[i].right.length==0)
				enter++;
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
			System.out.println(upCount);
			result=search(TilesVisited,start,TilesPos,upCount,rightCount,leftCount,downCount);
			
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
				stack.getMixedBoard()[i][j]=tiles[result[Count]];
				Count++;
			}
	}
	public int[] search(boolean TilesVisited[],int currentSquare,int TilesPos[],int upCount,int rightCount,int leftCount,int downCount)
	{
		   int checkedTile=-1,aboveTile=-1,leftTile=-1;
    	   int upTemp=upCount,leftTemp=leftCount,rightTemp=rightCount,downTemp=downCount;
			//TilesPos[i]=j means tile j is in position i
	       if(currentSquare == maxNum) //Done
	           return TilesPos;
	       if(currentSquare>=size)//We are not in the first row
	       {
			   if(upCount>0)//If some up squares were not put in upper row
			   {
				   error++;
		    	   return null;				   
			   }
			   int emptyRightSquares=size-(currentSquare/size)-1;//Count empty left squares
			   if(emptyRightSquares<rightCount)//Number is not enough
				   return null;
			   int emptyLeftSquares=currentSquare%size==0?emptyRightSquares-1:emptyRightSquares;
			   if(emptyLeftSquares<leftCount)
				   return null;
			   if(currentSquare/size==size-1)//We are in the last row
			   {
				   int emptyDownSquares=size-(currentSquare%size);
				   if(emptyDownSquares<emptyDownSquares)
					   return null;
			   }
			   aboveTile=TilesPos[currentSquare-size];//get above tile
	    	   int i=0;
		       for(i=0;i<adjLists[aboveTile].down.length;i++)//scan all tiles that belongs to the down list of the upper tile
		   	   {
		    	   count++;
		    	   checkedTile=adjLists[aboveTile].down[i];//index of checked tile
		    	   if(TilesVisited[checkedTile]==true)//tile was used
		    		   continue;
		    	   if(currentSquare%size!=0&&tiles[checkedTile].isLeft())//A left tile not in left
		    		   continue;
		    	   if((currentSquare+1)%size!=0&&tiles[checkedTile].isRight())//A right tile not in right
		    		   continue;
		    	   if(currentSquare/size!=0&&tiles[checkedTile].isUp())//An up tile not in up
		    		   continue;
		    	   if(currentSquare/size!=size-1&&tiles[checkedTile].isDown())//A down tile not in down
		    		   continue;
		    	   if(currentSquare%size!=0)//We are not in the first column
			    	   leftTile=TilesPos[currentSquare-1];//get left tile
		    	   if(currentSquare%size==0||tiles[leftTile].getRight()==tiles[checkedTile].getLeft())
		    	   {
			    	   if(tiles[checkedTile].isUp())
			    		   upTemp--;
			    	   if(tiles[checkedTile].isRight())
			    		   rightTemp--;
			    	   if(tiles[checkedTile].isLeft())
			    		   leftTemp--;
			    	   if(tiles[checkedTile].isDown())
			    		   downTemp--;
		    		   count++;
		        	   TilesPos[currentSquare]=checkedTile;//push tile
		        	   TilesVisited[checkedTile]=true;
		        	   int result[]=search(TilesVisited,currentSquare+1,TilesPos,upCount,rightCount,leftCount,downCount);
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
		       for(i=0;i<adjLists[leftTile].right.length;i++)//scan all tiles that belongs to the down list of the upper tile
		   	   {
		    	   if(size-currentSquare+1<upCount)//The number of remaining squares must be >= number of up count
		    		   return null;
		    	   checkedTile=adjLists[leftTile].right[i];//index of checked tile
		    	   if(TilesVisited[checkedTile]==true)//tile was used
		    		   continue;
		    	   if(currentSquare%size==0&&tiles[checkedTile].isRight())
		    	   {
		    		   Count++;
		    		   continue;
		    	   }
		    	   if((currentSquare+1)%size==0&&tiles[checkedTile].isLeft())
		    	   {
		    		   Count++;
		    		   continue;
		    	   }
		    	   if(currentSquare/size==0&&tiles[checkedTile].isDown())
		    	   {
		    		   Count++;
		    		   continue;
		    	   }
		    	   if(tiles[checkedTile].isUp())
		    		   upTemp--;
		    	   if(tiles[checkedTile].isRight())
		    		   rightTemp--;
		    	   if(tiles[checkedTile].isLeft())
		    		   leftTemp--;
		    	   if(tiles[checkedTile].isDown())
		    		   downTemp--;
		    	   count++;
	        	   TilesPos[currentSquare]=checkedTile;//push tile
	        	   TilesVisited[checkedTile]=true;
	        	   int result[]=search(TilesVisited,currentSquare+1,TilesPos,upTemp,rightTemp,leftTemp,downTemp);
	        	   if(result!=null)
	        		   return result;
	        	   TilesVisited[checkedTile]=false; 
		   	   }
		       return null;//No match
	       }
	}
	public static void main(String[]args)
	{
		//Why does he take time when the number on the square is<999999
		Board c=new Board(35);
		long start=System.currentTimeMillis();
		TestGraph g=new TestGraph(c);
		g.stack.display();
		System.out.println("Number of trials ="+count);
		System.out.println("Number of destinied squares ="+enter);
		System.out.println("Number of rejectec up states "+error);
		long end=System.currentTimeMillis();
		System.out.println("Elpased time ="+(end-start)/1000+" second");
	/*	JFrame f=new JFrame();
		f.setSize(1000,1000);
		f.setLayout(null);
		f.getContentPane().add(g.stack);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);*/
	}
}