package GUI;

import Tools.Constant;
import Tools.MColor;
import Tools.PropertiesReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h2>
 * 右侧面板的实现类
 * </h2>
 * <p>
 * 这个面板用于装选择功能的按钮.
 * </p>
 *
 * @author Max
 * @date 2023/07/01 22:14
 **/
public class SidePanel extends JPanel implements ActionListener {

    /**
     * 日历功能按钮
     */
    private RoundButton calendarButton;

    /**
     * 日历功能按钮图标
     */
    private ImageIcon calendarIcon;

    /**
     * 笔记功能按钮
     */
    private RoundButton noteButton;

    /**
     * 笔记按钮图标
     */
    private ImageIcon noteIcon;

    /**
     * 当前正在展示的按钮
     */
    private JButton focusButton;

    private int width;

    private int height;

    /**
     * 构造函数
     */
    public SidePanel() {
        super();
    }

    /**
     * 初始化该面板和其他属性的方法
     */
    public void init() {

        width = Constant.SIDE_PANEL_WIDTH;
        height = Integer.parseInt(PropertiesReader.get("ScreenHeight"));
        focusButton = calendarButton;
        int x = 0;
        int y = Constant.TITLE_PANEL_HEIGHT;

        //设置大小和位置
        this.setBounds(x, y, width, height);

        //设置背景颜色
        this.setBackground(MColor.SIDE_PANEL);

        //设置布局管理器为绝对布局管理器
        this.setLayout(null);

        //初始化logo
        this.initIcon();

        //初始化按钮
        this.initButton();
    }

    /**
     * 封装的初始化按钮logo的方法
     */
    private void initIcon() {
        calendarIcon = new ImageIcon(".\\resource\\calendarIcon.png");
        noteIcon = new ImageIcon(".\\resource\\noteIcon.png");
    }

    /**
     * 封装的初始化按钮的方法
     */
    private void initButton() {

        int width = 34;
        int height = 34;

        //让组件在边栏中居中显示. 组件的大小不能超过边栏的大小, 否则会出问题
        int x = (this.width - width) / 2;
        int oriY = 10;

        int diff = 15;

        calendarButton = new RoundButton(calendarIcon);
        noteButton = new RoundButton(noteIcon);

        //设置背景颜色
        calendarButton.setBackground(MColor.SIDE_PANEL);
        noteButton.setBackground(MColor.SIDE_PANEL);

        //设置位置
        calendarButton.setBounds(x, oriY, width, height);
        noteButton.setBounds(x, oriY + height + diff, width, height);

        calendarButton.addActionListener(this);
        noteButton.addActionListener(this);

        this.add(calendarButton);
        this.add(noteButton);


    }

    /**
     * 重绘面板的方法. 用于修改内部控件的颜色和位置啥的
     */
    public void rePaint() {
        this.setBackground(MColor.SIDE_PANEL);

        if(focusButton != null){
            focusButton.setBackground(MColor.GRAY);
        }
    }

    private void calendarButtonClick() {
        switchFocusButton(calendarButton);

    }

    private void noteButtonClick() {
        switchFocusButton(noteButton);

    }

    /**
     * 切换当前焦点处的按钮的方法.
     *
     * @param jb 当前被点击的按钮. 不要在意名字
     */
    private void switchFocusButton(JButton jb) {

        if (focusButton == null) {

            focusButton = jb;
            focusButton.setBackground(MColor.GRAY);

        } else if (focusButton.equals(jb)) {

            return;

        } else {

            focusButton.setBackground(MColor.SIDE_PANEL);
            jb.setBackground(MColor.GRAY);
            focusButton = jb;

        }
    }


    /**
     * 重写的源自接口的方法
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source.equals(calendarButton)) {
            calendarButtonClick();
            return;
        }

        if (source.equals(noteButton)) {
            noteButtonClick();
            return;
        }

    }
}
