package Test2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;
public class VBSUtils {
    public VBSUtils() {

    }

    /**
     * 通过调用VBScript查看当前正在执行的进程数，Linux下不一定适用..没试过
     * @return
     */
    public static List<String> listRunningProcesses() {
        List<String> processList = new ArrayList<String>();
        try {
            File file = File.createTempFile("realhowto",".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                    + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
                    + "Set service = locator.ConnectServer()\n"
                    + "Set processes = service.ExecQuery _\n"
                    + " (\"select name from Win32_Process\")\n"
                    + "For Each process in processes\n"
                    + "wscript.echo process.Name \n"
                    + "Next\n"
                    + "Set WSHShell = Nothing\n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return processList;
    }

    public static void main(String[] args){
      ViewProcess();
    }

    public static void ViewProcess() {
        List<String> processes = VBSUtils.listRunningProcesses();
        String result = "";

        Iterator<String> it = processes.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            result += i+" "+it.next() +",";
//            i++;
            if ((i%10)==0) {
                result += "\n\n";
//                i = 0;
            }
        }
        System.out.println("正在运行的进程有 "+i+" 个 "+": \n " + result);
        //调用GUI界面输出函数：
//        msgBox("Running processes : " + result);
    }

    /**
     * 用GUI打印现在正在运行的进程
     */
    public static void msgBox(String msg) {
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
                null, msg, "VBSUtils", javax.swing.JOptionPane.DEFAULT_OPTION);
    }
}
