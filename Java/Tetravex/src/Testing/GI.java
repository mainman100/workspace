package Testing;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
public class GI extends JFrame
{
	JButton black;
	JButton red;
	JTextField text;
	public GI()
	{
		super("Text");
		setLayout(null);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		black=new JButton("Black");
		black.setBounds(100,100,100,100);
		getContentPane().add(black);
		red=new JButton("Red");
		red.setBounds(0,0,100,100);
		getContentPane().add(red);
		text=new JTextField("Testing");
		text.setBounds(200,300,100,20);
		getContentPane().add(text);
		listener loai=new listener();
		black.addActionListener(loai);
		red.addActionListener(loai);
		setVisible(true);
	}
	public static void main(String[]args)
	{
		GI loai=new GI();
	}
	private class listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==black)
			{
				getContentPane().setBackground(Color.BLACK);
				black.setLocation(300,300);
				black.setText("loai");
				repaint();
			}
			else if(e.getSource()==red)
				getContentPane().setBackground(Color.RED);
			print();
		}
		public void print()
		{
			System.out.println("Hello");
		}
	}
}