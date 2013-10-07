package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperations
{
	public static String readFromFile(File file) throws FileNotFoundException
	{
		FileReader fread=new FileReader(file);
		BufferedReader reader=new BufferedReader(fread);
		String text="";
		String line="";
		try 
		{
			while((line=reader.readLine())!=null)
			{
				text+=line+"\n";
			}
			text=text.substring(0,text.length()-1);
			reader.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	public static void writeToFile(File file,String s)
	{
		try 
		{
			FileWriter wr=new FileWriter(file);
			BufferedWriter writer=new BufferedWriter(wr);
			writer.write(s);
			writer.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getExtn(String s)
	{
		String res="";
		for(int i=s.length()-1;i>=0;i--)
		{
			if(s.charAt(i)!='.')
				res=s.charAt(i)+res;
			else 
				break;
		}
		return res;
	}
	public static int countLines(File file,String[] extensions) throws Exception
	{
		int count=0;
		if(file.isDirectory())
		{
			File[]files=file.listFiles();
			if(files!=null)
			for(File childFile:files)
				count+=countLines(childFile,extensions);
		}
		else
		{
			boolean correctExtn=false;
			String fileExtn=getExtn(file.getName());
			for(String extionsion:extensions)
				if(extionsion.equals(fileExtn))
				{
					correctExtn=true;
					break;
				}
			if(!correctExtn)
				return 0;
			BufferedReader stdin;
			FileReader reader=new FileReader(file);
			stdin=new BufferedReader(reader);
			while(stdin.readLine()!=null)
				count++;
		}
		return count;
	}
	public static void main(String[]args) throws FileNotFoundException
	{
		File input=new File("C:\\Users\\Loai Ghoraba\\Desktop\\Input.txt");
		File output=new File("C:\\Users\\Loai Ghoraba\\Desktop\\Output.txt");
		writeToFile(output,readFromFile(input));
	}
}
