package P21;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class E14  implements Runnable {
	private static int timers = 0;
	private static int tasks = 0;
	public void run() {
		try {
			while(timers < 1000) { 
				++timers;
				//定时任务类，TimerTask(Runnable,time(毫秒))
				Timer t = new Timer();		
				t.schedule(new TimerTask() {
					public void run() {
						++tasks; 
						if(timers % 100 == 0)		
							System.out.println(timers + " timers did " 
								+ tasks + " tasks");
					}
				}, 10);
				try {
					TimeUnit.MILLISECONDS.sleep(30); // time to do task
				} catch(InterruptedException e) {
					System.out.println("Sleep interrupted");
				}
				t.cancel();
			}
		} finally {
			System.out.println("Done. " + timers + " timers completed " 
				+ tasks + " tasks");
		} 
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new E14());
		exec.shutdown();
	}
}
