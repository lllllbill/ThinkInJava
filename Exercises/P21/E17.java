package P21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



//Exercise 创建一个辐射计数器，可以拥有任意数量的传感器
public class E17 {
	
	public static void main(String[] args) throws InterruptedException{
		Count count = new Count();
		ExecutorService exec = Executors.newCachedThreadPool();
		int num = 10;
		for(int i= 0 ;i<num;i++){
			exec.submit(new Transducer(count,i));
		}
		TimeUnit.SECONDS.sleep(3);
		Transducer.setCancle();
		exec.shutdown();
	}
	
}

//辐射计数器，用于计数，计数共享
class Count{
	private  int count = 0;
	private Random random = new Random(47);
	public synchronized int add(){
		int temp = count;
		if(random.nextBoolean()){
			Thread.yield();
		}
		return (count = ++temp);
	}
	
	public synchronized int getCount(){
		return this.count;
	}
}

class Transducer implements Runnable{
	private Count masterCount = null;
	private int count = 0;
	private int id = 0;
	private static volatile boolean isCancle = false;
	public static void setCancle(){ isCancle = true;}
	Transducer(Count count,int id){
		this.masterCount = count;
		this.id = id;
	}
	@Override
	public void run(){
		while(!isCancle){
			//需要synchronized 
			//一个对象启动多个线程？
			//会存在一个对象在多个线程中启动
			synchronized(this){
				this.count +=1;
			}
			System.out.println(this);
			try{
				TimeUnit.MILLISECONDS.sleep(100);
			}catch(InterruptedException e){
				System.out.println("sleep interrupted");
			}
			
		}
		System.out.println("Stopping " +this );
	}
	
	public String toString(){
		return "Transducer "+ id +" :num " +count +" masterCount nums:" + masterCount.add() ;
	}
}
