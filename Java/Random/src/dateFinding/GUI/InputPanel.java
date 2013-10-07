package dateFinding.GUI;

import dateFinding.Engine.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

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
public class InputPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4298889356688968336L;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private JLabel messageLabel;
	private JTextField dayField;
	private JLabel dayLabel;
	private JLabel result;
	private JLabel dayAt;
	private JButton ok;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JTextField yearField;
	private JTextField monthField;
	public InputPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				messageLabel = new JLabel();
				messageLabel.setText("Enter the date");
			}
			{
				dayField = new JTextField();
				dayField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dayFieldActionPerformed(evt);
					}
				});
			}
			{
				monthField = new JTextField();
				monthField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						monthFieldActionPerformed(evt);
					}
				});
			}
			{
				yearField = new JTextField();
				yearField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						yearFieldActionPerformed(evt);
					}
				});
			}
			{
				dayLabel = new JLabel();
				dayLabel.setText("day");
			}
			{
				monthLabel = new JLabel();
				monthLabel.setText("month");
			}
			{
				yearLabel = new JLabel();
				yearLabel.setText("year");
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("/");
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("/");
			}
			{
				ok = new JButton();
				ok.setText("ok");
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						okActionPerformed(evt);
					}
				});
			}
			{
				result = new JLabel();
				result.setText("The day is :");
			}
			{
				dayAt = new JLabel();
				dayAt.setFont(new java.awt.Font("Tahoma",3,18));
				dayAt.setForeground(new java.awt.Color(0,0,128));
			}
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addContainerGap(21, 21)
					.addComponent(messageLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(61)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(dayLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(monthLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(yearLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(ok, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					    .addComponent(dayField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(monthField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(yearField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(thisLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addComponent(result, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 43, Short.MAX_VALUE))
					    .addGroup(thisLayout.createSequentialGroup()
					        .addComponent(dayAt, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 0, Short.MAX_VALUE)))
					.addContainerGap(32, 32));
				thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(thisLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addGroup(thisLayout.createParallelGroup()
					            .addComponent(result, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                .addComponent(dayField, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					                .addGap(13))
					            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                .addComponent(dayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					                .addGap(44)))
					        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addGroup(thisLayout.createParallelGroup()
					            .addComponent(monthField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                .addComponent(monthLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					                .addGap(21)))
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addGroup(thisLayout.createParallelGroup()
					            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					                .addGap(46))
					            .addGroup(thisLayout.createSequentialGroup()
					                .addPreferredGap(jLabel2, yearField, LayoutStyle.ComponentPlacement.INDENT)
					                .addGroup(thisLayout.createParallelGroup()
					                    .addComponent(yearField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					                        .addComponent(yearLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					                        .addGap(26)))))
					        .addGap(48)
					        .addComponent(ok, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 63, Short.MAX_VALUE))
					    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					        .addPreferredGap(result, messageLabel, LayoutStyle.ComponentPlacement.INDENT)
					        .addComponent(messageLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					        .addGap(11)
					        .addComponent(dayAt, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 0, Short.MAX_VALUE)))
					.addContainerGap(21, 21));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getDate()
	{
		try
		{
			int day=Integer.parseInt(dayField.getText());
			int month=Integer.parseInt(monthField.getText());
			int year=Integer.parseInt(yearField.getText());	
			Date date=new Date(day,month,year);
			String result=date.getDate();
			dayAt.setText(result);
		}
		catch(Exception e)
		{
			dayAt.setText("No such date exist !!");
		}
	}
	private void okActionPerformed(ActionEvent evt) 
	{
		getDate();
	}
	private void dayFieldActionPerformed(ActionEvent evt)
	{
		getDate();
	}
	
	private void monthFieldActionPerformed(ActionEvent evt) 
	{
		getDate();
	}
	
	private void yearFieldActionPerformed(ActionEvent evt) 
	{
		getDate();
	}
}
