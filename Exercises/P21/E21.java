package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class E21 {
	public static void main(String[] args){
		ExecutorService exe =Executors.newCachedThreadPool();
		Runnable1 r1 = new Runnable1();
		Runnable2 r2 = new Runnable2(r1);
		exe.execute(r1);
		exe.equals(r2);
		exe.shutdown();
	}
}

class Runnable1 implements Runnable{
	@Override
	public void run() {
		method();
	}
	
	public synchronized void  method(){
		try {
			System.out.println("in runnable1");
			wait();
			System.out.println("Runnable1");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Runnable2 implements Runnable{
	private Runnable1 r1;
	Runnable2(Runnable1 r1){
		this.r1=r1;
	}
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1000);
			r1.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}