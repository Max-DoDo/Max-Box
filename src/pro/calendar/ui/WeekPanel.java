package pro.calendar.ui;

import pro.base.SuperCPanel;

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

    private CalendarPanel father;

    public WeekPanel(CalendarPanel superP){

        father = superP;


    }

    @Override
    public void rePaint(){
        super.rePaint();

    }

    @Override
    public void updateSize(){

    }

}
