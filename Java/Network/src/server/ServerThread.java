package server;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread
{
	boolean stop=false;
	ChatRoom room;
	Client client;
	Socket serverSocket;
	BufferedReader inFromClient;
	DataOutputStream outToClient;
	
    public ServerThread(Socket serverSocket) 
    {
    	this.serverSocket =serverSocket;
    	try
    	{    		
    		inFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			outToClient = new DataOutputStream(serverSocket.getOutputStream());
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void sendMessage(String message)
    {
    	try 
    	{
    		System.out.println("Server control message "+message);
    		message=message.replaceAll("\n","\\*");
			outToClient.writeBytes(message+"\n");
		}
    	catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
    public void sendJoinResponse(int id)
    {
    	String message="JOIN_RESPONSE ";
    	if(id==-1)
    		message+="REFUSE name";
    	else if(id==-2)
    		message+="REFUSE room";
    	else
    		message+="OK "+id;
    	sendMessage(message);
    }
    public void sendSwitchToRoomResponse(boolean flag)
    {
    	String message="SWITCH_ROOM_RESPONSE ";
    	if(flag==true)
    	{
    		message+="OK";
    		sendMessage(message);
    	}
    	else
    	{
    		message+="REFUSE";
    		sendMessage(message);
    	}
    }
    public void sendAllMemberListResponse(String message)
    {
    	sendMessage("MEMBER_LIST_RESPONSE \n"+message);
    }
    public void sendRoomMemberListResponse(String message)
    {
    	sendMessage("MEMBER_ROOM_REQUEST \n"+message);
    }
    public void sendRoomsIDsListResponse(String message)
    {
    	sendMessage("ROOM_LIST_RESPONSE "+message);
    }
    public boolean controlMessage(String message)
    {
    	String[] token=message.split(" ");
    	if(token[0].equals("JOIN_REQUEST"))
    	{
    		int id=processJoinRequest(token[1],Integer.parseInt(token[2]));
    		sendJoinResponse(id);
    		return true;
    	}
    	if(token[0].equals("SWITCH_ROOM_REQUEST"))
    	{
    		int roomNum=Integer.parseInt(token[1]);
    		boolean switched=processSwitchRoomRequest(roomNum);
    		sendSwitchToRoomResponse(switched);
    		return true;
    	}
    	if(token[0].equals("LIST_ALL_MEMBERS"))
    	{
    		String names=processAllMemberListRequest();
    		sendAllMemberListResponse(names);
    		return true;
    	}
    	if(token[0].equals("ROOM_LIST_REQUEST"))
    	{
    		String roomsIDs=processRoomsIDsRequest();
    		sendRoomsIDsListResponse(roomsIDs);
    		return true;
    	}
    	if(token[0].equals("MEMBER_LIST_REQUEST"))
    	{
    		int roomID=Integer.parseInt(token[1]);
    		String result=processMemberListRequest(roomID);
    		sendRoomMemberListResponse(result);
    		return true;
    	}
    	if(token[0].equals("QUIT"))
    	{
    		processQuitRequest();
    		return true;
    	}
    	return false;
    }
    public int processJoinRequest(String name,int roomNum)
    {
    	if(Server.containsName(name))
    		return -1;
    	if(roomNum<1||roomNum>5)
    		return -2;
    	int id=Server.lastId++;
    	client=new Client(name,id,roomNum,serverSocket);
    	room=Server.rooms[roomNum];
    	room.addClient(client);
    	return id;
    }
    public boolean processSwitchRoomRequest(int newRoomNum)
    {
    	if(newRoomNum<1||newRoomNum>5)
    		return false;
    	ChatRoom previousRoom=Server.rooms[client.getRoom()];
    	previousRoom.removeClient(client);
    	ChatRoom newRoom=Server.rooms[newRoomNum];
    	newRoom.addClient(client);
    	room=newRoom;
    	client.setRoom(newRoomNum);
    	return true;
    }
    public String processAllMemberListRequest()
    {
    	return Server.getClientsNames();
    }
    public String processRoomsIDsRequest()
    {
    	return "Rooms IDs are :1,2,3,4,5";
    }
    public String processMemberListRequest(int roomID)
    {
    	if(roomID<1||roomID>5)
    		return "There is no such room !!!";
    	return Server.rooms[roomID].getClientsNames();
    }
    public void processQuitRequest()
    {
    	try
    	{
    		Server.rooms[client.getRoom()].removeClient(client);
    		serverSocket.close();
    		stop=true;
			System.out.println("called");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    public void run()
    {
    	String message=null;
    	while(stop==false)
    	{    		
			try
			{
				message = inFromClient.readLine();
				System.out.println("In server thread "+message);
				if(controlMessage(message))
					continue;
				message=client.getName()+" :"+message;
				room.broadcastClients(message);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("connection terminated ");
				break;
			}
    	}
    }    
}