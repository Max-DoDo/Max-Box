package pro.calendar.ui;

import pro.base.SuperCPanel;
import tools.Constant;
import tools.MColor;
import tools.PropertiesReader;

/**
 * <h2>
 *      日历面板的侧窗体
 * </h2>
 * <p>
 *      这个窗体用于展示事件
 * </p>
 *
 * @author Max
 * @date 2023/07/05 22:29
 **/
public class SidePanel extends SuperCPanel {

    /**
     * 窗体所依靠的组件
     */
    CalendarPanel father;

    /**
     * 构造函数, 设置窗体的位置大小和背景颜色.
     * 设置窗体的边框
     * @param superP 窗体所依靠的组件
     */
    public SidePanel(CalendarPanel superP){
        father = superP;

        int fatherWidth = Integer.parseInt(PropertiesReader.get("MainPanelWidth"));
        int fatherHeight =  Integer.parseInt(PropertiesReader.get("MainPanelHeight"));

        this.setSize(fatherWidth/6,fatherHeight - Constant.FUNCTION_PANEL_HEIGHT);
        this.setLocation(fatherWidth - fatherWidth/6,Constant.FUNCTION_PANEL_HEIGHT);
        this.setBackground(MColor.SIDE_PANEL);
        this.setBorder(new PanelBorder());

    }

    @Override
    public void rePaint(){
        super.rePaint();
    }

    @Override
    public void updateSize() {
        int width = father.getWidth();
        int height = father.getHeight();

        this.setSize(width / 6,height - Constant.FUNCTION_PANEL_HEIGHT);
        this.setLocation(width - width/6,Constant.FUNCTION_PANEL_HEIGHT);
    }
}
