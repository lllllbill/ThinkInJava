package P18;

import java.io.File;
import java.io.IOException;

public final class E20 {
	public static void main(){
		File[] fileList = Directory.local(".", ".*[Zz].*\\.class");
		for(File file:fileList){
			byte[] ba;
			try {
				ba = BinaryFile.read(file);
				for(int i = 0; i < 4; i++)
					System.out.println(Integer.toHexString(ba[i] & 0xff).toUpperCase());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}


