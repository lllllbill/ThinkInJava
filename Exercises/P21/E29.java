package P21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Toast {
	public enum Status { DRY, JELLIED, PEANUTBUTTERED }
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) { id = idn; }
	public void jelly() { status = Status.JELLIED; }
	public void peanutButter() { status = Status.PEANUTBUTTERED; }
	public Status getStatus() { return status; }
	public int getId() { return id; }
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}
	
class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random();
	public Toaster(ToastQueue tq) { toastQueue = tq; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(
					100 + rand.nextInt(500));
				// Make toast
				Toast t = new Toast(count++);
				System.out.println(t);
				// Insert into queue
				toastQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster off");
	}
}

// Apply peanut butter to taast
class PeanutButterer implements Runnable {
	private ToastQueue dryQueue, peanutButteredQueue;
	public PeanutButterer(ToastQueue dry, ToastQueue peanutButtered) {
		dryQueue = dry;
		peanutButteredQueue = peanutButtered;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available
				Toast t = dryQueue.take();
				t.peanutButter();
				System.out.println(t);
				peanutButteredQueue.put(t);
			}
		} catch(InterruptedException e) {
		  	System.out.println("PeanutButterer interrupted");
		}
		System.out.println("PeanutButterer off");
	}
}

// Apply jelly to toast
class Jellyer implements Runnable {
	private ToastQueue dryQueue, jelliedQueue;
	public Jellyer(ToastQueue dry, ToastQueue jellied ) {
		dryQueue = dry;
		jelliedQueue = jellied;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available
				Toast t = dryQueue.take();
				t.jelly();
				System.out.println(t);
				jelliedQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Jellyer interrupted");
		}
		System.out.println("Jellyer off");
	}
}

class Sandwich {
	private Toast top, bottom;
	private final int id;
	public Sandwich(Toast top, Toast bottom, int id) {
		this.top = top;
		this.bottom = bottom;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public Toast getTop() { return top; }
	public Toast getBottom() { return bottom; }
	public String toString() {
		return "Sandwich " + id + ": top: " + top + " and bottom: " + bottom;
	}
}

class SandwichQueue extends LinkedBlockingQueue<Sandwich> {}

class SandwichMaker implements Runnable {
	private int count = 0;
	private ToastQueue jelliedQueue, peanutButteredQueue;
	private SandwichQueue sandwichQueue;
	public SandwichMaker(ToastQueue jellied, ToastQueue peanutButtered, SandwichQueue sq) {
		jelliedQueue = jellied;
		peanutButteredQueue = peanutButtered;
		sandwichQueue = sq;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Sandwich s = new Sandwich(
					jelliedQueue.take(), peanutButteredQueue.take(), count++);
				System.out.println(s);
				sandwichQueue.put(s);
			}
		} catch(InterruptedException e) {
			System.out.println("SandwichMaker interrupted");
		}
		System.out.println("Sandwich maker off");
	}
}

// Consume the toast
class SandwichEater implements Runnable {
	private SandwichQueue sandwichQueue;
	private int counter = 0;
	public SandwichEater(SandwichQueue sq) {
		sandwichQueue = sq;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available
				Sandwich s = sandwichQueue.take();
				// Verify that the sandwich is coming in order,
				// and that all are jellied and peanutbettered:
				if(s.getId() != counter++ || 
					s.getTop().getStatus() != Toast.Status.JELLIED || 
					s.getBottom().getStatus() != Toast.Status.PEANUTBUTTERED) {
						System.out.println(">>>> Error: " + s);
						System.exit(1);
				} else
					System.out.println("NumNum! " + s);
			} 
		} catch(InterruptedException e) {
				System.out.println("SandwichEater interruped");
		}
		System.out.println("SandwichEater off");
	}
}

public class E29 {
	public static void main(String[] args) throws Exception {
		ToastQueue dryQueue = new ToastQueue(),
			jelliedQueue = new ToastQueue(),
			peanutButteredQueue = new ToastQueue();
		SandwichQueue sandwichQueue = new SandwichQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Jellyer(dryQueue, jelliedQueue));
		exec.execute(new PeanutButterer(dryQueue, peanutButteredQueue));
		exec.execute(new SandwichMaker(
			jelliedQueue, peanutButteredQueue, sandwichQueue));
		exec.execute(new SandwichEater(sandwichQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
