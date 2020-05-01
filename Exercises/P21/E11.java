package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class E11 implements Runnable{
	private String[] music = "我和你吻别，在无人的街道,一起呼叫，没有烦恼，烦恼什么烦恼~".split("");
	private  volatile int data2;
	
	public void unstabitilyOut(){
		while(data2<music.length){
			System.out.print(music[data2]);
			Thread.yield();
			++data2;
		}
	}
	
	public synchronized void synchronizedOut(){
		while(data2<music.length){
			System.out.print(music[data2]);
			Thread.yield();
			++data2;
		}
	}
	
	@Override
	public void run() {
		unstabitilyOut();
		//synchronizedOut();
	}
	
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		E11 e11 =new E11();
		for(int i=0;i<10;i++){
			exec.execute(e11);
		}
	}

}

	
