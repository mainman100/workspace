package Suduko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JPanel{

	Suduko s;
	static final int width=70;
	static final int height=70;
	static Object[] values;
	public GUI(int side)
	{
		s=new Suduko(side);
		values=new Object[side*side+1];
		values[0]="Empty";
		for(int i=1;i<values.length;i++)
			values[i]=i+"";
		setLayout(null);
		setSize(width*(s.n+1), height*(s.n+1));
		int offset=20;
		for(int i=0;i<s.n;i++)
			for(int j=0;j<s.n;j++)
			{
				JButton cell=new JButton();
				cell.addActionListener(new CellListener(i, j));
				cell.setBounds(offset+j*width,offset+i*height, width, height);
				add(cell);
			}
	}
	public static void main(String[]args)
	{
		JFrame f=new JFrame();
		f.setSize(500,500);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH) ;
		f.setContentPane(new GUI(3));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	private class CellListener implements ActionListener
	{
		int row,col;
		boolean constant;
		public CellListener(int i,int j)
		{
			row=i;
			col=j;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(constant)
				return;
			String num =(String) JOptionPane.showInputDialog(null, null, (String)"chose number", 0, null, values, values[0]);
			if(num==null)
				return;
			if(num.equals("Empty"))
			{
				((JButton)e.getSource()).setText("");
				s.unPut(row, col, s.board[row][col]);
				return;
			}		
			int nu=Integer.parseInt(num);
			if(s.put(row, col, nu-1))
				((JButton)e.getSource()).setText(num);
		}
		
	}
}
