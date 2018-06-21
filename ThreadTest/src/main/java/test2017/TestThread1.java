package test2017;

public class TestThread1 {
    public static void main(String[] args) {
        Runnable1 r1 = new Runnable1();
        Thread th = new Thread(r1);
        for (int i=0;i<=100;i++) {
            System.out.println("主程序————————————"+i);
        }
        th.start();
//如果不用star直接调用run方法的话将会向原来调用函数那样，等函数执行完之后才能返回到主函数里面来
//        th.run();

    }
}

class Runnable1 implements Runnable{
    public void run() {
        for (int i=0;i<=100;i++) {
            System.out.println("调用多线程"+i);
        }
    }
}

