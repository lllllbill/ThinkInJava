package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E3 {

	public static void main(String[] args) {
		method1();
		method2();
		method3();
	}
	
	public static void method1(){
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new thread1(i));
		}
		exec.shutdown();
	}
	
	public static void method2(){
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for(int i=0;i<5;i++){
			exec.execute(new thread1(i));
		}
		exec.shutdown();
	}
	
	public static void method3(){
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for(int i=0;i<5;i++){
			exec.execute(new thread1(i));
		}
		exec.shutdown();
	}
}
