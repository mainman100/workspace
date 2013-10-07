package client;

import static client.Main.*;

import java.net.Socket;
import java.io.*;

public class ClientListenerThread extends Thread
{
	public void run()
	{
		BufferedReader inFromServer;
		String message,oldText;
		while(true)
		{
			try
			{
				inFromServer=new BufferedReader(new 
						InputStreamReader(
						client.getClientSocket().getInputStream()));	
				message=inFromServer.readLine();
				System.out.println("test "+message);
				System.out.println("damn");
				String temp=message;// if the message is not a control one, we will
				//restore it
				message=message.replaceAll("\\*","\n");//as * represents \n in control messages
				oldText=chatPanel.getOutputArea().getText();
				if(client.controlMessage(message)==true)
					message="Control message :"+client.getControlMessage();
				else
					message=temp;
				System.out.println("Client listener-->"+message);
				chatPanel.getOutputArea().setText(oldText+"\n"+message);
				chatPanel.repaint();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("End connection!!!!");
				System.exit(0);
			}	
		}
	}
}
