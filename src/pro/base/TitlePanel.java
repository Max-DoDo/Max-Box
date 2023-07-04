package pro.base;

import tools.Constant;
import tools.MColor;
import tools.PropertiesReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 标题栏
 * <p>
 *
 * </p>
 */
public class TitlePanel extends JPanel implements ActionListener, MouseListener,MouseMotionListener{

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
    private JButton resizeButton;

    private ImageIcon resizeIcon;

    private ImageIcon resizeIcon2;

    /**
     * 关闭按钮
     */
    private JButton exitButton;

    private ImageIcon exitIcon;

    private ImageIcon exitIcon2;

    /**
     * 设置按钮
     */
    private JButton settingButton;

    private int width;

    private int height;

    /**
     * 鼠标拖动定位用变量
     */
    private int newX,newY,oldX,oldY;

    /**
     * 鼠标拖动定位用变量
     */
    private int startX,startY;


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

        width = Integer.parseInt(PropertiesReader.get("ScreenWidth"));
        height = Constant.TITLE_PANEL_HEIGHT;
        this.setSize(width, height);
        this.setLayout(null);
        this.setBackground(MColor.TITLE_PANEL);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.initIcon();
        this.initButton();
        this.initIconMenu();
        this.initSettingButton();
    }

    public void init(int width, int height){
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setLayout(null);
        this.setBackground(MColor.TITLE_PANEL);

        this.initIcon();
        this.initButton();
        this.initIconMenu();
        this.initSettingButton();
    }

    /**
     * 初始化最左边logo按钮的相关设置
     */
    private void initIcon() {

        Icon icon = new ImageIcon(".\\resource\\titleIcon.png");

        titleIcon = new JButton(icon);
        titleIcon.setLocation(10, 7);
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

        //三个按钮 的高度和宽度是一样的
        int width = this.getHeight() + 10;
        int height = this.getHeight();
        int oriX = this.getWidth() - (3 * width);
        int y = 0;

        resizeIcon = new ImageIcon(".\\resource\\resizeIcon.png");
        resizeIcon2 = new ImageIcon(".\\resource\\resizeIcon2.png");
        exitIcon = new ImageIcon(".\\resource\\exitIcon.png");
        exitIcon2 = new ImageIcon(".\\resource\\exitIcon2.png");

        //设置图标
        Icon Icon = new ImageIcon(".\\resource\\closeIcon.png");
        closeButton = new JButton(Icon);
        resizeButton = new JButton(resizeIcon);
        exitButton = new JButton(exitIcon);

        //设置大小
        closeButton.setSize(width, height);
        resizeButton.setSize(width, height);
        exitButton.setSize(width, height);

        //设置背景颜色
        closeButton.setBackground(MColor.TITLE_PANEL);
        resizeButton.setBackground(MColor.TITLE_PANEL);
        exitButton.setBackground(MColor.TITLE_PANEL);

        //设置位置
        closeButton.setLocation(oriX, y);
        resizeButton.setLocation(oriX + width, y);
        exitButton.setLocation(oriX + (width * 2), y);


        //去掉按钮边框和焦点边框
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        resizeButton.setBorderPainted(false);
        resizeButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);

        //设置监听, 按键监听和鼠标移动的监听
        closeButton.addActionListener(this);
        closeButton.addMouseListener(this);
        resizeButton.addActionListener(this);
        resizeButton.addMouseListener(this);
        exitButton.addActionListener(this);
        exitButton.addMouseListener(this);

        //加到标题面板上
        this.add(closeButton);
        this.add(resizeButton);
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

        //还原按钮有些麻烦而且没啥用处, 暂时先不使用了
        menuItems[0].setEnabled(false);
    }


    /**
     * 初始化设置按钮
     */
    private void initSettingButton() {

        int width = this.getHeight();
        int height = this.getHeight();

        //初始化和设置图标
        settingButton = new JButton(new ImageIcon(".\\resource\\settingIcon.png"));

        //设置背景颜色
        settingButton.setBackground(MColor.TITLE_PANEL);

        //去掉焦点和边框
        settingButton.setBorderPainted(false);
        settingButton.setFocusPainted(false);

        //设置长和宽
        settingButton.setSize(width,height);

        //设置位置
        settingButton.setLocation(closeButton.getX() - width,0);

        //设置监听
        settingButton.addActionListener(this);
        settingButton.addMouseListener(this);

        this.add(settingButton);
    }

    /**
     * 重绘该面板的所有颜色属性
     */

    public void rePaint() {

        rePaintSelf();
        rePaintButton();
    }

    /**
     * 重新设置控件的大小
     */
    public void rePaintSelf() {

        //本体的相关设置
        width = this.getRootPane().getParent().getWidth();
        this.setSize(width, height);

    }

    /**
     * 重绘老三样的颜色,位置, 修改大小按钮的图标
     */
    private void rePaintButton() {


        //设置背景颜色
        closeButton.setBackground(MColor.TITLE_PANEL);
        resizeButton.setBackground(MColor.TITLE_PANEL);
        exitButton.setBackground(MColor.TITLE_PANEL);
        settingButton.setBackground(MColor.TITLE_PANEL);

        //计算位置
        int width = this.getHeight() + 10;
        int oriX = this.getWidth() - (3 * width);
        int y = 0;

        //设置位置
        closeButton.setLocation(oriX, y);
        resizeButton.setLocation(oriX + width, y);
        exitButton.setLocation(oriX + (width * 2), y);
        settingButton.setLocation(oriX - width + 10,y);

        //重绘修改大小按钮的图标
        MainFrame mf = (MainFrame) this.getRootPane().getParent();
        if (mf.isFullScreen()) {
            resizeButton.setIcon(resizeIcon);
        } else {
            resizeButton.setIcon(resizeIcon2);
        }

        //重绘退出按钮的图标
        exitButton.setIcon(exitIcon);
    }

    /**
     * 打开弹出菜单栏
     */
    private void showTitleMenu() {

        titleIconMenu.show(titleIcon, titleIcon.getWidth(), titleIcon.getHeight());
    }

    /**
     * 最小化窗体按钮的实现
     */
    private void closeButtonClick() {

        MainFrame mf = (MainFrame) this.getRootPane().getParent();
        /*
         * 根据查阅到的资料, 在swing的窗体的用于ExtendedState常量中, 用二进制位来表示不同的情况.
         * 其中个位为0表示窗体打开, 1为关闭
         * 因此直接操作这个值 + 1就可以达到最小化的情况.
         * 另外因为最小化后用户一般情况下(至少我没发现)是不能再次单击这个按钮的. 而当用户通过点击应用的图标或者切屏啥的给切回来的时候
         * 这个加上的1因为状态的改变已经被swing自己减去了. 因此目前这样就很完美.
         * (之后遇到bug了再说)
         */
        mf.reState(mf.getExtendedState() + 1);

        //玄学操作
        mf.setVisible(false);
        mf.setVisible(true);

        mf.repaint();
    }

    /**
     * 调整窗口大小按钮的实现
     */
    private void reSizeButtonClick() {

        //获得主框架的引用
        MainFrame mf = (MainFrame) this.getRootPane().getParent();

        if(mf.isFullScreen()){
            System.out.println("to normal");
            mf.reState(JFrame.NORMAL);
        }else{
            System.out.println("to max");
            mf.reState(JFrame.MAXIMIZED_BOTH);
        }

    }

    /**
     * 退出按钮的单击事件相应方法.
     * <p>
     *     把皮球踢给主框架那边让它处理和退出相关的事情
     */
    private void exitButtonClick(){
        MainFrame mf = (MainFrame) this.getRootPane().getParent();
        mf.exit();
    }

    /**
     * 设置按钮的单击事件相应方法
     */
    private void settingButtonClick(){

    }

