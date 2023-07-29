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

    private final int LEFT_SIDE_WIDTH = 50;

    private JTextField title;

    /**
     * 事件注释文本区域的垂直滚动条
     */
    private JScrollPane comment;

    /**
     * 事件注释文本区域
     */
    private JTextArea textArea;

    NewEventPanel(){

        this.setBorder(new GridBorder());
        this.setLayout(null);
        this.setBackground(MColor.MAIN_PANEL);

        init();

    }

    /**
     * 初始化内部的组件
     */
    private void init(){

        //初始化标题文本框
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

        this.add(comment);


        this.rePaint();
    }

    public void rePaint(){
        title.setBounds(LEFT_SIDE_WIDTH,20,this.getWidth() - LEFT_SIDE_WIDTH * 2, 40);
        comment.setBounds(LEFT_SIDE_WIDTH,200,this.getWidth() - LEFT_SIDE_WIDTH * 2, 120);
    }
}
