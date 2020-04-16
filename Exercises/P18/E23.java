package P18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class E23 {
	private static final int BSIZE=1024;
	public static void main(String[] args){
		try {
			FileChannel fc = new FileInputStream("d:\\text.txt").getChannel();
			ByteBuffer buff= ByteBuffer.allocate(BSIZE);
			String encoding = System.getProperty("file.encoding");
			System.out.println("Decoded using "+encoding);
			while(fc.read(buff)!=-1){
				buff.flip();
				System.out.print(Charset.forName(encoding).decode(buff));
				buff.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
