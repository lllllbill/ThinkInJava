package P18;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

public class E24 {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		DoubleBuffer db = bb.asDoubleBuffer();
		db.put(new double[]{22.11,33.22,44.55});
		db.flip();
		while(db.hasRemaining()){
			System.out.println(db.get());
		}
	}

}
