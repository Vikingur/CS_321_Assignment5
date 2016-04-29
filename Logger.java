import java.util.*;
import java.lang.*;
import java.io.*;

public class Logger
{
	public static String outFile;
	
	public static void initLogger(String file) 
	{
		File f = new File(file);
		file = f.getName();
		outFile = "HRSLOG_"+file;
	}

	public static void writeln(String s)
	{
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(outFile,true));
			out.write(s+"\n");
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Error: "+e);
		}
	}
}