
import java.util.ArrayList;
import java.util.Collections;


public class Huffman 
{
	private static ArrayList<Node> huffProcess(String text)
	{
		ArrayList<Node> list=new ArrayList<Node>();
		boolean found=false;
		for(int i=0;i<text.length();i++)//initialize each character
		{
			char c=text.charAt(i);
			found=false;
			for(int j=0;j<list.size();j++)//did we process the character before?
			{
				if(list.get(j).getChar()==c)
				{
					found=true;
					break;
				}
			}
			if(found==false)
			{
				Node cc=new Node(c);
				list.add(cc);
				for(int k=0;k<text.length();k++)
					if(text.charAt(k)==c)
						cc.increaseCount();
			}
		}
		Collections.sort(list);
		return list;
	}
	public static Comp compress(String text)
	{
		ArrayList<Node> list=huffProcess(text);
		ArrayList<Node> temp=new ArrayList<Node>();
		while(!list.isEmpty())
		{
			Node parent=new Node((char)1);
			Node right=list.get(list.size()-1);
			parent.addRight(right);
			right.updateChildern('1');
			list.remove(list.size()-1);
			temp.add(right);
			if(!list.isEmpty())
			{
				Node left=list.get(list.size()-1);
				parent.addLeft(left);
				left.updateChildern('0');	
				list.remove(list.size()-1);	
				temp.add(left);
				if(list.isEmpty())
					break;
				list.add(parent);
			}
			Collections.sort(list);
		}
		String result="";
		list=temp;
		for(int i=0;i<text.length();i++)
		{
			char c=text.charAt(i);
			for(int j=0;j<list.size();j++)
				if(c==list.get(j).getChar())
				{
					result+=list.get(j).getCode();
					break;
				}
		}
		double factor=text.length()*8.0/result.length();
		return new Comp(result,factor);
	}
}

class Node implements Comparable<Node> 
{
	private char c;
	private int count=0;
	private char parentEdgeWeight;
	private Node parent,right,left;
	private String code="";
	public Node(char c, int count) 
	{
		this.c = c;
		this.count = count;
	}
	public Node(char c)
	{
		this.c=c;
	}
	public void increaseCount()
	{
		count++;
	}
	/**
	 * @return the c
	 */
	public char getChar() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setChar(char c) {
		this.c = c;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	public void addLeft(Node e)
	{
		left=e;
		left.updateCode('0');
	}
	public void addRight(Node e)
	{
		right=e;
		right.updateCode('1');
	}
	public void setParent(Node e)
	{
		parent=e;
	}
	public void setParentEdgeWeight(char weight)
	{
		parentEdgeWeight=weight;
	}
	public void updateCode(char c)
	{
		code=c+code;
	}
	public void updateChildern(char c)
	{
		if(left!=null)
		{
			left.updateCode(c);
			left.updateChildern(c);
		}
		if(right!=null)
		{
			right.updateCode(c);
			right.updateChildern(c);
		}
	}
	public String getCode()
	{
		return code;
	}
	public String toString()
	{
		return "("+c+","+count+","+code+")";
	}
	public int compareTo(Node c)
	{
		return c.getCount()-this.count;
	}
}

