package client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import static client.Main.client;

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
public class chatPanel extends javax.swing.JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String inputText;
	private String outputText;
	private JButton listRoomsIDButton;
	private JLabel roomNumberLabel;
	private JScrollPane jScrollPane1;
	private JTextField inputArea;
	private JTextArea outputArea;
	private JLabel seperateLabel;
	private JButton switchToRoomButton;
	private JLabel nameLabel;
	private JButton listAllMembersButton;
	private JButton listRoomMembersButton;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public chatPanel(Client c) {
		super();
		client=c;
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(540, 389));
			{
				listRoomsIDButton = new JButton();
				listRoomsIDButton.setText("List all the rooms");
				listRoomsIDButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							listRoomsIDButtonActionPerformed(evt);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					outputArea = new JTextArea();
					outputArea.setFont(new java.awt.Font("Courier New",1,16));
					jScrollPane1.setViewportView(outputArea);
					outputArea.setEditable(false);
				}
			}
			{
				listRoomMembersButton = new JButton();
				listRoomMembersButton.setText("List a room members");
				listRoomMembersButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							listRoomMembersButtonActionPerformed(evt);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			{
				listAllMembersButton = new JButton();
				listAllMembersButton.setText("List all the members in the chat");
				listAllMembersButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							listAllMembersButtonActionPerformed(evt);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			{
				nameLabel = new JLabel();
				nameLabel.setText("Your name is : "+client.getName());
			}
			{
				roomNumberLabel = new JLabel();
				roomNumberLabel.setText("Room number is :"+client.getRoomNum());
			}
			{
				switchToRoomButton = new JButton();
				switchToRoomButton.setText("Switch the room");
				switchToRoomButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							switchToRoomButtonActionPerformed(evt);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			{
				seperateLabel = new JLabel();
				seperateLabel.setBackground(new java.awt.Color(0,0,0));
				seperateLabel.setForeground(new java.awt.Color(0,0,0));
			}
			{
				inputArea = new JTextField();
				inputArea.setFont(new java.awt.Font("Courier New",1,14));
				inputArea.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						inputAreaKeyPressed(evt);
					}
				});
			}
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(inputArea, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
				    .addComponent(seperateLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(switchToRoomButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
				    .addComponent(listAllMembersButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
				    .addComponent(listRoomMembersButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
				    .addComponent(listRoomsIDButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(50)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(roomNumberLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
				            .addComponent(nameLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))));
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(50)
				        .addComponent(switchToRoomButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(22)
				        .addComponent(listAllMembersButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(32)
				        .addComponent(listRoomMembersButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(27)
				        .addComponent(listRoomsIDButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(seperateLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(inputArea, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(26)
				        .addComponent(roomNumberLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the inputText
	 */
	public String getInputText() {
		return inputText;
	}

	/**
	 * @param inputText the inputText to set
	 */
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	/**
	 * @return the outputText
	 */
	public String getOutputText() {
		return outputText;
	}

	/**
	 * @param outputText the outputText to set
	 */
	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	/**
	 * @return the seperateLabel
	 */
	public JLabel getSeperateLabel() {
		return seperateLabel;
	}

	/**
	 * @param seperateLabel the seperateLabel to set
	 */
	public void setSeperateLabel(JLabel seperateLabel) {
		this.seperateLabel = seperateLabel;
	}

	/**
	 * @return the nameLabel
	 */
	public JLabel getNameLabel() {
		return nameLabel;
	}

	/**
	 * @param nameLabel the nameLabel to set
	 */
	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	/**
	 * @param roomNumberLabel the roomNumberLabel to set
	 */
	public void setRoomNumberLabel(JLabel roomNumberLabel) {
		this.roomNumberLabel = roomNumberLabel;
	}

	/**
	 * @param inputArea the inputArea to set
	 */
	public void setInputArea(JTextField inputArea) {
		this.inputArea = inputArea;
	}

	/**
	 * @param outputArea the outputArea to set
	 */
	public void setOutputArea(JTextArea outputArea) {
		this.outputArea = outputArea;
	}

	/**
	 * @return the roomNumberLabel
	 */
	public JLabel getRoomNumberLabel() {
		return roomNumberLabel;
	}

	public JTextArea getOutputArea()
	{
		return outputArea;
	}
	public JTextField getInputArea()
	{
		return inputArea;
	}
	private void listAllMembersButtonActionPerformed(ActionEvent evt) throws IOException 
	{
		client.sendListAllMembersRequest();
	}
	
	private void listRoomMembersButtonActionPerformed(ActionEvent evt) throws IOException
	{
		String num=JOptionPane.showInputDialog("Enter the room number");
		int roomID=Integer.parseInt(num);
		client.sendMemberListRequestRequest(roomID);
	}
	
	private void switchToRoomButtonActionPerformed(ActionEvent evt) throws IOException
	{
		String num=JOptionPane.showInputDialog("Enter the room number");
		int roomID=Integer.parseInt(num);
		client.sendSwitchRoomRequest(roomID);
	}
	
	private void listRoomsIDButtonActionPerformed(ActionEvent evt) throws IOException
	{
		client.sendRoomListRequestRequest();
	}
	
	private void inputAreaKeyPressed(KeyEvent evt)
	{
		if(evt.getKeyCode()==10)
		{
			String inputText=inputArea.getText();
			client.sendMessage(inputText);
			inputArea.setText("");
			inputArea.repaint();
		}
	}
}
