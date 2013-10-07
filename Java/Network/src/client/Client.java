package client;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;
import static client.Main.chatPanel;

public class Client
{
	private String name;
	private int id=-1;
	private int roomNum=-1;
	private int requestRoomSwitch;
	private int requestRoomList;
	private Socket clientSocket;
	private String controlMessage;
	private DataOutputStream outToServer;
	
	public Client(String name) 
	{
		this.name=name;
		try {
			clientSocket=new Socket("localhost",5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outToServer=new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		roomNum=0;
	}
	public void sendMessage(String message)
	{
		try
		{
			outToServer.writeBytes(message+"\n");
		}
		catch(Exception e)
		{
			try
			{
				clientSocket.close();				
			}
			catch(Exception eo)
			{
				eo.printStackTrace();
			}
		}
	}
	public void sendSwitchRoomRequest(int roomID) 
	{
		sendMessage("SWITCH_ROOM_REQUEST "+roomID);
		requestRoomSwitch=roomID;
	}
	public void sendListAllMembersRequest()  
	{
		sendMessage("LIST_ALL_MEMBERS ");
	}
	public void sendMemberListRequestRequest(int roomID) 
	{
		sendMessage("MEMBER_LIST_REQUEST "+roomID);
		requestRoomList=roomID;
	}
	public void sendRoomListRequestRequest() 
	{
		sendMessage("ROOM_LIST_REQUEST");
	}
	public void sendQuitRequest() 
	{
		sendMessage("QUIT");
		try
		{
			clientSocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void processSwitchRoomResponse(String reply)
	{
		if(reply.contains("REFUSE"))
		{
			controlMessage="NO SUCH ROOM EXIST !!";
			return;
		}
		controlMessage="SWITCH SUCCESFUL TO ROOM "+requestRoomSwitch;
		roomNum=requestRoomSwitch;
		chatPanel.getRoomNumberLabel().setText("Room Number "+roomNum);
	}
	public void processListAllMemebersResponse(String list)
	{
		controlMessage="All members list :"+list;
	}
	public void processMemberListResponse(String list)
	{
		controlMessage="There is no such room !!!";
		controlMessage="Room "+requestRoomList+" members list :"+list;
	}
	public void processRoomListResponse(String list)
	{
		controlMessage=list;
	}
	public boolean controlMessage(String message)
	{
		String[]token=message.split(" ");
		if(token[0].equals("SWITCH_ROOM_RESPONSE"))
		{
			//System.out.println(token[1]);
			processSwitchRoomResponse(token[1]);
			return true;
		}
		if(token[0].equals("MEMBER_LIST_RESPONSE"))
		{
			String remain=message.substring(token[0].length());
			processListAllMemebersResponse(remain);
			return true;
		}
		if(token[0].equals("ROOM_LIST_RESPONSE"))
		{
			String remain=message.substring(token[0].length());
			processRoomListResponse(remain);
			return true;
		}
		if(token[0].equals("MEMBER_ROOM_REQUEST"))
		{
			String remain=message.substring(token[0].length());
			processMemberListResponse(remain);
			return true;
		}
		return false;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the clientSocket
	 */
	public Socket getClientSocket() {
		return clientSocket;
	}
	/**
	 * @param clientSocket the clientSocket to set
	 */
	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	/**
	 * @param roomNum the roomNum to set
	 */
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public int getRoomNum()
	{
		return roomNum;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public String getControlMessage()
	{
		return controlMessage;
	}
	public int getID()
	{
		return id;
	}
}
