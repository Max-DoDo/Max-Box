package pro.calendar.ui;

import pro.base.SuperCPanel;
import tools.Constant;
import tools.MColor;

import java.awt.*;

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
public class WeekPanel extends SuperCPanel {

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

    /**
     * 本类实例使用的网格包布局管理器
     */
    private GridBagLayout gb;

    /**
     * 本类实例使用的网格包布局管理器的约束实例
     */
    private GridBagConstraints gbc;


    public WeekPanel(CalendarPanel superP){

        father = superP;

        //初始化布局管理器
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();



        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.fill = GridBagConstraints.BOTH;


        //设置自己的一些属性
        this.setBorder(new PanelBorder());
        this.setBackground(MColor.SIDE_PANEL);
        this.setLayout(new GridLayout(1,5));

        this.initButton();

    }

    /**
     * 初始化按钮们
     */
    private void initButton(){

        toTodayButton = new CalButton("今天");

        gbc.gridwidth = 2;
        gbc.gridheight = 1;

        gb.setConstraints(toTodayButton,gbc);
        this.add(toTodayButton);


        lastButton = new CalButton("last");
        gb.setConstraints(lastButton,gbc);
        this.add(lastButton);

        nextButton = new CalButton("next");

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gb.setConstraints(nextButton,gbc);
        this.add(nextButton);


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

    }

    @Override
    public void updateSize(){
        this.setBounds(0,
                Constant.FUNCTION_PANEL_HEIGHT,
                father.getWidth() - father.getSidePanel().getWidth(),
                Constant.FUNCTION_PANEL_HEIGHT);
    }

}
