package test2017;

public class MutiThread {
    //为什么当循坏很大的时候线程才老老实实的从上到下？当循坏较小的时候就很快的先调用主线程？
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        // for (int i=0;i<1;i++) {
        //     for (int b=0;b<10;b++) {
        //         for (int c=0;c<10;c++) {
        //             for (int k=0;k<1;k++) {
        //                 int a=0;
        //                 a++;
        //             }
        //         }
        //     }
        // }

        // for (int i=0;i<100;i++) {
        //     for (int b=0;b<1000;b++) {
        //         for (int c=0;c<100;c++) {
        //             for (int k=0;k<100;k++) {
        //                 int a=0;
        //                 a++;
        //             }
        //         }
        //     }
        // }
        // Thread.sleep(2);
        System.out.println("Main: Hello girl ！");


    }
}
class MyThread extends Thread{
    public void run() {
        System.out.println("子线程开始被启动.......");
    }
}
