package P18;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class E21 {

	public static void main(String[] args) {
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File("d:\\text.txt")));
			System.setIn(input);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s;
			try {
				while((s=br.readLine())!=null){
					System.out.println(s.toUpperCase());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					input.close();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		}
	}

}
