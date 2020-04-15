package P18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public final class Directory {
	public static File[] local(File dir,final String regex){
		return dir.listFiles(new FilenameFilter(){
			private Pattern pattern = Pattern.compile(regex);
			public boolean accept(File dir,String name){
				return pattern.matcher(new File(name).getName()).matches();
			}
		});
	}
	
	public static File[] local(String dir,final String regex){
		return local(new File(dir),regex);
	}
}
