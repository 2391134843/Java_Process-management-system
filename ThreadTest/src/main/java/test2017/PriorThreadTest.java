package test2017;

import static java.lang.Thread.sleep;

class Thread0 implements Runnable {
    public void run() {
        for (int j=0;j<100;j++) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
class Thread1 implements Runnable{
    public void run(){
        for (int i=0;i<=200;i++){
            try{
                sleep(100);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println("1111111111111111111111");
        }
    }
}
public class PriorThreadTest {
    public static void main(String[] args) {
        Thread0 r = new Thread0();
        Thread1 r1=new Thread1();
        Thread thread = new Thread(r);
        Thread thread1 = new Thread(r);
        thread.setName("000");
        thread1.setName("111");
//        thread = new Thread(new test2017.Thread0());
        thread.setPriority(Thread.NORM_PRIORITY+5);
        thread.start();
        thread1.start();
    }
}


