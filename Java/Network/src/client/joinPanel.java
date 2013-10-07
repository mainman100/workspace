package client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import client.Client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import client.chatPanel;
import static client.Main.*;



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
public class joinPanel extends javax.swing.JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTextField nameField;
	private JTextField roomField;
	private JButton nextButton;
	private JLabel roomLabel;
	private JLabel nameLabel;

	public joinPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				nameField = new JTextField();
			}
			{
				roomField = new JTextField();
			}
			{
				nextButton = new JButton();
				nextButton.setText("Next");
				nextButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						nextButtonActionPerformed(evt);
					}
				});
			}
			{
				nameLabel = new JLabel();
				nameLabel.setText("Enter your name");
			}
			{
				roomLabel = new JLabel();
				roomLabel.setText("Enter the room number");
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(39, 39)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(nameField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(nameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(73)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(roomField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
				    .addComponent(roomLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(56)
				.addComponent(nextButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(67, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(roomLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
				        .addGap(36))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(17)
				        .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
				        .addGap(43)))
				.addGap(25)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(roomField, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(153, Short.MAX_VALUE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendJoinRequest(int roomID) 
	{
		client.sendMessage("JOIN_REQUEST "+nameField.getText()+" "+roomID);
	}
	public boolean processJoinResponse(String reply,String error)
	{
		String errorMessage="";
		if(reply.equals("REFUSE"))
		{
			if(error.equals("name"))
				errorMessage="Change your name first then try again !!";
			else if(error.equals("room"))
				errorMessage="There is no such room !!";
			JOptionPane.showMessageDialog(null,errorMessage);
			return false;
		}
		client.setId(Integer.parseInt(error));
		JOptionPane.showMessageDialog(null,"Your id is "+client.getID());
		return true;
	}
	private void nextButtonActionPerformed(ActionEvent evt)
	{
		try
		{
			sendJoinRequest(Integer.parseInt(roomField.getText()));
			BufferedReader reader=new BufferedReader(new InputStreamReader(client.getClientSocket().getInputStream()));
			String response=reader.readLine();
			String[]token=response.split(" ");
			boolean flag=processJoinResponse(token[1],token[2]);
			if(flag==true)//move to next panel
			{
				client.setName(nameField.getText());
				client.setRoomNum(Integer.parseInt(roomField.getText()));
				chatPanel=new chatPanel(client);
				frame.setContentPane(chatPanel);
				frame.pack();
				frame.repaint();
				System.out.println("here");
				ClientListenerThread listener=new ClientListenerThread();
				listener.start();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Wrong Input!!!");
		}
	}

}
