package pro.calendar.ui;

import pro.base.SuperCPanel;
import tools.Constant;
import tools.MColor;
import tools.PropertiesReader;

/**
 * <h2>
 *      功能面板
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/05 22:28
 **/
public class FunctionPanel extends SuperCPanel {

    CalendarPanel father;

    public FunctionPanel(CalendarPanel superP){

        father = superP;

        int width = Integer.parseInt(PropertiesReader.get("MainPanelWidth"));
        int height = Constant.FUNCTION_PANEL_HEIGHT;

        this.setBounds(0,0,width,height);
        this.setBackground(MColor.SIDE_PANEL);
        this.setBorder(new PanelBorder());

    }

    @Override
    public void rePaint(){
       super.rePaint();
    }

    @Override
    public void updateSize(){
        this.setBounds(0,0,father.getWidth(),Constant.FUNCTION_PANEL_HEIGHT);
    }
}
