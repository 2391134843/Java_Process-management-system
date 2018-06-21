package TestRunnableThread;

class Person implements Runnable {
    private int count=5;
    String name;


    public synchronized void  run() {
        for (int i=0;i<10;i++) {
            if (this.count >0) {
                count--;
                System.out.println(Thread.currentThread().getName()+ "正在买票，" + "剩余：" + count);
            }
        }
    }
}
public class RunnableTest {

    public static void main(String[] args) {
        Person person = new Person();
        Thread thread1 = new Thread(person,"一号柜台");
        Thread thread2 = new Thread(person,"二号柜台");
        Thread thread3 = new Thread(person,"三号柜台");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
