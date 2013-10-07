import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;


public class Assembler 
{
	static HashMap<String,String> map=new HashMap<String, String>();
	/**
	 * Returns all instructions in a file as an array of instructions
	 * @param file the file to read from
	 * @return an array representing the instructions
	 * @throws FileNotFoundException 
	 */
	public static String[] readFromFile(File file) throws FileNotFoundException {
		FileReader fread = new FileReader(file);
		BufferedReader reader = new BufferedReader(fread);
		String text = "";
		String line = "";
		LinkedList<String> res=new LinkedList<String>();
		try {
			while ((line = reader.readLine()) != null) {
				res.add(line);
				text += line + "\n";
			}
			text = text.substring(0, text.length() - 1);
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res.toArray(new String[0]);
	}
	/**
	 * Write the given String <code>s </code> to the given <code> file</code>
	 * @param file the file to write to
	 * @param s the string to write
	 * 
	 */
	public static void writeToFile(File file, String s) {
		try {
			
			FileWriter wr = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(wr);
			writer.write(s);
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * For instructions that uses addresses as their operands
	 * @param instOp the instruction operation "i.e AND, ADDI ...."
	 * @param addr the address operand in this instruction
	 * @return the hexadecimal value of the whole instruction (operation+address)
	 * 
	 */
	public static String getHex(String instOp,String addr)
	{
		addr=Integer.toHexString( 0x1000 | Integer.parseInt(addr)).substring(1).toUpperCase();
		if(instOp.equals("AND"))
			return "0"+addr;
		if(instOp.equals("ANDI"))
			return "8"+addr;
		if(instOp.equals("ADD"))
			return "1"+addr;
		if(instOp.equals("ADDI"))
			return "9"+addr;
		if(instOp.equals("LDA"))
			return "2"+addr;
		if(instOp.equals("LDAI"))
			return "A"+addr;
		if(instOp.equals("STA"))
			return "3"+addr;
		if(instOp.equals("STAI"))
			return "B"+addr;
		if(instOp.equals("BUN"))
			return "4"+addr;
		if(instOp.equals("BUNI"))
			return "C"+addr;
		if(instOp.equals("BSA"))
			return "5"+addr;
		if(instOp.equals("BSAI"))
			return "D"+addr;
		if(instOp.equals("ISZ"))
			return "6"+addr;
		if(instOp.equals("ISZI"))
			return "E"+addr;
		return null;
	}
	public static void main(String[]args) throws FileNotFoundException
	{
		//Key is the instruction and value is the binary code
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("CLA","7800");//7800 clear AC
		map.put("CLE","7400");//7400 
		map.put("CMA","7200");//7200
		map.put("CME","7100");//7100
		map.put("CIR","7080");
		map.put("CIL","7040");
		map.put("INC","7020");//7020
		map.put("SPA","7010");//7010
		map.put("SNA","7008");//7008
		map.put("SZA","7004");
		map.put("SZE","7002");
		map.put("HLT","7001");
		//--------------------
		map.put("INP","F800");
		map.put("OUT","F400");
		map.put("SKI","F200");
		map.put("SKO","F100");
		map.put("ION","F080");
		map.put("IOF","F040");
		//--------------------
		File file=new File("C:\\Users\\Loai_Ghoraba\\Desktop\\Assem.txt");
		String result="";
		String[]insts=readFromFile(file);
		for(int i=0;i<insts.length;i++)
		{
			String []temp=insts[i].split(" ");
			String hex="@"+Integer.toHexString( 0x10000 | i).substring(1).toUpperCase()+" ";
			if(temp.length==1)
				result+=hex+ map.get(temp[0])+"\n";
			else
				result+=hex+getHex(temp[0],temp[1])+"\n";
		}
		File out=new File("C:\\Users\\Loai_Ghoraba\\Documents\\My Dropbox\\DSD Project\\Runnable version\\Vasm.out");
		writeToFile(out, result);
	}
}
