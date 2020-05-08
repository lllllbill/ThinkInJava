package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class E15 {
	Object o1 = new Object();
	public  void method1(){
		synchronized(o1){
			while(true){
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("method1");
				Thread.yield();
			}	
		}
	}
	Object o2 = new Object();
	public  synchronized void method2(){
		synchronized(o2){
			while(true){
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("method2");
				Thread.yield();
			}	
		}
	}
	Object o3 = new Object();
	public  synchronized void method3(){
		synchronized(o3){
			while(true){
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("method3");
				Thread.yield();
			}	
		}
	}

	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService es =Executors.newCachedThreadPool();
		E15 e15 = new E15();
		es.execute(new E15_1(e15));
		es.execute(new E15_2(e15));
		es.execute(new E15_3(e15));
		TimeUnit.SECONDS.sleep(1);
		es.shutdown();
	}

	
}

class E15_1  implements Runnable{
	private E15 e15;
	E15_1(E15 e15){
		this.e15 = e15;
	}
	@Override
	public void run() {
		e15.method1();
	}
}

class E15_2  implements Runnable{
	private E15 e15;
	E15_2(E15 e15){
		this.e15 = e15;
	}
	@Override
	public void run() {
		e15.method2();
	}
}

class E15_3  implements Runnable{
	private E15 e15;
	E15_3(E15 e15){
		this.e15 = e15;
	}
	@Override
	public void run() {
		e15.method3();
	}
}
