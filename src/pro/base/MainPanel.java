package pro.base;

import pro.calendar.ui.CalendarPanel;
import pro.note.ui.NotePanel;
import tools.Constant;
import tools.MColor;
import tools.PropertiesReader;

import java.awt.*;

/**
 * <h2>
 * 主面板的实现类
 * </h2>
 * <p>
 *  窗体的主面板
 * </p>
 *
 * @author Max
 * @date 2023/07/01 22:14
 **/
public class MainPanel extends SuperCPanel {


    /**
     * 日历面板的最底层
     */
    private final CalendarPanel calPanel;

    /**
     * 记事本面板的最底层
     */
    private final NotePanel notePanel;

    /**
     * 布局管理器, 本面板采用卡片布局管理器
     */
    private final CardLayout card;

    private int width;

    private int height;


    /**
     * 构造函数
     */
    public MainPanel() {

        //计算自己的坐标和大小
        int x = Constant.SIDE_PANEL_WIDTH;
        int y = Constant.TITLE_PANEL_HEIGHT;
        width = Integer.parseInt(PropertiesReader.get("ScreenWidth")) - x;
        height = Integer.parseInt(PropertiesReader.get("ScreenHeight")) - y;

        //把自己的大小写到配置文件中. 这就是所有后续主窗体的真正大小
        PropertiesReader.set("MainPanelWidth",String.valueOf(width));
        PropertiesReader.set("MainPanelHeight",String.valueOf(height));


        calPanel = new CalendarPanel();
        notePanel = new NotePanel();

        card = new CardLayout();
        //设置背景(实际上这个东西应该是看不见的吧...)
        this.setBackground(MColor.MAIN_PANEL);

        //设置大小和坐标
        this.setBounds(x, y, width, height);

        //设置布局管理器为卡片布局管理器
        this.setLayout(card);

        //在添加的时候设置组件的名字用于让卡片布局管理器调用
        this.add(calPanel,Constant.CALENDAR);
        this.add(notePanel,Constant.NOTEBOOK);
    }

    @Override
    public void rePaint() {

        super.rePaint();
        calPanel.rePaint();
        notePanel.rePaint();
    }

    @Override
    public void updateSize() {
        width = this.getRootPane().getParent().getWidth() - Constant.SIDE_PANEL_WIDTH;
        height = this.getRootPane().getParent().getHeight() - Constant.TITLE_PANEL_HEIGHT;

        this.setSize(width,height);
    }

    public void switchPanel(String panelNum) {

        card.show(this, panelNum);
    }
}
