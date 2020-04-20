package P18;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class E25 {
	private static abstract class Tester{
		private String name;
		public Tester(String name){
			this.name = name;
		}
		public void runtest(){
			long start = System.nanoTime();
			if(test()){
				start  = System.nanoTime() - start;
				System.out.println(name + ":"+start);
			}else{
				System.out.println(name + " wrong");
			}
			
		}
		
		public abstract boolean test();
	}
	
	public static void main(String[] args){
		int BSIZE = 1024;
		new Tester("allocate"){
			@Override
			public boolean test() {
				ByteBuffer bb = ByteBuffer.allocate(BSIZE);
				try {
					FileChannel fc = new RandomAccessFile(new File(""),"rw").getChannel();
					while(fc.read(bb)>0){
						bb.flip();
					}
					return true;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			
		}.runtest();
		
		new Tester("allocateDirect"){
			@Override
			public boolean test() {
				ByteBuffer bb = ByteBuffer.allocateDirect(BSIZE);
				try {
					FileChannel fc = new RandomAccessFile(new File(""),"rw").getChannel();
					while(fc.read(bb)>0){
						bb.flip();
					}
					return true;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			
		}.runtest();
	}
}
