package Testing;
import java.io.*;
public class ser implements Serializable
{
	String name="loai";
	public ser(String Name)
	{
		name=Name;
	}
	public static void main(String[]args)
	{
		ser loai=new ser("Hashem");
		try
		{
			FileOutputStream out=new FileOutputStream("save.ser");
			ObjectOutputStream foo=new ObjectOutputStream(out);
			foo.writeObject(loai);
			foo.close();
		}
		catch (Exception e)
		{
			System.exit(0);
		}
	}
}