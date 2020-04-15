package P18;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class E19 {

	public static void main(String[] args) {
		Map<Byte, Integer> map = new HashMap<Byte,Integer>();
		Set<Byte> byteSet = new TreeSet<Byte>();
		try {
			byte[] ba = BinaryFile.read("d:\\binary.txt");
			for(Byte b : ba) byteSet.add(b);
			for(Byte b : byteSet) {
				int count = 0;
				for(Byte d : ba) {
					if(d.equals(b)) count++;
				}
				map.put(b, count);
			}
			System.out.println(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
