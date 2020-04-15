//a single string,and treating a file as an Arraylist
package P18;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFile extends ArrayList{
	//Read a file as a single string;
	public static String read(String fileName) throws IOException{
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader in = new BufferedReader(new FileReader(
					new File(fileName).getAbsoluteFile()));
			try{
				String s;
				while((s=in.readLine())!=null){
					sb.append(s);
					sb.append("\n");
				}
			}finally{
				in.close();
			}
		}catch(IOException e){
			throw e;
		}
		return sb.toString();
	}
	//write a single file in one method call;
	public static void write(String fileName,String text) throws IOException{
		try{
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try{
				out.print(text);
			}finally{
				out.close();
			}
		}catch(IOException e){
			throw e;
		}
	}
	public TextFile(String name,String spliter) throws IOException{
		super(Arrays.asList(read(name).split(spliter)));
		if(get(0).equals("")) remove(0);
	}
	
	public TextFile(String name) throws IOException{
		this(name,"\n");
	}

	public static void main(String[] args){
		
	}
}
