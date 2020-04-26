package P21;

import java.util.concurrent.TimeUnit;

public class E7 {

	public static void main(String[] args) throws Exception{
		Thread d = new Thread(new Daemon());
		//后台线程创建的线程也是后台线程
		d.setDaemon(true);
		d.start();
		System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
		//非后台线程结束（main）后台线程也会全部结束。
		TimeUnit.SECONDS.sleep(1);
	}

}

class Daemon implements Runnable {
	private Thread[] t = new Thread[10];
	public void run() {
		for(int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpawn " + i + " started, ");
		}
		for(int i = 0; i < t.length; i++)
			System.out.println("t[" + i + "].isDaemon() = " + 
				t[i].isDaemon() + ", ");
		while(true)
			Thread.yield();
	}
}

class DaemonSpawn implements Runnable {
	public void run() {
		while(true)
			Thread.yield();
	}
}