package pro.calendar.ui;

import pro.base.MFrame;
import tools.PropertiesReader;

/**
 * <h2>
 * 创建新事件用的窗体
 * </h2>
 * <p>
 * 这是一个Frame. 继承了MFrame, 和主窗体共用一个超类.
 * </p>
 *
 * @author Max
 * @date 2023/07/05 20:11
 * @see MFrame
 **/
public class EventFrame extends MFrame {

    private int x;

    private int y;

    private int width;

    private int height;

    public EventFrame() {
        super(" 新事件 - 日历");

        width = 1200;
        height = 700;

        this.setSize(width, height);
        this.initLocation();
        this.setVisible(true);

        this.init();
    }

    public void init() {

        this.rePaintAll();
    }

    public void rePaint() {

    }

    private void initLocation() {
        x = Integer.parseInt(PropertiesReader.get("ScreenWidth"));
        y = Integer.parseInt(PropertiesReader.get("ScreenHeight"));

        x = (x - width) / 2;
        y = (y - height) / 2;

        this.setLocation(x,y);
    }

    @Override
    public void exit() {
        this.setVisible(false);
    }

}
