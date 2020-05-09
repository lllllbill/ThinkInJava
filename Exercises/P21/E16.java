package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class E16 {
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void method1(){
		lock1.lock();
		try{
		while(true){
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.println("method1");
			
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock1.unlock();
		}
	}
	
	public void method2(){
		lock2.lock();
		try{
		while(true){
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.println("method2");
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock2.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService es =Executors.newCachedThreadPool();
		E16 e16 = new E16();
		es.execute(new E16_1(e16));
		es.execute(new E16_2(e16));
		TimeUnit.SECONDS.sleep(1);
		es.shutdown();
	}
}

class E16_1  implements Runnable{
	private E16 e16;
	E16_1(E16 e16){
		this.e16 = e16;
	}
	@Override
	public void run() {
		e16.method1();
	}
}

class E16_2  implements Runnable{
	private E16 e16;
	E16_2(E16 e16){
		this.e16 = e16;
	}
	@Override
	public void run() {
		e16.method2();
	}
}