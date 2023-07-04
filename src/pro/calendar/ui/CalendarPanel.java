package pro.calendar.ui;

import pro.base.MainPanel;
import pro.base.SuperCPanel;
import tools.MColor;

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


    /**
     * 默认的构造函数
     */
    public CalendarPanel(){

        this.setBackground(MColor.MAIN_PANEL);

        this.setLayout(null);

        mainCalPanel = new MainCalPanel();

        this.add(mainCalPanel);

    }

    @Override
    public void rePaint(){

        super.rePaint();
        mainCalPanel.rePaint();
    }
}
