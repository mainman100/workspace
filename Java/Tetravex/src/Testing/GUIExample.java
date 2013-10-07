package Testing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUIExample extends JFrame implements ActionListener
{
	JTextField text;
	public GUIExample()
	{
		setLayout(null);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button=new JButton("red");
		button.setBounds(100,100,100,30);
		button.setFocusPainted(false);
		button.setActionCommand("red");
		JButton button1=new JButton("Blue");
		button1.setActionCommand("blue");
		button1.setBounds(200,200,100,30);
		getContentPane().add(button1);
		button1.addActionListener(this);
		button.addActionListener(this);
		getContentPane().add(button);
		text=new JTextField("as");
		text.setBounds(200,250,50,20);
		getContentPane().add(text);	
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("red"))
			getContentPane().setBackground(Color.RED);
		else if(e.getActionCommand().equals("blue"))
		{
			getContentPane().setBackground(Color.BLUE);
			text.setText("blue");
		}
	}
	public static void main(String[]args)
	{
		GUIExample l=new GUIExample();
	}
}