package pro.calendar.ui;

import pro.base.MainPanel;
import pro.base.SuperCPanel;
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

        sidePanel = new SidePanel(this);
        this.add(sidePanel);

        functionPanel = new FunctionPanel(this);
        this.add(functionPanel);

        weekPanel = new WeekPanel(this);
        this.add(weekPanel);

        mainCalPanel = new MainCalPanel(this);
        this.add(mainCalPanel);




    }

    @Override
    public void rePaint(){
        super.rePaint();

        functionPanel.rePaint();
        weekPanel.rePaint();
        sidePanel.rePaint();
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
