package P18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class E27 implements Serializable{
	private static class E27_inner implements Serializable{
		private int uuid ;
		public E27_inner(int uuid){
			this.uuid = uuid;
		}
		public String toString(){
			return "E27_inner uuid :"+uuid;
		}
	}
	public String flag;
	public E27_inner inner;
	public E27(String  flag){
		this.inner = new E27_inner(1);
		this.flag = flag;
	}
	public String toString(){
		return "E27 uuid :"+flag +"\n"+inner.toString();
	}
	public static void main(String[] args){
		E27 c = new E27("a");
		System.out.println(" out "+c.toString());
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("E27.out"));
			oos.writeObject(c);
			oos.flush();
			oos.close();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E27.out"));
			c = (E27)ois.readObject();
			ois.close();
			System.out.println(" in "+c.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}	
