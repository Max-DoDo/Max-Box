package pro.calendar.ui;

import pro.base.SuperCPanel;
import pro.calendar.al.MCalendar;
import pro.calendar.al.Time;
import tools.MColor;
import tools.PropertiesReader;

import java.awt.*;

/**
 * <h2>
 * 显示某月日历的面板实现类
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/03 14:15
 **/
public class MainCalPanel extends SuperCPanel {

    /**
     * 日历格子. 每个月份都拥有42个日历格子, 采用7*6的方式排序. 可以涵盖本月的所有日期
     */
    private DateGrid[][] dateGrids = new DateGrid[7][6];

    private DateGrid selectedGrid;

    private GridBagLayout gbLayout;

    /**
     * 构造函数
     */
    public MainCalPanel() {

        gbLayout = new GridBagLayout();

        this.setLayout(gbLayout);
        this.setBackground(MColor.MAIN_PANEL);
        this.setBounds(0, 0,
                Integer.parseInt(PropertiesReader.get("MainPanelWidth")),
                Integer.parseInt(PropertiesReader.get("MainPanelHeight")));
        this.initGrids();
    }

    private void initGrids() {

        //用于设置网格包布局管理器的相关参数
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 1;
        gbc.weighty = 1;

        //双向填充整个网格
        gbc.fill = GridBagConstraints.BOTH;

        //初始化格子
        for (int i = 0; i < dateGrids[0].length; i++) {
            for (int j = 0; j < dateGrids.length; j++) {

                gbc.gridx = j;
                gbc.gridy = i;

                dateGrids[j][i] = new DateGrid(this);
                this.add(dateGrids[j][i]);
                gbLayout.setConstraints(dateGrids[j][i], gbc);
            }
        }

        this.initDate();
    }

    /**
     * 初始化每个格子的日期
     */
    private void initDate() {

        //获取当前的日期
        int year = MCalendar.getCurrentYear();
        int month = MCalendar.getCurrentMonth();
        int day = MCalendar.getCurrentDay();

        //把日历调整为当前的月份, 且日期为1
        MCalendar.setDate(year, 1);

        //这个月的总天数
        int dayOfMonth = MCalendar.getTotalDayInMonth();
        //第一天是星期几

        //这个月在日历上显示时前方有几个空格. 获得当前星期几减少1即可. 例如星期1开始的月份在日历上前方没有空格.
        int blank = MCalendar.getDayInWeek() - 1;

        //数组索引
        int x = blank;
        int y = 0;
        boolean isToday = false;

        //========================================================
        //初始化本月的格子

        dateGrids[x][y].setShowMonth(true);

        for (int i = 1; i <= dayOfMonth; i++) {
            if (x == dateGrids.length) {
                x = 0;
                y += 1;
            }

            dateGrids[x][y].setTime(new Time(year, month, i));

            if (i == day) {
                dateGrids[x][y].setHighLight(true);
                isToday = true;
            }

            if (!isToday) {
                dateGrids[x][y].setPasted(true);
            }
            x++;
        }

        //========================================================
        //初始化上个月的格子

        if (blank != 0) {
            if (month == 1) {
                dateGrids[0][0].setShowYear(true);
                month = 12;
                year--;
            } else {
                month--;
            }

            //把日历调整为当前的月份, 且日期为1
            MCalendar.setDate(year, month);
            //这个月的总天数
            dayOfMonth = MCalendar.getTotalDayInMonth();
            int last = dayOfMonth - blank;

            dateGrids[0][0].setShowMonth(true);

            for (int i = dayOfMonth; i > last; i--) {
                dateGrids[blank - 1][0].setTime(new Time(year, month, i));
                dateGrids[blank - 1][0].setPasted(true);
                blank--;
            }
        }


        //========================================================
        //初始化下个月的格子

        if (month == 12) {
            dateGrids[x][y].setShowYear(true);
            month = 1;
            year++;
        } else {
            month += 2;
        }

        //把日历调整为当前的月份, 且日期为1
        MCalendar.setDate(year, month);
        day = 1;

        dateGrids[x][y].setShowMonth(true);
        while (x < 7) {

            dateGrids[x][y].setTime(new Time(year, month, day));
            x++;
            day++;
        }

    }

    public void updateSelected(DateGrid dateGrid) {

        if (selectedGrid == null) {
            selectedGrid = dateGrid;
            return;
        }

        selectedGrid.setPreSelected(false);
        selectedGrid = dateGrid;
    }

    /**
     * 切换显示到其他月份
     *
     * @param year  年份
     * @param month 月份
     */
    public void switchMonth(int year, int month) {

    }

    @Override
    public void rePaint(){
        super.rePaint();

    }

}
