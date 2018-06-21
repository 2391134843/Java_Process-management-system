package Test2018;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Scanner;

public class Main {
    //用于获取进程id
    public static long PROCESS_ID;
    //设置判断进程是否活着的进程
    static boolean isProcessLive = false;
    public static long threadID ;
    public static String threadName ;
    public static long threadPriority ;
    public static Thread.State threadState;

    public static void main(String[] args) {
        System.out.println("*****************************************************");
        System.out.println("*                                                   *");
        System.out.println("*                进程演示系统                        *");
        System.out.println("*                              作者：李林育          *");
        System.out.println("*****************************************************");
        System.out.println("*                                                   *");
        System.out.println("*   1.创建一个线程    2.查看正在运行的进程           *");
        System.out.println("*   3.换出某个进程    4.杀死运行的进程               *");
        System.out.println("*   5.进程之间的通信  6.退出系统                     *");
        System.out.println("*                                                   *");
        System.out.println("*****************************************************");
        System.out.println();
        System.out.println("请选择：（1~6）:");
        Scanner scanner = new Scanner(System.in);
        int judge = scanner.nextInt();
        while (judge != 1 && judge != 2 && judge != 3 && judge != 4 && judge != 5 && judge != 6) {
            System.out.println("请输入1~6: ");
            judge = scanner.nextInt();
        }
        while (true) {
            //如果当
            if (judge == 1) {
                Run1 run1 = new Run1();
                Thread thread = new Thread(run1);
                System.out.println("请输入进程名称：");
                String name = scanner.next();
                thread.setName(name);
                threadID = thread.getId();
                threadName =  thread.getName();
                threadPriority = thread.getId();
                threadState = thread.getState();
                System.out.print("成功创建一个id为：" + thread.getId() + "\t");
                System.out.print("进程的名字为：" + thread.getName()+"\t");
                System.out.print("进程的优先级为：" + thread.getPriority()+"\t");
                System.out.println("进程的状态为：" + thread.getState()+" 的进程");
                PROCESS_ID = thread.getId();
                isProcessLive = true;

            } else if (judge == 2) {
                //2 查看正在运行的进程
                VBSUtils.ViewProcess();
            } else if (judge == 3) {
                //3 唤出某个进程
                Thread thread2 = new Thread();
                System.out.println("请输入你要换出进程的id :");
                long id = scanner.nextLong();
                if (id == PROCESS_ID) {
                    Thread thread1 = new Thread();
                    thread1.setName(threadName);
                    try {
                        Runtime.getRuntime().exec("taskkill /F /PID " + PROCESS_ID);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("切换的新进程信息为：");
                    System.out.print("进程的id为：" + thread1.getId() + "\t");
                    System.out.print("进程的名字为：" + thread1.getName()+"\t");
                    System.out.print("进程的优先级为：" + thread1.getPriority()+"\t");
                    System.out.println("进程的状态为：" + thread1.getState()+" 的进程");
                }else {
                    System.out.println("您输入的id号进程不存在");
                }

            } else if (judge == 4) {
                //4 杀死某个正在运行的进程
                try {
                    /**
                     * 调用cmd指令结束进程的生命
                     */
                    if (isProcessLive) {
                        Runtime.getRuntime().exec("taskkill /F /PID " + PROCESS_ID);
                        System.out.println("成功杀ID为：" + PROCESS_ID + " 的进程");
                        isProcessLive = false;
                    } else {
                        System.out.println("线程已经被终止或还没有创建，再不要继续终止，或者先创建一个线程");
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (judge == 5) {
                //5 进程之间的通信
                try{

                    PipedReader pipedReader = new PipedReader();
                    PipedWriter pipedWriter = new PipedWriter();

                    //inputStream.connect(outputStream);
                    pipedWriter.connect(pipedReader);   //将pipedWriter和pipeRead利用connect相连

                    System.out.println("进程通信中......");
                    //启动写线程
                    ThreadWrite threadWrite = new ThreadWrite(pipedWriter);
                    threadWrite.start();

                    Thread.sleep(2000);

                    //启动读线程
                    ThreadRead threadRead = new ThreadRead(pipedReader);
                    threadRead.start();
                    Thread.sleep(2000);

                }catch (IOException e){
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            } else if (judge == 6) {
                //结束进程
                System.out.println("系统演示结束 ！");
                break;
            }
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("请继续输入你想要的功能（1~6）");
            judge = scanner.nextInt();
        }
        //不关闭的话，idea的程序显示还没有结束运行程序
        scanner.close();
    }
}
