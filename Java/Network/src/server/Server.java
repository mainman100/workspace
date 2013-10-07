package server;
import java.net.*;
import java.io.*;

public class Server
{
	public static int lastId=0;
	public static ChatRoom[]rooms={
		new ChatRoom(0),
		new ChatRoom(1),
		new ChatRoom(2),
		new ChatRoom(3),
		new ChatRoom(4),
		new ChatRoom(5)};
	public static boolean containsName(String s)
	{
		for(int i=1;i<=5;i++)
			if(rooms[i].containsName(s))
				return true;
		return false;
	}
	public static String getClientsNames()
	{
		String result="";
		for(int i=1;i<=5;i++)
			result+=rooms[i].getClientsNames()+"\n";
		return result;
	}
	public static void main(String[]args) throws IOException
	{
		ServerSocket welcomeSocket = new ServerSocket(5000);
		while(true)
		{
			ServerThread d = new ServerThread(welcomeSocket.accept());
			d.start();
		}
	}
}
