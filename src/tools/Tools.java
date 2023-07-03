package tools;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;

/**
 *  <p><b>
 *  这个类是一个静态的工具类, 用于在构造项目中的某些算法的实现
 *  </b></p>
 *  其所包含的所有方法都应当是静态的
 *  因此这个类不能被实例化为对象, 也不能被继承
 *
 * @author Maxwell 王一帆
 * @version 1.1
 */
public class Tools {

    /**
     * <p>
     * 输出带时间戳的内容到控制台.且在结尾换行
     * </p><p>
     * 用于替换<code>System.out.println()</code>方法
     * </p><p>
     * 这个方法的时间戳的实现本质上是创建和调用了<code>MCalendar</code>类的对象
     * 有很多优化空间, 例如使用<code>format()</code>方法来获得该类对象的事件内容
     * </p>
     * @see java.util.Calendar
     * @see java.lang.System
     * @param obj 输出的内容
     */
    public static void println(Object obj){
        String data = format(obj);
        Log.addLog(data);
        System.out.println(data);
    }

    /**
     * 重载的输出带时间戳的内容到控制台的方法, 且在结尾换行
     * <p>
     *     可以指定是否将内容记录到日志中
     * </p>
     * @param obj 输出的内容
     * @param isLog 是否记录到日志中
     */
    public static void println(Object obj, boolean isLog){
        String data = format(obj);
        if(isLog)Log.addLog(data);
        System.out.println(data);
    }

    /**
     * <p>
     *     输出带时间戳的错误信息到控制台, 且在结尾换行
     * </p>
     * @param obj 输出的内容
     */
    public static void printErr(Object obj){
        String data = formatErr(obj);
        Log.addLog(data);
        System.err.println(data);
    }

    public static String getCurrentFormatTime(){
        //获得当前时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        int milSec = calendar.get(Calendar.MILLISECOND);
        return hour + "-" + min + "-" + sec + "-" + milSec;
    }

    public static String getCurrentFormatDate(){
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        return year + "-" + month + "-" + date;
    }

    private static String format(Object obj){

        return "Terminal: " + getCurrentFormatTime() +"\t"+ obj;
    }

    private static String formatErr(Object obj){
        return "ERROR: " + getCurrentFormatTime() +"\t"+ obj;
    }

    /**
     * <p>
     *     这个方法用于设置全局的字体. 应当在UI界面初始化的时候调用该方法.
     *     看起来是个静态的方法就直接丢到<code>Tools</code>类里好了
     * </p>
     * 网上抄来的
     * @param font 字体设置
     */
    public static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    /**
     * 判断字符串是否由纯整数组成
     * @param str 判断字符串
     * @return 布尔值. 若为整数返回True. 反之False
     */
    public static boolean canParseInt(String  str) {

        if (str == null) { //验证是否为空
            return false;
        }
        return str.matches("\\d+");
    }

    /**
     * 获得屏幕的宽度和高度
     * @return int类型数组, 长度为2. 其中1为屏幕宽度, 2为屏幕高度
     */
    public static int[] getScreenSize(){
        //获得当前屏幕的宽度和高度
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int[] value = new int[2];
        value[0] = (int)screenSize.getWidth();
        value[1] = (int)screenSize.getHeight();

        return value;
    }

    /**
     * 查找数组中是否包含某元素
     * @param array 数组
     * @param obj 需要查找的元素
     * @return 如果包含该元素返回true, 否则返回false
     */
    public static boolean in(Object[] array,Object obj){
        return Arrays.asList(array).contains(obj);
    }

    public static String format_D(int day){
        return addSpace(day + " 日 ");

    }

    public static String format_MD(int month, int day){
        return addSpace(month + " 月 " + day + " 日 ");

    }

    public static String format_YMD(int year, int month, int day){
        return addSpace(year + " 年 " + month + " 月 " + day  + " 日 ");
    }

    private static String addSpace(String str){
        return "    " + str;
    }


    /**
     * 私有化的构造方法. 禁止实例化本类对象
     * */
    private Tools(){}

}
