package mediaLab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class ChatWindow extends JFrame {
	
	protected JTextField textField;
    protected JTextArea textArea;
    protected JButton button;
    
    protected JButton connectButton;
    protected JTextField connectField;
	protected JButton listenButton;
    
    protected String name;
    
    protected Socket clientSocket;
	protected ServerSocket serverSocket;
	protected MulticastSocket multi;
	protected DataOutputStream clientOut;
	protected DataInputStream clientIn;
    protected InetAddress group;
    protected MulticastSocket s ;
    public ChatWindow(String name){
    	super(name+" chat");
    	this.name = name;
    	initComponents();
    }
    
    public void initComponents(){
    	Container Sframe = getContentPane();
		Sframe.removeAll();
		Sframe.setLayout(null);
		
		this.textField = new JTextField();
		Sframe.add(this.textField);
		
		this.textArea = new JTextArea();
		Sframe.add(this.textArea);
		
		this.button = new JButton("Send");
		Sframe.add(this.button);
		
		this.textField.setBounds(10, 10, 600, 20);
		this.button.setBounds(10+this.textField.getX()+this.textField.getWidth(),
				this.textField.getY(),100,20);
		this.textArea = new JTextArea();
		
		JScrollPane jScrollPane = new JScrollPane(this.textArea);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setBounds(10,10+this.textField.getY()+this.textField.getHeight(),
				this.textField.getX()+this.textField.getWidth()+this.button.getWidth(),300);
		Sframe.add(jScrollPane);
		
		this.connectField = new JTextField();
		this.connectField.setBounds(10,jScrollPane.getY()+jScrollPane.getHeight()+10,300,20);
		this.connectButton = new JButton("Connect");
		this.connectButton.setBounds(10+this.connectField.getX()+this.connectField.getWidth(),
				this.connectField.getY(),150,20);
		this.listenButton = new JButton("Listen");
		this.listenButton.setBounds(10+this.connectButton.getX()+this.connectButton.getWidth(),
				this.connectField.getY(),100,20);
		
		Sframe.add(this.connectButton);
		Sframe.add(this.connectField);
		Sframe.add(this.listenButton);
		
		
		int winWidth = this.textField.getX()*2+this.button.getX()+this.button.getWidth();
		int winHeight = this.textField.getY()*6+jScrollPane.getHeight()+jScrollPane.getY()+20;
		
		this.button.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event){
				
				try {
					sendButtonPressed();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
			});
		
		this.connectButton.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event){
				
				try {
					connectButtonPressed();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
			});
		
		this.listenButton.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event){
				
				try {
					listenButtonPressed();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
			});
		
		setSize(winWidth,winHeight);
	    setVisible(true);
	    validate();
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    public void appendToTextArea(String text){
    	this.textArea.append(text+"\n");
    }
    
/*    public void sendButtonPressed() throws IOException{
    	String send=textField.getText();
    	textArea.append("Sending : "+send+"\n");
    	textField.setText(null);
    	clientOut.writeBytes(send+"\n");
    }*/
    public void sendButtonPressed() throws IOException{
    	String send=textField.getText();
    	//textArea.append("Sending : "+send+"\n");
    	textField.setText(null);
    	DatagramPacket hi = new DatagramPacket(send.getBytes(), send.length(),
                group, 6789);
    	
    	s.send(hi);
    }
    /*public void connectButtonPressed() throws IOException{
    	String IP=connectField.getText();
    	
		clientSocket=new Socket(IP, 5000);
		clientOut=new DataOutputStream(clientSocket.getOutputStream());
		clientIn=new DataInputStream(clientSocket.getInputStream());
		ClientListener listener=new ClientListener(clientSocket, textArea, clientIn);
		listener.start();
    }*/
    
    public void connectButtonPressed() throws IOException{
    	group = InetAddress.getByName("228.5.6.7");
    	s= new MulticastSocket(6789);
    	s.joinGroup(group);    
		ClientListener listener=new ClientListener(s, textArea,clientIn);
		listener.start();

    }
	
	public void listenButtonPressed() throws UnknownHostException, IOException{

		/*serverSocket=new ServerSocket(5000);
		clientSocket=serverSocket.accept();
		clientOut=new DataOutputStream(clientSocket.getOutputStream());
		clientIn=new DataInputStream(clientSocket.getInputStream());
		ClientListener listener=new ClientListener(clientSocket, textArea,clientIn);
		listener.start();*/
	}
    
    public static void main(String[]args){
    	ChatWindow cw = new ChatWindow("Jack");
    }
    
}

