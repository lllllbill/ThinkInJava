package P21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class E30 {
	public static void main(String[] args) throws Exception {
		LinkedBlockingQueue<Character> lbq = new LinkedBlockingQueue<Character>();
		Sender sender = new Sender(lbq);
		Receiver receiver = new Receiver(lbq);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}

class Sender implements Runnable{
	private Random rand = new Random(47);
	private LinkedBlockingQueue<Character> queue;
	public Sender(LinkedBlockingQueue<Character> lbq) {
		queue = lbq;
	}
	public void run() {
		try {
			while(true)
				for(char c = 'A'; c <= 'z'; c++) {
					queue.put(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
		} catch(InterruptedException e) {
			System.out.print(e + " Sender sleep interrupted");
		}
	}
}

class Receiver implements Runnable {
	private LinkedBlockingQueue<Character> queue;
	public Receiver(LinkedBlockingQueue<Character> lbq) {
		queue = lbq;
	}
	public void run() {
		 try {
			while(true) {
				System.out.print("Read: " + (char)queue.take() + ", ");
			}
		 } catch(InterruptedException e) {
			 System.out.print(e + " Receiver read exception");
		}
	}
}
