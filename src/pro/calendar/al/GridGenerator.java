package pro.calendar.al;

import pro.calendar.ui.DateGrid;

/**
 * <h2>
 *
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/05 17:31
 **/
public class GridGenerator extends Object{


    /**
     * 需要生成的日历的年月日
     */
    private final Time calendar;

    private int year;

    private int month;

    /**
     * 该月份共有几天
     */
    private int dayOfMonth;

    /**
     * 该月份在标准日历表上前方有多少空格.
     */
    private int blank;

    /**
     * 循环用数组索引
     */
    private int x;

    /**
     * 循环用数组索引
     */
    private int y;

    /**
     * 共生成了多少个格子
     */
    private int count;

    /**
     * 今天的日期
     */
    private Time today;

    /**
     * 正在生成的月份是否是这个月
     */
    private boolean isThisMonth;

    /**
     * 日历
     */
    private DateGrid[][] dg;

    private static final int SHOW_YEAR = 1;

    private static final int SHOW_MONTH = 2;

    private static final int SHOW_DAY = 0;

    private static final int SHOW_DEF = -1;


    /**
     * 自动构造一个生成器, 年份和月份会使用系统当前时间.
     */
    public GridGenerator(){

        calendar = new Time(MCalendar.getCurrentYear(),MCalendar.getCurrentMonth());
        init();
    }

    /**
     * 通过年份和月份来构造一个生成器.
     * @param yr 年份
     * @param mth 月份
     */
    public GridGenerator(int yr, int mth){

        calendar = new Time(yr,mth);
        init();
    }

    /**
     * 初始化成员变量
     */
    private void init(){

        //今天的日期
        today = new Time(MCalendar.getCurrentYear(),MCalendar.getCurrentMonth(),MCalendar.getCurrentDay());

        //把日历调整为当前的月份, 且日期为1
        MCalendar.setDate(calendar.getYear(), calendar.getMonth());

        //这个月的总天数
        dayOfMonth = MCalendar.getTotalDayInMonth();
        //第一天是星期几

        //这个月在日历上显示时前方有几个空格. 获得当前星期几减少1即可. 例如星期1开始的月份在日历上前方没有空格.
        blank = MCalendar.getDayInWeek() - 1;

        //数组索引
        x = blank;
        y = 0;
        count = 0;

    }

    public void generator(DateGrid[][] dateGrids){

        this.dg = dateGrids;
        dg[0][0].setShowMonth(true);

        this.lastMonth();
        this.thisMonth();
        this.nextMonth();
    }

    private void generatorGrid(DateGrid dateGrid,Time time){

        dateGrid.setTime(time);

        int st = time.compare(today);

        switch (st){
            case Time.EQUAL -> {
                dateGrid.setHighLight(true);
            }

            case Time.OLDER ->  {
                dateGrid.setPasted(true);

            }
            case Time.YOUNGER -> {
            }

        }

        count++;
    }

    private void lastMonth(){

        //如果本月日历前方没有空格就不生成了, 防止indexOutOfBound.
        if (blank != 0) {

            //初始化这个月的相关数值
            this.updateCalendar(false);

            if(month == 12){
                dg[0][0].setShowYear(true);
            }else{
                dg[0][0].setShowMonth(true);

            }

            //把日历调整为当前的月份, 且日期为1
            MCalendar.setDate(year, month);
            //这个月的总天数
            int total = MCalendar.getTotalDayInMonth();

            int last = total - blank;



            for (int i = total; i > last; i--) {
                this.generatorGrid(dg[blank - 1][0],new Time(year, month, i));
                blank--;
            }


        }
    }

    private void thisMonth(){
        //==========================================初始化这个月
        //使用单循环手动递增第二维度索引

        this.updateCalendar(true);

        if(month == 1){
            dg[x][y].setShowYear(true);
        }else{
            dg[x][y].setShowMonth(true);
        }

        for (int i = 1; i <= dayOfMonth; i++) {

            //递增第二维度索引
            if (x == dg.length) {
                x = 0;
                y += 1;
            }

            //设置
            this.generatorGrid(dg[x][y],new Time(year, month, i));
            x++;
        }
    }

    private void nextMonth(){
        //========================================================
        //初始化下个月的格子
        //顺序化x避免上次用完后顶到头了溢出了
        if (x == dg.length) {
            x = 0;
            y += 1;
        }
        this.updateCalendar(true);

        if(month == 1){
            dg[x][y].setShowYear(true);
        }else{
            dg[x][y].setShowMonth(true);

        }
        //把日历调整为当前的月份, 且日期为1
        MCalendar.setDate(year, month);
        int day = 1;

        while (count < 42) {

            if (x == dg.length) {
                x = 0;
                y += 1;
            }

            this.generatorGrid(dg[x][y],new Time(year, month, day));

            x++;
            day++;
        }
    }

    private void updateCalendar(boolean toward){
        calendar.stepMonth(toward);
        year = calendar.getYear();
        month = calendar.getMonth();
    }

    private void setShown(DateGrid dg){

        if(month == 1){
            dg.setShowYear(true);
        }







    }
}