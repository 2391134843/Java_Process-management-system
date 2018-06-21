package test2017;

import static java.lang.Thread.sleep;

class Mythread0 extends Thread{
    public void run() {
        int i = 0;
        System.out.println("继承Thread的接口线程，正在占有运行处理机制...." + i);
        while (i < 10) {
            try {
                sleep(1000);
                i++;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

class RunnableDemo implements Runnable {
    int i = 0;
    public void run() {
        while (i < 10) {
            System.out.println("实现Runnable类继承的线程，正在占有处理机运行..."+i);
            try {
                sleep(1000);
                i++;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
public class MyThread1 {
    public static void main(String[] args) {
        Mythread0 mythread0 = new Mythread0();
        Thread thread1 = new Thread(new RunnableDemo());
        mythread0.start();
        thread1.start();
        //主线程自己也执行的其他语句
        System.out.println("Main:Thread");
    }
}