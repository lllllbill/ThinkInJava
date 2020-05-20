package P21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count_E19 {
	private int count = 0;
	private Random rand = new Random(47);
	// Remove the synchronized keyword to see counting fail:
	public synchronized int increment() {
		int temp = count;
		if(rand.nextBoolean()) // Yield half the time
			Thread.yield();
		return (count = ++temp);
	}
	public synchronized int value() { return count; } 
}

class Entrance implements Runnable {
	private static Count_E19 count = new Count_E19();
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private int number = 0;
	// Doesn't need synchronization to read:
	private final int id;
	private static volatile boolean canceled = false;
	// Atomic operation on a volatile field:
	public static void cancel() { canceled = true; }
	public Entrance(int id) {
		this.id = id;
		// Keep this task in a list. Also prevents
		// garbage collection of dead tasks:
		entrances.add(this);
	}
	public void run() {
		while(!canceled) {
			synchronized(this) { 
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch(InterruptedException e) {
				System.out.println("sleep interrupted");
				break; 
			}
		}
		System.out.println("Stopping " + this);
	}
	public synchronized int getValue() { return number; }
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	} 
	public static int getTotalCount() {
		return count.value();
	}
	public static int sumEntrances() {
		int sum = 0;
		for(Entrance entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

public class E19 {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++)
			exec.execute(new Entrance(i));
		// Run for a while, then stop and collect the data:
		TimeUnit.SECONDS.sleep(4);
		// Sent interrupts (without canceling):
		exec.shutdownNow();
		if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
			System.out.println("Some tasks were not terminated");
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}
