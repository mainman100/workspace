package client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;

public class Main
{
	public static Client client=new Client("");
	public static JFrame frame;
	public static chatPanel chatPanel;
	public static void main(String[]args)
	{
		frame=new JFrame();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e)
			{	
			}
			public void windowClosed(WindowEvent e)
			{
			}
			public void windowClosing(WindowEvent e)
			{
				client.sendQuitRequest();
			}
			public void windowDeactivated(WindowEvent e)
			{
			}
			public void windowDeiconified(WindowEvent e)
			{	
			}
			public void windowIconified(WindowEvent e)
			{	
			}
			public void windowOpened(WindowEvent e)
			{
			}
		});
		frame.setContentPane(new joinPanel());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
