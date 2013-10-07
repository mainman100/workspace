package Test;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends JFrame 
{
	private static final long serialVersionUID = -4324462325448036780L;
	LinkedList <String>savesList=new LinkedList<String>();
	LinkedList<player[]> scores=new LinkedList<player[]>();
	loadListener loadListener= new loadListener();
	JMenu loadMenu,HighScores;
	JMenuItem[] loadNames;
	Board c;
	boolean firstGame=true,load=false;
	int size;
	player player;
	public Main()
	{
		//**********Configure saves list************
		try
		{
			//Check up the save list,in case one of them is deleted
			//remove it from the list and re_write the list into the save file
			//also gets the list to have it during the program
			File file=new File("saves.ser");
			if(!file.exists())
				throw new FileNotFoundException();//If the file doesn't exist,an
			//exception will be catched and the file will be written ,this will 
			// always happen in the first time you run the game
			FileInputStream in=new FileInputStream("saves.ser");//Connect to the file
			ObjectInputStream reader=new ObjectInputStream(in);//Chain
			savesList=(LinkedList<String>)reader.readObject();//Read List
			reader.close();
			file=new File("Saves");//To get a list of saves in 
			String []names=file.list();//the save directory
			int j=0;//Counter to determine where we are in the saves list
			int n=savesList.size();//As the list size will change with removal
			for(int i=0;i<n;i++)//Merely to count
			{
				String temp=savesList.get(j);
				if(!find(temp,names))//If the save is in the list but not in 
					savesList.remove(j);//the directory,remove it
				else
					j++;//Else increment the position counter
			}
			if(savesList.size()!=n)//If some saves were deleted,re-write
			{					   //the saves list
				FileOutputStream out=new FileOutputStream("saves.ser");//connect
				ObjectOutputStream writer=new ObjectOutputStream(out);//chain
				writer.writeObject(savesList);//Save the modified list
				writer.close();	
			}
		}
		catch(FileNotFoundException e)
		{
			try
			{
				//create the saves.ser file and write empty list on it
				FileOutputStream out=new FileOutputStream("saves.ser");
				ObjectOutputStream writer=new ObjectOutputStream(out);
				writer.writeObject(savesList);
				writer.close();
			}
			catch(Exception E)
			{
				E.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{	//Get high scores
			File file=new File("scores.ser");
			if(!file.exists())
				throw new FileNotFoundException();//It will be catched and write
			//the file,this will always happen when the file does not exist,
			//like in the first time you run the game
			FileInputStream in=new FileInputStream("scores.ser");
			ObjectInputStream reader=new ObjectInputStream(in);
			scores=(LinkedList<player[]>)reader.readObject();
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			try
			{	//The first time you run the game,scores.ser will be created
				//and an empty list of five empty priority queue will be
				//written
				for(int i=2;i<=6;i++)//make five empty queues
				{
					player p[]=new player[6];
					scores.add(p);
					for(int j=0;j<6;j++)
						p[j]=new player("",-1);	
				}
				FileOutputStream out=new FileOutputStream("scores.ser");
				ObjectOutputStream  writer=new ObjectOutputStream(out);
				writer.writeObject(scores);
				
			}
			catch(Exception E)
			{
				E.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//**************************************************
		setLayout(null);
		setSize(500,500);
		//*****************Configure menus***************
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		//*******File menu*************
		JMenu fileMenu=new JMenu("File");
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);
		//********New game menu*****************
		JMenu newGame=new JMenu("New game");
		JMenuItem [] items=new JMenuItem[5];
		handler handler =new handler();
		for(int i=0;i<=4;i++)
		{
			items[i]=new JMenuItem((i+2)+"*"+(i+2));
			items[i].setActionCommand("newGame"+(i+2)+"");
			items[i].addActionListener(handler);
			newGame.add(items[i]);
		}
		fileMenu.add(newGame);
		//*********Save menu item*******
		JMenuItem save=new JMenuItem("save");
		save.setActionCommand("save");
		save.addActionListener(handler);
		fileMenu.add(save);
		//**********Load menu**************
		loadMenu=new JMenu("load");
		loadMenu.setActionCommand("load");
		loadMenu.addActionListener(handler);
		loadNames=new JMenuItem[savesList.size()];
		for(int i=0;i<savesList.size();i++)
		{
			String Item=savesList.get(i);
			loadNames[i]=new JMenuItem(Item);
			loadMenu.add(loadNames[i]);
			loadNames[i].addActionListener(loadListener);
		}
		fileMenu.add(loadMenu);
		//*******High scores menu*********
		HighScores=new JMenu("High scores");
		JMenuItem [] highScoresTypes=new JMenuItem[5];
		for(int i=0;i<=4;i++)
		{
			highScoresTypes[i]=new JMenuItem((i+2)+"*"+(i+2));
			highScoresTypes[i].setActionCommand("High scores"+(i+2)+"");
			highScoresTypes[i].addActionListener(handler);
			HighScores.add(highScoresTypes[i]);
		}
		menuBar.add(HighScores);
		//********************************
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH) ;
		setVisible(true);
	}
	public void paint(Graphics gd)//Paint Tetravex
	{
		super.paint(gd);
		Graphics2D g=(Graphics2D)gd;
		if(c==null&&load==false)
		{
			GradientPaint gradient= new GradientPaint(400,500,Color.RED,600,400,new Color(0,200,0));
			g.setPaint(gradient);
			g.setFont( new Font( "Serif", Font.BOLD + Font.ITALIC, 350) );
			g.drawString("Tetravex",0,400);
		}
	}
	public void save(String saveName)
	{
		try
		{
			String fileName="Saves\\"+saveName+".ser";//Make the file in the saves folder
			File test=new File(fileName);
			if(test.exists()==false)
			{
				savesList.addFirst(saveName);
				JMenuItem item=new JMenuItem(saveName);
				item.addActionListener(loadListener);
				loadMenu.insert(item,0);
			}
			//***Save the data of the game,which is:Solve board,mixed board,
			//evaluated board,stack of played moves,elapsed time****
			FileOutputStream out=new FileOutputStream(fileName);
			ObjectOutputStream writer=new ObjectOutputStream(out);
			writer.writeObject(c.getSolvedBoard());
			writer.writeObject(c.getMixedBoard());
			writer.writeObject(c.getEvaluatedBoard());
			writer.writeObject(c.getStack());
			writer.writeObject(new Integer(c.getElapsedTime()));
			//********Re-write the saves list**********
			out=new FileOutputStream("saves.ser");
			writer=new ObjectOutputStream(out);
			writer.writeObject(savesList);
			writer.close();
		}
		catch (Exception E)
		{
			E.printStackTrace();
		}
	}
	public void load(String loadName)
	{
		try
		{
			String fileName="Saves\\"+loadName+".ser";;
			//*****Load data*****
			FileInputStream in=new FileInputStream(fileName);
			ObjectInputStream loai=new ObjectInputStream(in);
			square[][] solved=(square[][])loai.readObject();
			square[][] mixed=(square[][])loai.readObject();
			square[][] evaluated=(square[][])loai.readObject();
			Stack<String> s=(Stack<String>)loai.readObject();
			int elapsedTime=((Integer)loai.readObject()).intValue();
			if(c!=null)//Remove old board
				remove(c);
			c=new Board(solved,mixed,evaluated,s,elapsedTime);
			c.setBounds(50,20,1500,1500);
			addBoard();
			size=c.getsize();
			add(c);
			repaint();
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
	}
	public boolean find(String s,String[]list)//Checks whether a string name
	{										  //is in a list
		s=s+".ser";
		for(int i=0;i<list.length;i++)
		{
			if(s.equals(list[i]))
				return true;
		}
		return false;
	}
	public void Notify()//comapre the current score with others
	{
		player.setTime(c.getElapsedTime());
		player[]p=scores.get(size-2);
		p[5]=player;
		Arrays.sort(p);
		try	//************Save the score in high scores*******
		{
			FileOutputStream out=new FileOutputStream("scores.ser");
			ObjectOutputStream writer=new ObjectOutputStream(out);
			writer.writeObject(scores);	
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void addBoard()
	{
		getBoard().addMain(this);
	}
	public Board getBoard()
	{
		return c;
	}
	public static void main(String[]args)throws IOException
	{
		Main tetra=new Main();
	}
	private class handler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String command=e.getActionCommand();
			if(command.equals("save")&&c!=null)//showWindow
			{
				String name=JOptionPane.showInputDialog( "Enter save name" );
				if(name!=null)
				{
					name.toUpperCase();
					save(name);
				}
			}
			else if(command.length()>7&&command.substring(0,7).equals("newGame"))
			{
				if(firstGame==true)//Create the player
				{
					String name=JOptionPane.showInputDialog( "Enter your name" );
					player=new player(name,-1);
					firstGame=false;
				}
				if(c!=null)//If it is not the first time,remove the old board
						   //and stop the timer
				{
					remove(c);
					c.stopTimer();
				}
				c=new Board(Integer.parseInt(command.substring(7)),0);
				c.setBounds(50,20,1500,1500);
				addBoard();
				size=c.getsize();
				add(c);
				repaint();				
			}
			else if(command.length()>11&&command.substring(0,11).equals("High scores"))
			{//show high scores
				int n=Integer.parseInt(command.substring(11));
				showHighScores(n);
			}
		}
	}
	public void showHighScores(int n)
	{
		String data="";
		player p[]=scores.get(n-2);
		for(int i=0;i<6&&p[i].getTime()!=-1;i++)
			data+=p[i]+"\n";
		JOptionPane.showMessageDialog(null,data,"High scores for size "+n+"*"+n,JOptionPane.PLAIN_MESSAGE);
		repaint();
	}
	private class loadListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			load=true;
			String name=e.getActionCommand();
			if(c!=null)
				c.stopTimer();
			if(name!=null)
				load(name);

		}
	}
}