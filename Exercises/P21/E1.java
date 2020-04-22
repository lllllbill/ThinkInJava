package P21;

public class E1 {
	public static void main(String[] args) {
		for(int i =0;i<100;i++){
			new Thread(new thread1(i)).start();
		}
	}

}

class thread1 implements Runnable{
	private int id;
	
	public thread1(int id){
		this.id=id;
	}
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			System.out.println(id+":"+i);
			Thread.yield();
		}
		
	}
	
}