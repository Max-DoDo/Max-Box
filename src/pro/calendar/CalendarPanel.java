package pro.calendar;

import pro.MainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * <h2>
 *      日历面板
 * </h2>
 * <p>
 *      这个类是日历面板的实现类. 是{@link MainPanel}面板的子组件.
 * </p>
 *
 * @author Max
 * @date 2023/07/02 21:03
 * @see MainPanel
 * @see java.util.Calendar
 **/
public class CalendarPanel extends JPanel {

    /**
     * 默认的构造函数
     */
    public CalendarPanel(){


        this.setBackground(Color.PINK);
    }

    public void rePaint(){

    }
}
