package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatRoom
{
	ArrayList<Client> clients=new ArrayList<Client>();
	int num;
	public ChatRoom(int num)
	{
		this.num=num;
	}
	public void addClient(Client c)
	{
		clients.add(c);
	}
	public void removeClient(Client c)
	{
		clients.remove(c);
	}
	public boolean containsName(String newName)
	{
		for(int i=0;i<clients.size();i++)
			if(clients.get(i).getName().equals(newName))
				return true;
		return false;
	}
	public String getClientsNames()
	{
		String result="Clients in room "+num+" :\n";
		for(int i=0;i<clients.size();i++)
			result+=clients.get(i).getName()+"\n";
		return result;
	}
	public void broadcastClients(String message) throws IOException
	{
		DataOutputStream outToClient;
		Socket serverSocket;
		for(int i=0;i<clients.size();i++)
		{
			serverSocket=clients.get(i).getServerSocket();
			outToClient=new DataOutputStream(serverSocket.getOutputStream());
			System.out.println("Server message :"+message);
			outToClient.writeBytes(message + "\n");
		}
	}
}
