package pro.calendar.ui;

import pro.base.MainPanel;
import pro.base.SuperCPanel;
import pro.calendar.al.MCalendar;
import pro.calendar.al.Time;
import tools.Constant;

import java.awt.*;

/**
 * <h2>
 *      日历面板
 * </h2>
 * <p>
 *      这个类是日历面板的实现类. 是{@link MainPanel}面板的子组件. 它是最底层的实现类, 他的上面还会覆盖许多其他的面板
 * </p>
 *
 * @author Max
 * @date 2023/07/02 21:03
 * @see MainPanel
 * @see java.util.Calendar
 **/
public final class CalendarPanel extends SuperCPanel {

    Time currentTime;

    MainCalPanel mainCalPanel;

    FunctionPanel functionPanel;

    SidePanel sidePanel;

    WeekPanel weekPanel;


    /**
     * 默认的构造函数
     */
    public CalendarPanel(){

        this.setBackground(Color.PINK);
        this.setLayout(null);
        currentTime = new Time(MCalendar.getCurrentYear(),MCalendar.getCurrentMonth());

        init();
    }

    /**
     * 用于向这个面板中添加子组件的方法
     */
    private void init(){
        sidePanel = new SidePanel(this);
        this.add(sidePanel);

        functionPanel = new FunctionPanel(this);
        this.add(functionPanel);

        weekPanel = new WeekPanel(this);
        this.add(weekPanel);

        mainCalPanel = new MainCalPanel(this);
        mainCalPanel.updateDate(currentTime);
        this.add(mainCalPanel);
    }

    /**
     * 更新主月份面板等操作面板所展示的月份
     */
    public void updateMonth(Time time){
        currentTime = time;
        mainCalPanel.updateDate(time);
    }

    public void nextMonth(){
        currentTime.stepMonth(Time.TOWARD);
        mainCalPanel.updateDate(currentTime);
    }

    public void lastMonth(){
        currentTime.stepMonth(Time.BACKWARD);
        mainCalPanel.updateDate(currentTime);
    }

    @Override
    public void rePaint(){
        super.rePaint();

        functionPanel.rePaint();
        sidePanel.rePaint();
        weekPanel.rePaint();
        mainCalPanel.rePaint();
    }

    @Override
    public void updateSize() {
        int width = this.getRootPane().getParent().getWidth() - Constant.SIDE_PANEL_WIDTH;
        int height = this.getRootPane().getParent().getHeight() - Constant.TITLE_PANEL_HEIGHT;

        this.setSize(width,height);
    }

    public SidePanel getSidePanel(){
        return sidePanel;
    }
}
