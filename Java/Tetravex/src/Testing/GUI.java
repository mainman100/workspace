package Testing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener
{
	JLabel l;
	public GUI()
	{
		super();
		setSize(500,500);
		setLayout(null);
		l=new JLabel("I'm loai");
		JTextField t=new JTextField("You can change it");
		l.setBounds(100,100,100,20);
		t.setBounds(200,200,100,30);
		JButton button=new JButton("Abdelrahman");
		button.setBounds(20,20,100,50);
		add(button);
		add(l);
		add(t);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.addActionListener(this);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		l.setText("Event tigger");
	}
	public static void main(String[]args)
	{
		GUI g=new GUI();
	}
}