package pro.calendar.eventui;

import pro.calendar.ui.GridBorder;
import tools.MColor;
import tools.TextAreaBorder;

import javax.swing.*;

/**
 * 创建新事件窗体中的主要组件面板.
 * 在这个面板中可以设置:
 * 1. 事件的标题
 * 2. 事件的内容
 * 3. 事件的开始时间和截至时间
 * 4. 事件的重复次数
 * 5. 事件是否是全天事件
 * 6. 事件的图标
 */
public class NewEventPanel extends JPanel {

    /**
     * 窗体内控件距离左侧面板边缘的距离
     */
    private final int LEFT_SIDE_WIDTH = 50;

    /**
     * 事件标题
     */
    private JTextField title;

    /**
     * 事件注释文本区域的垂直滚动条
     */
    private JScrollPane comment;

    /**
     * 事件注释文本区域
     */
    private JTextArea textArea;

    /**
     * 无参构造函数, 这应该也会是本类的唯一一个构造函数
     * <p>
     * 在这个构造函数中设置面板的边框, 背景颜色和编码
     * </p>
     */
    public NewEventPanel() {

        this.setBorder(new GridBorder());
        this.setLayout(null);
        this.setBackground(MColor.MAIN_PANEL);

        init();

    }

    /**
     * 初始化内部的组件
     * <p>
     * 这个方法由构造方法调用, 用于初始化窗体内的控件
     * <p>
     * * 标题文本框
     * * 注释文本框
     * </p>
     */
    private void init() {

        //初始化标题文本框
        //TODO working on it!
        title = new JTextField();
        title.setBackground(MColor.MAIN_PANEL);
        title.setForeground(MColor.FONT);
        title.setBorder(new TextAreaBorder());
        this.add(title);

        //初始化事件注释文本框
        textArea = new JTextArea();
        textArea.setBackground(MColor.MAIN_PANEL);
        textArea.setForeground(MColor.FONT);

        //自动换行
        textArea.setLineWrap(true);
        //换行不断字
        textArea.setWrapStyleWord(true);

        comment = new JScrollPane(textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        comment.setBackground(MColor.MAIN_PANEL);
//        comment.setViewportBorder(new PanelBorder());

        this.add(comment);

        this.rePaint();
    }

    /**
     * 重绘本类对象的方法, 这个方法会重新设置本类对象的位置和大小
     * 这个方法会在本类对象初始化的时候被自动调用一次用于初始化控件的位置和大小
     */
    public void rePaint() {
        title.setBounds(LEFT_SIDE_WIDTH, 20, this.getWidth() - LEFT_SIDE_WIDTH * 2, 40);
        comment.setBounds(LEFT_SIDE_WIDTH, 200, this.getWidth() - LEFT_SIDE_WIDTH * 2, 120);
    }
}
