package tools;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>
 * 日志类
 * </h2>
 *
 * <p>
 * 这个类用于将打印到控制台的内容记录在本类成员变量中, 在需要的时候对其进行输出
 * </p>
 *
 * @author Maxwell
 */
public class Log {

    /**
     * 通过字符串列表实现日志
     */
    private static final List<String> log = new ArrayList<>();

    /**
     * 基本没用但是符合面向对象编程概念的方法, 用于向日志中添加新的内容
     */
    public static void addLog(String str) {
        log.add(str);
    }

    /**
     * <p>
     * 将日志成员变量装载为文件并导出
     * </p><p>
     * 通过调用系统时间获得日志的名称, 然后将程序的所有控制台输出整合到日志文件中并导出到系统文件中
     * </p>
     *
     * @return 该日志文件的路径和名称
     */
    public static String printLogInFile() {

        //获得当前格式化的日期和时间
        String date = Tools.getCurrentFormatDate();
        String time = Tools.getCurrentFormatTime();

        //再次格式化
        String dt = date + "-" + time;
        String logName = "log-" + dt + ".log";
        String logPath = ".\\log\\" + logName;

        Tools.println("打印日志到:" + logPath);
        File logFile = new File(logPath);


        try {
            //创建新的日志文件
            logFile.createNewFile();
            //创建写文件用的流啥的
            FileWriter writer = new FileWriter(logFile);
            BufferedWriter bw = new BufferedWriter(writer);

            //写日志title
            bw.write("导出的日志文件\n");
            bw.write("导出时间:" + dt + "\n\n\n");

            //导出日志
            for (String str : log) {
                bw.write(str + "\n");
            }

            //打印完成后删除日志
            log.clear();

            bw.close();
            writer.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return logName;

    }


}
