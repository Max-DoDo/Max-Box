package pro.calendar.al;

/**
 * <h2>
 *
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/03 14:04
 **/
public class Time {

    private int year;

    private int month;

    private int week;

    private int day;


    private String weekDay;

    private boolean isToday;



    public Time(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

//    /**
//     * 返回下一天的时间对象
//     * @return
//     */
//    public Time nextDay(){
//        return new Time();
//    }

    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return this.year;
    }


}
