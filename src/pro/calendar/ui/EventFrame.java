package pro.calendar.ui;

import pro.base.MFrame;

/**
 * <h2>
 *      创建新事件用的窗体
 * </h2>
 * <p>
 *      这是一个Frame. 继承了MFrame, 和主窗体共用一个超类.
 * </p>
 *
 * @author Max
 * @date 2023/07/05 20:11
 * @see MFrame
 **/
public class EventFrame extends MFrame {

    public EventFrame(){

        this.setBounds(0,0,500,500);
        this.setVisible(true);

        this.init();
    }

    public void init(){

        this.rePaintAll();
    }

    public void rePaint(){

    }

    @Override
    public void exit(){
        this.setVisible(false);
    }

}
