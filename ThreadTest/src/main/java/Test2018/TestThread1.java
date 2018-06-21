package Test2018;

import java.io.*;

public class TestThread1 {

    /**
     * 杀死进程
     */
    public static void killProcess() {

    }
}

class Run1 implements Runnable {
    public void run() {
//            System.out.println("成功创建一个id为："+""+"线程");
    }
}

/**
 * 写入进程信息的类
 */
class ThreadWrite extends Thread{
    private PipedWriter out;

    public ThreadWrite(PipedWriter out){
        super();
        this.out = out;
    }
    @Override
    public void run(){
        try{
            System.out.println("现在正在向进程写入信息.........");
            Thread.sleep(1000);
            System.out.println("写入的信息为：");
            for(int i=0;i<10;i++){
                String outData = " "+(i+2);
                out.write(outData);
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


/**
 * 读取进程信息的类
 */
class ThreadRead extends Thread{
    private PipedReader input;

    public ThreadRead(PipedReader input){
        super();
        this.input = input;
    }

    @Override
    public void run(){

        try{
            System.out.println("现在正在读出进程的信息......");
            Thread.sleep(1000);
            System.out.println("进程携带的信息为：");
            char[] byteArray = new char[100];
            int readLength = input.read(byteArray);
            while (readLength!=-1){
                String newData = new String(byteArray,0,readLength);
                System.out.print(newData);
                readLength = input.read(byteArray);
            }
            System.out.println();
            input.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

