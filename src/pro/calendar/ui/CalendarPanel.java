package pro.calendar.ui;

import pro.base.MainPanel;
import pro.base.SuperCPanel;

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

        mainCalPanel = new MainCalPanel(this);
        this.add(mainCalPanel);




    }

    @Override
    public void rePaint(){

        super.rePaint();
        mainCalPanel.rePaint();
        sidePanel.rePaint();
    }

    public int getSidePanelWidth(){
        return sidePanel.getWidth();
    }
}
