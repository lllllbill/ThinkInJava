package P18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class E22 {
	public static List<String> command(String command) {
		boolean err = false;
		List<String> ls = new ArrayList<String>(); 
		try {
			Process process = new ProcessBuilder(
				command.split(" ")).start();
			BufferedReader results = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
			String s;			
			while((s = results.readLine()) != null) {
				ls.add(s);
			}
			BufferedReader errors = new BufferedReader(
				new InputStreamReader(process.getErrorStream()));
			while((s = errors.readLine()) != null) { 
				System.err.println(s);
				err = true;
			}
		} catch(Exception e) {
			if(!command.startsWith("CMD /C"))	
				command("CMD /C " + command);
			else
				throw new RuntimeException(e);
		}
		if(err)
			throw new OSExecute22Exception("Errors executing " + command);
		return ls;
	}	
}

class OSExecute22Exception extends RuntimeException{
	public OSExecute22Exception (String why) {super(why);}
}
