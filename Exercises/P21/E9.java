package P21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class E9 implements Runnable {
	private int countDown = 5;
	private volatile double d; // No optimization
	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}
	public void run() {
		while(true) {
			for(int i = 0; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double)i;
				if(i % 1000 == 0)
					Thread.yield();
			} 
		System.out.println(this);
		if(--countDown == 0) return;
		}		
	}
	public static void main(String[] args) {
		ExecutorService exec = 
				Executors.newCachedThreadPool(new E9ThreadFactory());
			for(int i = 0; i < 5; i++)
				exec.execute(new E9());
			exec.execute(new E9());
			exec.shutdown();
	}

}

class E9ThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Random rand = new Random();
		Thread t = new Thread(r);
		int i = rand.nextInt(10);
		t.setPriority(i);
		return t;	
	}
	
}