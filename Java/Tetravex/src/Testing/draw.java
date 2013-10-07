package Testing;
import javax.swing.*;
import java.awt.*;
public class draw extends JButton
{
	public void paintComponent(Graphics g)
	{
		g.drawLine(0,0,20,20);
		g.setColor(Color.red);
		g.fillOval(0,0,50,30);
		g.setColor(Color.blue);
		g.setFont(new Font( "Serif", Font.BOLD, 20 ));
		g.drawString("6",20,30);
	}
}