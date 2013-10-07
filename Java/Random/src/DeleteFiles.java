import java.io.File;


public class DeleteFiles {
	public static void main(String[] args) {
		File folder=new File("D:\\Data\\Private\\Mobile\\Images\\Friends");
		File[]files=folder.listFiles();
		for(File file:files)
		{
			String name=file.getName();
			if(name.substring(name.length()-7).equals("(2).jpg"))
				file.delete();
		}
	}
}
