package pro.calendar.eventui;

import pro.base.MFrame;
import pro.calendar.al.Event;
import pro.calendar.al.Time;
import pro.calendar.ui.CalButton;
import tools.Constant;
import tools.MColor;
import tools.PropertiesReader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
public class NewEventFrame extends MFrame implements MouseListener {

    /**
     * 窗体的x轴坐标
     */
    private int x;

    /**
     * 窗体的y轴坐标
     */
    private int y;

    /**
     * 保存事件按钮
     */
    private CalButton createEventButton;

    /**
     * 取消创建事件按钮
     */
    private CalButton cancelEventButton;

    /**
     * 大部分组件所在的panel.
     */
    private NewEventPanel eventPanel;

    /**
     * 事件对象
     */
    private Event event;

    /**
     * 本类的构造函数. 通过调用该方法创建对象来自动的新建并且打开一个"新建事件"的窗口.
     */
    public NewEventFrame(Time time) {

        super(" 新事件 - 日历");

        int width = Integer.parseInt(PropertiesReader.get("ScreenWidth"));
        int height = Integer.parseInt(PropertiesReader.get("ScreenHeight"));

        width = width / 4 * 3;
        height = height / 4 * 3;

        event = new Event();

        this.setSize(width, height);
        this.initLocation();
//        this.setResizeable(false);
        this.setTitleTextColor(MColor.FONT);

        this.setVisible(true);

        this.init();
    }

    /**
     * 初始化面板中的组件内容
     */
    public void init() {

        //初始化保存事件的按钮
        createEventButton = new CalButton("保存");
        createEventButton.setSize(this.getWidth() / 10, Constant.TITLE_PANEL_HEIGHT);
        createEventButton.setLocation(10, Constant.TITLE_PANEL_HEIGHT + 10);
        createEventButton.setBackground(MColor.SAVE_EVENT_BUTTON);
        createEventButton.setFont(new Font(PropertiesReader.get("GlobalFont"),
                Font.BOLD,
                Integer.parseInt(PropertiesReader.get("FontSize")) - 3));
        createEventButton.setForeground(Color.BLACK);
        createEventButton.setArmedColor(new Color(87, 170, 247));
        createEventButton.setRolloverColor(new Color(100, 180, 250));
        createEventButton.addMouseListener(this);
        this.add(createEventButton);

        //取消按钮
        cancelEventButton = new CalButton("放弃");
        cancelEventButton.setSize(this.getWidth() / 10, Constant.TITLE_PANEL_HEIGHT);
        cancelEventButton.setLocation(this.getWidth() - cancelEventButton.getWidth() - 10, Constant.TITLE_PANEL_HEIGHT + 10);
        cancelEventButton.setFont(new Font(PropertiesReader.get("GlobalFont"),
                Font.BOLD,
                Integer.parseInt(PropertiesReader.get("FontSize")) - 3));
        cancelEventButton.addMouseListener(this);
        this.add(cancelEventButton);

        eventPanel = new NewEventPanel();
        eventPanel.setSize(this.getWidth(), this.getHeight() - (Constant.TITLE_PANEL_HEIGHT * 2 + 10 + 10));
        eventPanel.setLocation(0, Constant.TITLE_PANEL_HEIGHT * 2 + 10 + 10);

        this.add(eventPanel);

        this.rePaintAll();
    }

    @Override
    public void rePaintAll() {
        super.rePaintAll();
        this.rePaint();
        eventPanel.rePaint();
    }

    private void rePaint() {

        cancelEventButton.setLocation(this.getWidth() - cancelEventButton.getWidth() - 10, Constant.TITLE_PANEL_HEIGHT + 10);
        eventPanel.setSize(this.getWidth(), this.getHeight() - (Constant.TITLE_PANEL_HEIGHT * 2 + 10 + 10));

    }

    private void initLocation() {
        x = Integer.parseInt(PropertiesReader.get("ScreenWidth"));
        y = Integer.parseInt(PropertiesReader.get("ScreenHeight"));

        x = (x - this.getWidth()) / 2;
        y = (y - this.getHeight()) / 2;

        this.setLocation(x, y);
    }

    /**
     * 保存事件按钮单击的事件处理方法
     */
    private void saveEvent() {
        System.out.println("save event");
    }

    @Override
    public void exit() {
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();

        if (source.equals(createEventButton)) {
            this.saveEvent();
            return;
        }

        if (source.equals(cancelEventButton)) {
            this.exit();
            return;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
