package pro.calendar.ui;

import pro.calendar.al.MCalendar;
import pro.calendar.al.Time;
import tools.RoundBorder;
import pro.base.SuperCPanel;
import tools.Constant;
import tools.MColor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h2>
 *      展示星期和可以上翻页下翻页的面板
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/06 15:30
 **/
public class WeekPanel extends SuperCPanel implements ActionListener {

    private final CalendarPanel father;

    /**
     * 返回到今天的按钮
     */
    private CalButton toTodayButton;

    /**
     * 到下一个月的按钮
     */
    private CalButton nextButton;

    /**
     * 到上一个月的按钮
     */
    private CalButton lastButton;

    /**
     * 展示和选择月份的按钮
     */
    private CalButton selectButton;

    public WeekPanel(CalendarPanel superP){

        father = superP;

        //设置自己的一些属性
        this.setBorder(new PanelBorder());
        this.setBackground(MColor.SIDE_PANEL);
        this.setLayout(null);

        this.initButton();

    }

    /**
     * 初始化按钮们
     */
    private void initButton(){

        toTodayButton = new CalButton("今天");
        toTodayButton.setBorder(new RoundBorder());
        toTodayButton.addActionListener(this);
        this.add(toTodayButton);

        lastButton = new CalButton("last");
        lastButton.setBorder(null);
        lastButton.addActionListener(this);
        this.add(lastButton);

        nextButton = new CalButton("next");
        nextButton.setBorder(null);
        nextButton.addActionListener(this);
        this.add(nextButton);

        selectButton = new CalButton();
        selectButton.setBorder(null);
        selectButton.addActionListener(this);
        this.add(selectButton);

    }



    @Override
    public void rePaint(){

        super.rePaint();
        this.rePaintButton();

    }

    /**
     * 重新绘制按钮的位置, 大小, 内容和颜色.
     */
    private void rePaintButton(){

        int height;

        if(this.getHeight() / 2 - 5 >= 5){
            height = this.getHeight() / 2 - 5;
        }else{
            height = this.getHeight() / 2;
        }

        toTodayButton.setSize(this.getWidth()/10 - 5,height);
        toTodayButton.setLocation(5,5);

        lastButton.setSize(height,height);
        lastButton.setLocation(toTodayButton.getWidth() + toTodayButton.getX() + 5, 5);

        nextButton.setSize(height,height);
        nextButton.setLocation(lastButton.getWidth() + lastButton.getX() + 5, 5);

        selectButton.setSize(this.getWidth() / 8 - 5,height);
        selectButton.setLocation(nextButton.getWidth() + nextButton.getX() + 5, 5);

    }

    @Override
    public void updateSize(){
        this.setBounds(0,
                Constant.FUNCTION_PANEL_HEIGHT,
                father.getWidth() - father.getSidePanel().getWidth(),
                Constant.FUNCTION_PANEL_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source.equals(lastButton)){
            father.lastMonth();
            return;
        }

        if(source.equals(nextButton)){
            father.nextMonth();
            return;
        }

        if(source.equals(toTodayButton)){
            Time current = new Time(MCalendar.getCurrentYear(),MCalendar.getCurrentMonth());
            System.out.println(current);
            father.updateMonth(current);
        }
    }
}
