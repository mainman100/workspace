package cipher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import static cipher.Mapping.*;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Main extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel fromLabel;
	private JButton okButton;
	private JTextField keyLabel;
	private JRadioButton specifiedKey;
	private JRadioButton defaultKey;
	private ButtonGroup keyButtonGroup;
	private JRadioButton decodeRadio;
	private JRadioButton encodeRadio;
	private JTextField toText;
	private JTextField fromText;
	private JButton toButton;
	private JButton fromButton;
	private JLabel toLabel;

	/**
	 * Auto-generated main method to display this JFrame
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main inst = new Main();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public Main() {
		super();
		initGUI();
		ButtonGroup radioGroup = new ButtonGroup();
		keyButtonGroup = new ButtonGroup();
		radioGroup.add(encodeRadio);
		radioGroup.add(decodeRadio);
		keyButtonGroup.add(defaultKey);
		keyButtonGroup.add(specifiedKey);
	}

	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setPreferredSize(new java.awt.Dimension(400, 300));
			this.setResizable(false);
			{
				fromLabel = new JLabel();
				fromLabel.setText("Read from file");
			}
			{
				defaultKey = new JRadioButton();
				defaultKey.setText("Use default key");
				defaultKey.setSelected(true);
				defaultKey.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						defaultKeyActionPerformed(evt);
					}
				});
			}
			{
				specifiedKey = new JRadioButton();
				specifiedKey.setText("Use specified key");
				specifiedKey.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						specifiedKeyActionPerformed(evt);
					}
				});
			}
			{
				toLabel = new JLabel();
				toLabel.setText("Write to File");
			}
			{
				fromButton = new JButton();
				fromButton.setText("Browes");
				fromButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						fromButtonActionPerformed(evt);
					}
				});
			}
			{
				keyLabel = new JTextField();
				keyLabel.setText("War is for us");
				keyLabel.setEditable(false);
			}
			{
				okButton = new JButton();
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							okButtonActionPerformed(evt);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			{
				toButton = new JButton();
				toButton.setText("Browse");
				toButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						toButtonActionPerformed(evt);
					}
				});
			}
			{
				encodeRadio = new JRadioButton();
				encodeRadio.setText("Encode");
				encodeRadio.setSelected(true);
			}
			{
				decodeRadio = new JRadioButton();
				decodeRadio.setText("Decode");
			}
			{
				fromText = new JTextField();
			}
			{
				toText = new JTextField();
			}
			thisLayout
					.setVerticalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap(53, 53)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													fromLabel,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													37,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													fromText,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													fromButton,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													toLabel,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													37,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													toText,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													toButton,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													encodeRadio,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													defaultKey,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													decodeRadio,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													specifiedKey,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													keyLabel,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE));
			thisLayout
					.setHorizontalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap()
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addGroup(
													thisLayout
															.createSequentialGroup()
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					toLabel,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					78,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					fromLabel,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					78,
																					GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.RELATED)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					toButton,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					72,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					fromButton,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					72,
																					GroupLayout.PREFERRED_SIZE))
															.addGap(16)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					toText,
																					GroupLayout.Alignment.LEADING,
																					0,
																					193,
																					Short.MAX_VALUE)
																			.addComponent(
																					fromText,
																					GroupLayout.Alignment.LEADING,
																					0,
																					193,
																					Short.MAX_VALUE)))
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addPreferredGap(
																	toLabel,
																	decodeRadio,
																	LayoutStyle.ComponentPlacement.INDENT)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					decodeRadio,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					93,
																					GroupLayout.PREFERRED_SIZE)
																			.addGroup(
																					GroupLayout.Alignment.LEADING,
																					thisLayout
																							.createSequentialGroup()
																							.addComponent(
																									encodeRadio,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.PREFERRED_SIZE)
																							.addGap(32))
																			.addGroup(
																					GroupLayout.Alignment.LEADING,
																					thisLayout
																							.createSequentialGroup()
																							.addComponent(
																									okButton,
																									GroupLayout.PREFERRED_SIZE,
																									73,
																									GroupLayout.PREFERRED_SIZE)
																							.addGap(20)))
															.addGap(37)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					specifiedKey,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					110,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					defaultKey,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					110,
																					GroupLayout.PREFERRED_SIZE))
															.addGap(11)
															.addComponent(
																	keyLabel,
																	0,
																	102,
																	Short.MAX_VALUE)))
							.addContainerGap(21, 21));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fromButtonActionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser("Choose files "
				+ "you want to number");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = chooser.showOpenDialog(null);
		if (result == JFileChooser.CANCEL_OPTION)
			System.exit(0);
		fromText.setText(chooser.getSelectedFile().getAbsolutePath());
	}

	private void toButtonActionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser("Choose files "
				+ "you want to number");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = chooser.showOpenDialog(null);
		if (result == JFileChooser.CANCEL_OPTION)
			System.exit(0);
		toText.setText(chooser.getSelectedFile().getAbsolutePath());
	}

	private void specifiedKeyActionPerformed(ActionEvent evt) {
		keyLabel.setEditable(true);
	}

	private void defaultKeyActionPerformed(ActionEvent evt) {
		keyLabel.setEditable(false);
	}

	private void okButtonActionPerformed(ActionEvent evt) throws IOException,
			ClassNotFoundException {
		File from = new File(fromText.getText());
		File to = new File(toText.getText());
		String key = keyLabel.getText();
		if (encodeRadio.isSelected())
			encode(from, to, key);
		else
			decode(from, to, key);
		System.exit(0);
	}
}
