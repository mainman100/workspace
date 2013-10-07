import java.io.*;

public class LinesCount 
{
	public static void main(String[]args) throws Exception
	{
		//D:\Data\Java\Eclipse\Work space\smartSoft\play
		//Replace this with your scrum folder path
		String playPath="D:\\play-1.0.1\\scrum";
	//	String playPath="D:\\Data\\Java\\Eclipse\\Work space\\smartSoft\\play";
		File controllers=new File(playPath+"\\app\\controllers");
		File models=new File(playPath+"\\app\\models");
		File views=new File(playPath+"\\app\\views");
		File tests=new File(playPath+"\\test");
		int controllersCount=countLines(controllers);
		int modelsCount=countLines(models);
		int viewsCount=countLines(views);
		int testsCount=countLines(tests);
		System.out.println("Number of lines in consotrollers folder : "+controllersCount);
		System.out.println("Number of lines in models folder : "+modelsCount);
		System.out.println("Number of lines in views folder : "+viewsCount);
		System.out.println("Number of lines in tests folder : "+testsCount);
		System.out.println("Total number of lines : "+(controllersCount+modelsCount+viewsCount+testsCount));
	}
	/**
	 * Returns the number of java and HTML lines in the given file (the parameter can be file or directory)
	 * @author Loai_Ghoraba
	 * @param file the folder or the file to count the lines in
	 * @return the number of java and HTML lines in the given file or directory
	 * @throws Exception 
	 */
	public static int countLines(File file) throws Exception
	{
		int count=0;
		if(file.isDirectory())
		{
			File[]files=file.listFiles();
			if(files!=null)
			for(File childFile:files)
				count+=countLines(childFile);
		}
		else
		{
			if(!getExtn(file.getName()).equals("java")&&!getExtn(file.getName()).equals("html"))
				return 0;
			BufferedReader stdin;
			FileReader reader=new FileReader(file);
			stdin=new BufferedReader(reader);
			while(stdin.readLine()!=null)
				count++;
		}
		return count;
	}
	/**
	 * Returns the extension of a file
	 * @author Loai_Ghoraba
	 * @param fileName the name of the file
	 * @return the extension of a file
	 * 
	 */
	public static String getExtn(String fileName)
	{
		String res="";
		for(int i=fileName.length()-1;i>=0;i--)
		{
			if(fileName.charAt(i)!='.')
				res=fileName.charAt(i)+res;
			else 
				break;
		}
		return res;
	}
}


