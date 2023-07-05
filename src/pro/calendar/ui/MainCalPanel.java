package pro.calendar.ui;

import pro.base.SuperCPanel;
import pro.calendar.al.GridGenerator;
import pro.calendar.al.MCalendar;
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
    private final DateGrid[][] dateGrids = new DateGrid[7][6];

    /**
     * 当前正在选择的日期格子的引用
     */
    private DateGrid selectedGrid;

    /**
     * 本类对象使用的布局管理器. 使用网格包布局管理.
     */
    private final GridBagLayout gbLayout;

    private int year;
    private int month;
    private int day;

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

        this.updateDate(MCalendar.getCurrentYear(),MCalendar.getCurrentMonth());
    }

    /**
     * 让日历显示一个月份
     *
     * @param yr  年份
     * @param mth 月份
     */
    public void updateDate(int yr, int mth) {

        new GridGenerator(yr,mth).generator(dateGrids);
    }

    private void updateLast(){

    }

    /**
     * gdd
     */
    private void getDateData(int yr, int mth){

    }

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

}
