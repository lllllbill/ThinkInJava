package P18;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class E17 {

	public static void main(String[] args) {
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		char[] charList = "abcdefghijklmnopqistuvwxyz".toCharArray();
		for(char c:charList){
			map.put(c, 0);
		}
		try{
			String read = TextFile.read("d:\\text.txt");
			char[] readList = read.toCharArray();
			int num = 0;
			for(char c:readList){
				if(map.containsKey(c)){
					num = map.get(c)+1;
					map.replace(c, num);
				}
			}
			for(Entry<Character,Integer> entry:map.entrySet()){
				System.out.println(entry.getKey()+" number: "+entry.getValue());
			}
		}catch(IOException e){
			System.out.print("¶ÁÈ¡ÎÄ¼þ´íÎó");
			e.printStackTrace();
		}
	}

}
