package server;

import java.net.Socket;

public class Client
{
	private String name;
	private int id;
	private int roomNum;
	private Socket serverSocket;
	public Client(String name, int id,int room,Socket s) 
	{
		super();
		this.name = name;
		this.id = id;
		this.roomNum=room;
		serverSocket=s;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	/**
	 * @return the room
	 */
	public int getRoom() 
	{
		return roomNum;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(int room)
	{
		this.roomNum = room;
	}
	/**
	 * @return the serverSocket
	 */
	public Socket getServerSocket()
	{
		return serverSocket;
	}
	/**
	 * @param serverSocket the serverSocket to set
	 */
	public void setServerSocket(Socket serverSocket) 
	{
		this.serverSocket = serverSocket;
	}
}