//    下面是源自接口的重写方法

    /**
     * 重写源自接口的方法来实现对控件的监听
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        String name = e.getActionCommand();

        //标题logo
        if (source.equals(titleIcon)) {
            showTitleMenu();
            return;
        }

        //修改大小按钮
        if (source.equals(resizeButton)) {
            reSizeButtonClick();
            return;
        }

        //最小化按钮和弹出菜单最小化选项
        if (name.equals(menuItemName[1]) || source.equals(closeButton)) {
            closeButtonClick();
            return;
        }

        //弹出菜单最大化按钮
        if (name.equals(menuItemName[2])) {
            MainFrame mf = (MainFrame) this.getRootPane().getParent();
            mf.reState(JFrame.MAXIMIZED_BOTH);
            rePaint();
            return;
        }

        //弹出菜单退出选项和退出按钮
        if (name.equals(menuItemName[3]) || source.equals(exitButton)) {
            exitButtonClick();
            return;
        }

        if(source.equals(settingButton)){
            settingButtonClick();
            return;
        }


    }

    /**
     * 重写源自接口的方法
     * <p>
     * 在这个方法中主要负责监听老三样的鼠标进入事件, 当鼠标进入组件中时切换颜色或者切换icon
     * </p>
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        Component component = e.getComponent();

        if (component.equals(closeButton)) {
                closeButton.setBackground(MColor.BUTTON_SELECTING);
            return;
        }

        if (component.equals(resizeButton)) {
            resizeButton.setBackground(MColor.BUTTON_SELECTING);
            return;
        }

        if (component.equals(exitButton)) {
            exitButton.setBackground(new Color(232,17,35));
            exitButton.setIcon(exitIcon2);
            return;
        }

        if(component.equals(settingButton)){
            settingButton.setBackground(MColor.BUTTON_SELECTING);
            return;
        }

    }

    /**
     * 对鼠标点下去的事件进行监听的主要目的是重绘退出按钮
     * <p>
     *     正常情况下操作一个退出按钮的流程是:
     *     鼠标拖到它身上->按钮变红->鼠标点下去->按钮回复原样->鼠标松开->执行关闭的操作
     * <p>
     *     事实上每个按钮都应该在鼠标点下去的时候拥有另外一个icon. 但是实际情况是swing拥有自己的一个点击动画
     *     而且勉勉强强不算丑, 可以看得过去. 因此只需要把这个按钮X号附近发红的边框给换成原先灰色的样子就好了
     * <p>
     *     感觉为了一点无关紧要的小事增加了很多开销呢...算了反正我也用不完
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

        Object source = e.getSource();

        if(source.equals(exitButton)){
            exitButton.setIcon(exitIcon);
            return;
        }

        if(source.equals(this)){
            Component cp = (Component)source;
            MainFrame mf = (MainFrame) this.getRootPane().getParent();
            //当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
            startX = mf.getX();
            startY = mf.getY();
            oldX = e.getXOnScreen();
            oldY = e.getYOnScreen();
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        rePaintButton();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * 通过拖动鼠标来更改窗体的位置
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Component cp = (Component)e.getSource();

        if(cp.equals(this)){

            MainFrame mf = (MainFrame) this.getRootPane().getParent();

            //拖动的时候记录新坐标
            newX = e.getXOnScreen();
            newY = e.getYOnScreen();
            //设置bounds,将点下时记录的组件开始坐标与鼠标拖动的距离相加
            mf.setLocation(startX+(newX - oldX),startY+(newY - oldY));
            mf.setFullScreen(false);

            this.rePaint();

        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
