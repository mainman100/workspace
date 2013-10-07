



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import project.*;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class NewJApplet extends javax.swing.JApplet {
	private JButton RLCButton;
	private JButton LZWButton;
	private JTextField input;
	private JLabel ration;
	private JTextField factorField;
	private JTextField outputField;
	private JLabel output;
	private JLabel label;
	private JButton HuffmanButton;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this 
	* JApplet inside a new JFrame.
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				NewJApplet inst = new NewJApplet();
				frame.getContentPane().add(inst);
				((JComponent)frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.pack();
				frame.setVisible(true);
			}
		});

	}
	
	public NewJApplet() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			{
				RLCButton = new JButton();
				RLCButton.setText("RLC");
				RLCButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						RLCButtonActionPerformed(evt);
					}
				});
			}
			{
				HuffmanButton = new JButton();
				HuffmanButton.setText("Huffman");
				HuffmanButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						HuffmanButtonActionPerformed(evt);
					}
				});
			}
			{
				LZWButton = new JButton();
				LZWButton.setText("LZW");
				LZWButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						LZWButtonActionPerformed(evt);
					}
				});
			}
			{
				ration = new JLabel();
				ration.setText("Compression ratio");
			}
			{
				factorField = new JTextField();
				factorField.setEditable(false);
			}
			{
				input = new JTextField();
			}
			{
				label = new JLabel();
				label.setText("Enter your String here");
			}
			{
				output = new JLabel();
				output.setText("Output");
			}
			{
				outputField = new JTextField();
				outputField.setEditable(false);
			}
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(24, 24)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(input, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(label, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addComponent(RLCButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(HuffmanButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(LZWButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 18, Short.MAX_VALUE)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(outputField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(output, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(factorField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					    .addComponent(ration, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap());
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(thisLayout.createParallelGroup()
					    .addComponent(label, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(RLCButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					        .addGap(48))
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(HuffmanButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(48))
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(LZWButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					        .addGap(48))
					    .addGroup(thisLayout.createSequentialGroup()
					        .addGap(15)
					        .addGroup(thisLayout.createParallelGroup()
					            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                .addComponent(output, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					                .addGap(48))
					            .addComponent(ration, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))))
					.addGroup(thisLayout.createParallelGroup()
					    .addComponent(outputField, GroupLayout.Alignment.LEADING, 0, 305, Short.MAX_VALUE)
					    .addComponent(factorField, GroupLayout.Alignment.LEADING, 0, 305, Short.MAX_VALUE)
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addPreferredGap(outputField, input, LayoutStyle.ComponentPlacement.INDENT)
					        .addComponent(input, 0, 295, Short.MAX_VALUE)))
					.addContainerGap(40, 40));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void RLCButtonActionPerformed(ActionEvent evt) 
	{
		String text=input.getText();
		Comp encoded=RLC.compress(text);
		outputField.setText(encoded.getText());
		factorField.setText(encoded.getFactor()+"");
	}
	
	private void HuffmanButtonActionPerformed(ActionEvent evt) 
	{
		String text=input.getText();
		Comp encoded=Huffman.compress(text);
		outputField.setText(encoded.getText());
		factorField.setText(encoded.getFactor()+"");
	}
	
	private void LZWButtonActionPerformed(ActionEvent evt) 
	{
		String text=input.getText();
		Comp encoded=LZW.compress(text);
		outputField.setText(encoded.getText());
		factorField.setText(encoded.getFactor()+"");
	}

}
