package pro.calendar.ui;

import pro.base.SuperCPanel;
import pro.calendar.al.GridGenerator;
import pro.calendar.al.Time;
import tools.Constant;
import tools.MColor;

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
    private final DateGrid[][] dateGrids = new DateGrid[7][6];

    /**
     * 当前正在选择的日期格子的引用
     */
    private DateGrid selectedGrid;

    /**
     * 本类对象使用的布局管理器. 使用网格包布局管理.
     */
    private final GridBagLayout gbLayout;

    private final CalendarPanel father;

    private int year;
    private int month;
    private int day;

    /**
     * 构造函数
     */
    public MainCalPanel(CalendarPanel superP) {

        father = superP;

        gbLayout = new GridBagLayout();

        int x = 0;

        int y = Constant.FUNCTION_PANEL_HEIGHT * 2;
        this.setLocation(x,y);

        this.setLayout(gbLayout);
        this.setBackground(MColor.MAIN_PANEL);
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
                gbLayout.setConstraints(dateGrids[j][i], gbc);
                this.add(dateGrids[j][i]);
            }
        }

        //初始化每一个格子的内容
//        this.updateDate(MCalendar.getCurrentYear(),MCalendar.getCurrentMonth());
    }

    /**
     * 让日历显示一个月份
     *
     * @param yr  年份
     * @param mth 月份
     */
    public void updateDate(int yr, int mth) {

        DateGrid todayGrid = new GridGenerator(yr,mth).generator(dateGrids);
        if(todayGrid != null){
            todayGrid.click();
        }
    }

    public void updateDate(Time time){
        DateGrid todayGrid = new GridGenerator(time.getYear(),time.getMonth()).generator(dateGrids);
        if(todayGrid != null){
            todayGrid.click();
        }
    }

    /**
     * 更新成员变量{@code selectedGrid}的值, 将上一个被选中的值取消选中.
     * @param dateGrid 被选中的格子
     */
    public void updateSelected(DateGrid dateGrid) {

        if (selectedGrid == null) {
            selectedGrid = dateGrid;
            return;
        }

        selectedGrid.setPreSelected(false);
        selectedGrid = dateGrid;
    }


    @Override
    public void rePaint(){
        super.rePaint();
    }

    @Override
    public void updateSize() {

        int width = father.getWidth() - father.getSidePanel().getWidth();
        int height = father.getHeight() - Constant.FUNCTION_PANEL_HEIGHT * 2;

        this.setSize(width,height);
    }

}
