package pro.calendar.al;

import java.util.Calendar;

/**
 * <h2>
 * 日历的逻辑类
 * </h2>
 * <p>
 *      其实就是封装了一下{@link Calendar}类的方法, 稍微简化了一点点(对于我这个程序来说)
 * </p>
 *
 * @author Max
 * @date 2023/07/02 23:13
 * @see Calendar
 **/
public final class MCalendar {

    private static final Calendar cal = Calendar.getInstance();
    ;

    public static int getCurrentYear() {

        return cal.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {

        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay(){

        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 自定义日历. 其中day为当月第一天
     * @param y 自定义年份
     * @param m 自定义月份
     */
    public static void setDate(int y, int m) {

        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
    }

    /**
     * 获得指定月份的总天数
     * @return 月份的总天数
     */
    public static int getTotalDayInMonth(){

        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getDayInWeek(){

        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day == Calendar.SUNDAY){
            day = 7;
        }else{
            day--;
        }

        return day;

    }




}
