package P21;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class E5 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		for(int i = 0; i < 20; i++)
			results.add(exec.submit(new Thread3(i)));
		for(Future<Integer> fs : results)
			try {
				System.out.println(fs.get());
			} catch(InterruptedException e) {
				System.out.println(e);
				return;
			} catch(ExecutionException e) {
				System.out.println(e);	
			} finally {
				exec.shutdown();
			}				
	}
}

class Thread3 implements Callable{
	
	private int n = 0;
	public Thread3(int n) {
		this.n = n;
	}
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
	
}