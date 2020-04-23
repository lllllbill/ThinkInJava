package P21;

import java.util.ArrayList;
import java.util.List;

public class E2 {
	public static void main(String[] args) {
		for(int i =0;i<20;i++){
			new Thread(new thread2(i)).start();
		}
	}
}

class thread2 implements Runnable{
	private int n;
	public thread2(int n){
		this.n=n;
	}
	@Override
	public void run() {
		List<Integer> list = new ArrayList(n);
		for(int i=0;i<n;i++){
			if(i==0){
				list.add(1);
			}else if(i==1){
				list.add(1);
			}else{
				list.add(list.get(i-1)+list.get(i-2)) ;
			}
		}
		System.out.println(list);
	}
	
}