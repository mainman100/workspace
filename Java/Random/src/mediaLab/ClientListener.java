package mediaLab;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.*;

import javax.swing.JTextArea;

public class ClientListener extends Thread{

	MulticastSocket s;
	//Socket clientSocket
	DataInputStream clientIn;
	JTextArea textArea;

	public ClientListener(MulticastSocket s,
			JTextArea textArea,DataInputStream clientIn) {
		super();
		this.s = s;
		this.textArea = textArea;
		this.clientIn=clientIn;
	}

/*	public void run()
	{
		while(true)
		{
			try {
				String recieve=clientIn.readLine();
				recieve="Recieveing : "+recieve;
				textArea.append(recieve+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	public void run()
	{
		while(true)
		{
			 byte[] buf = new byte[1000];
			 DatagramPacket recv = new DatagramPacket(buf, buf.length);
			 try {
				s.receive(recv);
				String recieve=new String(buf);
				recieve="Recieveing : "+recieve;
				textArea.append(recieve+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
	}
}
