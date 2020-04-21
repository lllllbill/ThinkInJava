package P18;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class E29 implements Externalizable{
	private int i;
	private String s; // No initialization
	public E29() {
		System.out.println("E29 Contstructor");
		// s, i not initialized
	}
	public E29(String x, int a) {
		System.out.println("E29(String x, int a)");
		s = x;
		i = a;
		// s & i initialized only in non-default contructor
	}
	public String toString() { return s + i; }
	public void writeExternal(ObjectOutput out) 
	throws IOException {
		System.out.println("E29.writeExternal");
		// You must do this:
		// (or else default values will be used to
		// initialize fields)
		// out.writeObject(s);
		// out.writeInt(i);
		// or out.writeObject(i); 
	}
	public void readExternal(ObjectInput in)
	throws IOException, ClassNotFoundException {
		System.out.println("E29.readExternal");
		// You must do this:
		// (or else dafault constructor will automatically
		// initialize fields to default values)
		// s = (String)in.readObject();
		// i = in.readInt();
		// or i = (Integer)in.readObject(); 
	}
	public static void main(String[] args)
	throws IOException, ClassNotFoundException {
		System.out.println("Constructing objects:");
		E29 b29 = new E29("A String ", 47);
		System.out.println(b29);
		ObjectOutputStream o = new ObjectOutputStream(
			new FileOutputStream("E29.out"));
		System.out.println("Saving object:");
		o.writeObject(b29);
		o.close();
		// Now get it back:
		ObjectInputStream in = new ObjectInputStream(
			new FileInputStream("E29.out"));
		System.out.println("Recovering b29:");
		b29 = (E29)in.readObject();
		System.out.println(b29);
	}
}
