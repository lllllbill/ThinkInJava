package P21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class E6 {

	public static void main(String[] args) {
		ExecutorService exe = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			exe.execute(new Runnable(){
				Random rand = new Random();
				@Override
				public void run() {
					try {
						int t = 1000 * rand.nextInt(10);
						TimeUnit.MILLISECONDS.sleep(t);
						System.out.println("Slept " + t/1000 + "s");
						return;	
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
			
	}

}
