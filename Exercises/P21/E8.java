package P21;

import java.util.concurrent.TimeUnit;

public class E8 {

	public static void main(String[] args) throws InterruptedException {
		try { Thread t = new Thread(new Runnable(){
					private Thread[] t = new Thread[10];
					@Override
					public void run() {
						while(true){
							try {
								TimeUnit.SECONDS.sleep(1);
								System.out.println(System.currentTimeMillis());
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
				t.setDaemon(true);
				t.start();				
			System.out.println("Waiting for Thread");	
			TimeUnit.SECONDS.sleep(10);
		} finally {
			System.out.println("Finally out of main");
		}
	}

}
