package P21;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class E10 {

	public static void main(String[] args) {
		Thread10 t10 = new Thread10();
		try {
			for(int i=1;i<10;i++){
				System.out.println(t10.runTask(i).get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}finally{
			t10.exec.shutdown();
		}
	}

}

class Thread10 implements Callable{
	ExecutorService exec = Executors.newSingleThreadExecutor();
	private int n = 0;
	private int fib(int x) {
		if(x < 2) return 1;
		return fib(x - 2) + fib(x - 1);
	}
	public Integer call() {
		int result = 0;
		for(int i = 0; i < n; i++) 
			result += fib(i);		
		return (Integer)result;
	}
	public Future<Integer> runTask(int n){
		this.n=n;
		return exec.submit(this);	 
	}
}