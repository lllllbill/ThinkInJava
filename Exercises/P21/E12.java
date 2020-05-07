package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E12 implements Runnable{
	private int i = 0;
	public synchronized int getValue() { return i; }
	private synchronized void evenIncrement() { i++; i++; } 
	public void run() {
		while(true) {
			evenIncrement();
		}
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		E12 at = new E12();
		exec.execute(at);
		while(true) {
			int val = at.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}}
