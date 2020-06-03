package P21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class E25 {

	public static void main(String[] args) {
		new Restaurant();
	}

}
class Chef2 implements Runnable{
	private Restaurant2 restaurant;
	public Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
	int count = 0;
	public Chef2(Restaurant2 restaurant ){this.restaurant = restaurant;}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			lock.lock();
			try{
				//等待被唤醒，然后获取肉
				while(restaurant.meal!=null){
					condition.await();
				}
			} catch (InterruptedException e) {
				System.out.println("Chef2 Interrupted");
			}finally{
				lock.unlock();
			}
			//一天只烧10块肉
			if(++count>10){
				System.out.println("不烧了，打烊");
				restaurant.exec.shutdown();
			}
			System.out.println("烧肉了");
			//通知服务员送餐
			restaurant.waitPerson.lock.lock();
			try{
				restaurant.meal =new Meal(count);
				restaurant.waitPerson.condition.signalAll();
			}finally{
				restaurant.waitPerson.lock.unlock();
			}
		}
	}
}

class WaitPerson2 implements Runnable{
	public Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
	private Restaurant2 restaurant;
	public WaitPerson2(Restaurant2 restaurant ){this.restaurant = restaurant;}
	@Override
	public void run(){
		while(!Thread.interrupted()){
			lock.lock();
			try{
				while(restaurant.meal==null){
					condition.await();
				}
			} catch (InterruptedException e) {
				System.out.println("WaitPerson2 Interrupted");
			}finally{
				lock.unlock();
			}
			System.out.println("送餐了"+restaurant.meal);
			//通知厨师
			restaurant.chef.lock.lock();
			try{
				restaurant.chef.condition.signalAll();
			}finally{
				restaurant.chef.lock.unlock();
			}
			restaurant.busBoy.lock.lock();
			try{
				restaurant.busBoy.condition.signalAll();
			}finally{
				restaurant.busBoy.lock.unlock();
			}
		}
	}
}

class BusBoy2 implements Runnable{
	public Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
	private Restaurant2 restaurant;
	public BusBoy2(Restaurant2 restaurant ){this.restaurant = restaurant;}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			lock.lock();
			try{
				condition.wait();
			} catch (InterruptedException e) {
				System.out.println("Chef2 Interrupted");
			}finally{
				lock.unlock();
			}
			System.out.println("clean");
		}
	}
}

class Restaurant2{
	Meal meal;
	Chef2 chef =new Chef2(this);
	WaitPerson2 waitPerson = new WaitPerson2(this);
	BusBoy2 busBoy  =new BusBoy2(this);
	ExecutorService exec = Executors.newCachedThreadPool();
	public Restaurant2(){
		exec.execute(chef);
		exec.execute(waitPerson);
		exec.execute(busBoy);
	}
}