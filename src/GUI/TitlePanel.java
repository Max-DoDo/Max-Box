package GUI;

import Tools.MColor;
import Tools.PropertiesReader;
import Tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 标题栏
 * <p>
 *
 * </p>
 */
public class TitlePanel extends JPanel implements ActionListener,MouseListener {

    /**
     * 标题图标
     */
    private JButton titleIcon;

    /**
     * 标题图标的下拉菜单框架
     */
    private JPopupMenu titleIconMenu;

    /**
     * 标题图标下拉菜单选项卡
     */
    private JMenuItem[] menuItems;

    /**
     * 标题图标下拉菜单选项卡内容
     */
    private String[] menuItemName;


    /**
     * 最小化按钮
     */
    private JButton closeButton;

    /**
     * 切换大小按钮
     */
    private JButton reSizeButton;

    /**
     * 关闭按钮
     */
    private JButton exitButton;

    /**
     * 设置按钮
     */
    private JButton settingButton;

    private int titlePanelWidth;

    private int titlePanelHeight;


    /**
     * 默认构造函数
     */
    public TitlePanel() {

        super();
    }

    /**
     * 初始化该面板
     */
    public void init() {

        titlePanelWidth = Integer.parseInt(PropertiesReader.get("ScreenWidth"));
        titlePanelHeight = 46;
        this.setSize(titlePanelWidth, titlePanelHeight);

        this.setLayout(null);
        this.initIcon();
        this.initButton();
        this.initIconMenu();
    }

    /**
     * 重新设置控件的大小和位置
     */
    public void reSize() {

        //本体的相关设置
        titlePanelWidth = this.getRootPane().getParent().getWidth();
        titlePanelHeight = 46;
        this.setSize(titlePanelWidth, titlePanelHeight);

        //老三样的设置
        int width = this.getHeight() + 10;
        int oriX = this.getWidth() - (3 * width);
        int y = 0;
        closeButton.setLocation(oriX, y);
        reSizeButton.setLocation(oriX + width, y);
        exitButton.setLocation(oriX + (width * 2), y);

    }

    public void reColor() {

        reColorButton();
    }

    private void reColorButton() {

        closeButton.setBackground(MColor.SIDE_PANEL);
        reSizeButton.setBackground(MColor.SIDE_PANEL);
        exitButton.setBackground(MColor.SIDE_PANEL);
    }

    private void initIcon() {

        Icon icon = new ImageIcon(".\\resource\\titleIcon.png");

        titleIcon = new JButton(icon);
        titleIcon.setLocation(0, 5);
        titleIcon.setSize(30, 30);

        //去掉按钮内部颜色填充
        titleIcon.setContentAreaFilled(false);

        //去掉按钮边框
        titleIcon.setBorderPainted(false);

        //设置监听
        titleIcon.addActionListener(this);
        this.add(titleIcon);

    }

    /**
     * 初始化右边老三样
     */
    private void initButton() {

        //他们的高度和宽度是一样的
        int width = this.getHeight() + 10;
        int height = this.getHeight();
        int oriX = this.getWidth() - (3 * width);
        int y = 0;

        //设置他们的图标
        Icon Icon = new ImageIcon(".\\resource\\closeIcon.png");
        closeButton = new JButton(Icon);

        Icon = new ImageIcon(".\\resource\\resizeIcon.png");
        reSizeButton = new JButton(Icon);

        Icon = new ImageIcon(".\\resource\\exitIcon.png");
        exitButton = new JButton(Icon);

        //设置他们的大小
        closeButton.setSize(width, height);
        reSizeButton.setSize(width,height);
        exitButton.setSize(width,height);

        //设置他们的背景颜色
        closeButton.setBackground(MColor.SIDE_PANEL);
        reSizeButton.setBackground(MColor.SIDE_PANEL);
        exitButton.setBackground(MColor.SIDE_PANEL);

        //设置他们的位置
        closeButton.setLocation(oriX, y);
        reSizeButton.setLocation(oriX + width, y);
        exitButton.setLocation(oriX + (width * 2), y);


        //去掉按钮边框和焦点边框
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        reSizeButton.setBorderPainted(false);
        reSizeButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);

