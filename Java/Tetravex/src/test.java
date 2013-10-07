import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
public class test extends JPanel
{
	private static final long serialVersionUID = -3801363088978390882L;
	static ImageIcon picture=new ImageIcon("img36.jpg");
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		picture.paintIcon(this,g,0,0);
	}
	public static void main(String[]args)
	{
		JFrame f=new JFrame();
		f.setLayout(null);
		f.setSize(1000,1000);
		test loai=new test();
		loai.setSize(1000,1000);
		f.setContentPane(loai);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		loai.setVisible(true);
	}
}