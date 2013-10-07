package Testing;
import java.io.*;
public class load 
{
	public static void main(String[]args)
	{
		try
		{
			FileInputStream in=new FileInputStream("save.ser");
			ObjectInputStream loai=new ObjectInputStream(in);
			Object one=loai.readObject();
			ser out=(ser)one;
			System.out.println(out.name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}