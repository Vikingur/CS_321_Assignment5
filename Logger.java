import java.util.*;
import java.lang.*;
import java.io.*;

public class Logger
{
	public static String outFile;
	public static String inFile;
	public static PrintWriter out;
	
	public static void initLogger(String file)
	{
		inFile = file;
		outFile = "HRSLOG_"+inFile;
		File f = new File(outFile);
		try {
			out = new PrintWriter(f,"UTF-8");
		}
		catch (Exception e)
		{
			
		}
	}
	
	public static void closeWriter()
	{
		out.close();
	}
}