        //设置监听
        closeButton.addActionListener(this);
        closeButton.addMouseListener(this);
        reSizeButton.addActionListener(this);
        reSizeButton.addMouseListener(this);
        exitButton.addActionListener(this);
        exitButton.addMouseListener(this);

        this.add(closeButton);
        this.add(reSizeButton);
        this.add(exitButton);



    }

    /**
     * 初始化单击标题图标弹出的菜单栏
     */
    private void initIconMenu() {

        titleIconMenu = new JPopupMenu();

        //设置菜单内容
        menuItemName = new String[]{"还原", "最小化", "最大化", "关闭"};
        menuItems = new JMenuItem[menuItemName.length];

        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i] = new JMenuItem(menuItemName[i]);
            menuItems[i].addActionListener(this);
            titleIconMenu.add(menuItems[i]);
        }


    }

    /**
     *
     */
    private void showTitleMenu() {

        titleIconMenu.show(titleIcon, titleIcon.getWidth(), titleIcon.getHeight());
    }

    /**
     * 重写源自接口的方法来实现对控件的监听
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        String name = e.getActionCommand();

        if (source.equals(titleIcon)) {
            showTitleMenu();
            return;
        }

        if(source.equals(reSizeButton)){
            MainFrame mf = (MainFrame) this.getRootPane().getParent();
            if(mf.isFullScreen()){
                reSizeButton.setIcon(new ImageIcon(".\\resource\\resizeIcon2.png"));
                mf.reState(JFrame.NORMAL);
            }else{
                reSizeButton.setIcon(new ImageIcon(".\\resource\\resizeIcon.png"));
                mf.reState(JFrame.MAXIMIZED_BOTH);
            }

            return;
        }

        if (name.equals(menuItemName[0])) {
            Tools.println("标题图标下拉菜单按钮:'还原'被单击");
            MainFrame mf = (MainFrame) this.getRootPane().getParent();
            mf.reState(JFrame.NORMAL);

            if(mf.isFullScreen()){
                reSizeButton.setIcon(new ImageIcon(".\\resource\\resizeIcon.png"));
            }else{
                reSizeButton.setIcon(new ImageIcon(".\\resource\\resizeIcon2.png"));
            }
            return;
        }

        if (name.equals(menuItemName[1]) || source.equals(closeButton)) {
            Tools.println("'最小化'按钮被单击");
            MainFrame mf = (MainFrame) this.getRootPane().getParent();
            int state = mf.getExtendedState();
            Tools.println(state);
            mf.reState(7);
            return;
        }

        if (name.equals(menuItemName[2])) {
            Tools.println("'最大化'按钮被单击");
            MainFrame mf = (MainFrame) this.getRootPane().getParent();
            mf.reState(JFrame.MAXIMIZED_BOTH);

            if(mf.isFullScreen()){
                reSizeButton.setIcon(new ImageIcon(".\\resource\\resizeIcon2.png"));
            }else{
                reSizeButton.setIcon(new ImageIcon(".\\resource\\resizeIcon.png"));
            }
            return;
        }

        if (name.equals(menuItemName[3])|| source.equals(exitButton)) {
            Tools.println("'关闭'按钮被单击");
            MainFrame mf = (MainFrame) this.getRootPane().getParent();
            mf.exit();
            return;
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component component = e.getComponent();

        if(component.equals(closeButton)){
            closeButton.setBackground(MColor.DARK_WHITE);
            return;
        }

        if(component.equals(reSizeButton)){
            reSizeButton.setBackground(MColor.DARK_WHITE);
            return;
        }

        if(component.equals(exitButton)){
            exitButton.setBackground(MColor.RED);
            return;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
            reColorButton();
    }
}
