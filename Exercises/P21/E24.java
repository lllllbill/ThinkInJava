package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E24 {
	public static void main(String[] args){
		new Restaurant();
	}
}

class Chef implements Runnable{
	private Restaurant restaurant;
	int count = 0;
	public Chef(Restaurant restaurant ){this.restaurant = restaurant;}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized(this){
					//�ȴ������ѣ�Ȼ���ȡ��
					while(restaurant.meal!=null){
						wait();
					}
				}
				//һ��ֻ��10����
				if(++count>10){
					System.out.println("�����ˣ�����");
					restaurant.exec.shutdownNow();
				}
				System.out.println("������");
				//֪ͨ����Ա�Ͳ�
				synchronized(restaurant.waitPerson){
					restaurant.meal =new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				
			}
		}catch(InterruptedException e){
			System.out.println("Chife Interrupted");
		}
	}
}

class WaitPerson implements Runnable{
	private Restaurant restaurant;
	public WaitPerson(Restaurant restaurant ){this.restaurant = restaurant;}
	@Override
	public void run(){
		try{
			while(!Thread.interrupted()){
				synchronized(this){
					//�ȴ������ѣ�Ȼ���ȡ��
					while(restaurant.meal==null){
						wait();
					}
				}
				System.out.println("WaitPerson got"+restaurant.meal);
				//֪ͨ��ʦ����
				synchronized(restaurant.chef){
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
				//֪ͨBusBoy��ɨ
				synchronized(restaurant.busBoy){
					restaurant.busBoy.notifyAll();
				}
			}
		}catch(InterruptedException e){
			System.out.println("WaitPerson Interrupted");
		}
	}
}

class BusBoy implements Runnable{
	private Restaurant restaurant;
	public BusBoy(Restaurant restaurant ){this.restaurant = restaurant;}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized(this){
					//�ȴ������ѣ�Ȼ��ȥ��ɨ
					wait();
				}
				System.out.println("clean");
			}
		}catch(InterruptedException e){
			System.out.println("BusBoy Interrupted");
		}
	}
	
}

class Meal{
	private final int orderNum;
	public Meal(int orderNum){this.orderNum = orderNum;}
	public String toString(){return "Meal"+orderNum;}
}

class Restaurant{
	Meal meal;
	Chef chef =new Chef(this);
	WaitPerson waitPerson = new WaitPerson(this);
	BusBoy busBoy  =new BusBoy(this);
	ExecutorService exec = Executors.newCachedThreadPool();
	public Restaurant(){
		exec.execute(chef);
		exec.execute(waitPerson);
		exec.execute(busBoy);
	}
}