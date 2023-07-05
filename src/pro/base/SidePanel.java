package pro.base;

import tools.Constant;
import tools.MColor;
import tools.PropertiesReader;

import javax.swing.*;
import java.awt.*;
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

    /**
     * 存储侧面板的长和宽, 在目前的构思下这个面板的长和宽是在主程序启动后就不会更改了的. 因为按钮是从上向下排布的.
     */
    private final int width,height;

    /**
     * 构造函数
     */
    public SidePanel() {

        width = Constant.SIDE_PANEL_WIDTH;
        height = Integer.parseInt(PropertiesReader.get("ScreenHeight"));

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

        //重启焦点按钮
        this.loadFocusButton();

        //重绘
        this.rePaint();
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

        //设置名字, 用于存储配置文件的最后打开页面
        calendarButton.setName(Constant.CALENDAR);
        noteButton.setName(Constant.NOTEBOOK);

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

    private void loadFocusButton(){
        String name = PropertiesReader.get("FocusButton");
        if(name.equals(Constant.CALENDAR)) {
            focusButton = calendarButton;
        }

        if(name.equals(Constant.NOTEBOOK)){
            focusButton = noteButton;
        }

    }

    /**
     * 重绘面板的方法. 用于修改内部控件的颜色和位置啥的
     */
    public void rePaint() {
        this.setBackground(MColor.SIDE_PANEL);

        if(focusButton != null){
            focusButton.setBackground(Color.GRAY);
        }
    }

    /**
     * 日历功能按钮被点击的实现方法
     */
    private void calendarButtonClick() {
        switchFocusButton(calendarButton);

        MainFrame mf = (MainFrame) this.getRootPane().getParent();
        mf.switchPanel(Constant.CALENDAR);
    }

    /**
     * 记事本功能按钮被点击的实现方法
     */
    private void noteButtonClick() {
        switchFocusButton(noteButton);
        MainFrame mf = (MainFrame) this.getRootPane().getParent();
        mf.switchPanel(Constant.NOTEBOOK);
    }

    /**
     * 切换当前焦点处的按钮的方法.
     *
     * @param jb 当前被点击的按钮. 不要在意名字
     */
    private void switchFocusButton(JButton jb) {

        if (focusButton == null) {

            focusButton = jb;
            focusButton.setBackground(Color.GRAY);

        } else if (focusButton.equals(jb)) {

            return;

        } else {

            focusButton.setBackground(MColor.SIDE_PANEL);
            jb.setBackground(Color.GRAY);
            focusButton = jb;

        }
    }

    /**
     * Before整个程序关闭之前的方法, 由主面板的关闭方法调用
     * <p>
     *     保存当前正在打开的窗体到配置文件中, 启动时还打开它
     */
    public void exit(){
        if(focusButton != null){
            PropertiesReader.set("FocusButton",focusButton.getName());

        }
    }

    public String getFocusButtonName(){
        return focusButton.getName();
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
