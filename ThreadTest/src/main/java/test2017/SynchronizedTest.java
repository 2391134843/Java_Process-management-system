package test2017;

public class SynchronizedTest implements Runnable{
    Timer2 timer = new Timer2();
    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread thread1 = new Thread(synchronizedTest);
        Thread thread2 = new Thread(synchronizedTest);
        thread1.setName("t1");
        thread2.setName("t2");
        thread1.start();
        thread2.start();
    }

    public void run() {
        timer.add(Thread.currentThread().getName());
    }
}
class Timer {
    private static int num=0;

    public /*synchronized*/ void add(String name) {
        num++;
        System.out.println(name + " 你是第 " + num + " 个使用线程的");
    }
}
