package Testing;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class test extends JFrame
{
	public test()
	{
		super("GUI test");
		setLayout(null);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draw button=new draw();
		button.setBounds(100,100,100,200);
		getContentPane().add(button);
		JTextField text=new JTextField("Edit");
		text.setBounds(200,100,100,20);
		//getContentPane().add(text);
		button.setBounds(100,100,100,50);
		setVisible(true);
	}
	public static void main(String[]args)
	{
		test l=new test();
	}
}