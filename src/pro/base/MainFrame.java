package pro.base;

import tools.MColor;
import tools.PropertiesReader;
import tools.Tools;

import javax.swing.*;
import java.awt.*;


/**
 * <p>
 * GUI类的主类, 本类对象构造了程序的主窗口.
 * <p>
 * 实例化这个类的对象会创建一个本项目的窗体. 因此本类可以被称之为"所有项目UI控件的Root"之类的样子
 *
 * @author Maxwell
 */
public class MainFrame extends JFrame {

//    /**
//     * 程序的底层面板, 所有的其他面板都在这个面板之上
//     * 理论上来说这个面板应该会被覆盖的很惨所以没啥必要设置它的一大堆属性
//     * 这是最底层的面板
//     */
//    private JPanel basePanel;

    /**
     * 标题面板. 因为Swing框架会调用系统自带的标题栏, 那个标题栏丑死了. 所以我隐藏了这个标题栏然后自己画一个新的上去
     * 这个标题面板会在底层面板的最顶端
     */
    private TitlePanel titlePanel;

    /**
     * 因为这个项目的名字叫做M"Tools". 因此我决定让这个程序拥有许许多多我日常可能会用到的奇怪的功能, 因此在底层面板的右侧有一个小小的竖着
     * 的面板, 这个面板用于存放不同的功能, 例如"日历"按钮和"记事本"按钮
     */
    private SidePanel sidePanel;

    /**
     * 主面板, 用于承载主要面板控件
     */
    private MainPanel mainPanel;

//    /**
//     * 存储屏幕的宽度
//     */
//    private int screenWidth;
//
//    /**
//     * 存储屏幕的高度
//     */
//    private int screenHeight;

    /**
     * UI目前是否是全屏状态. 用于TitlePanel中控制最大化按钮的行为
     */
    private boolean isFullScreen;

    /**
     * <p>
     * 无参构造函数. 初始化一些简单的玩意
     */
    public MainFrame() {

        this.setTitle(PropertiesReader.get("Title"));

        //设置退出按钮
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //窗体总是在最前端
//        this.setAlwaysOnTop(true);

        //设置非全屏时的窗体大小
        this.setBounds(0, 0, 500, 1100);

        //最大化
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.isFullScreen = true;

        //去边框
        this.setUndecorated(true);

        //用于设置在窗体的边框取消后仍然可以拉伸
        ResizeAdapter resizeAdapter = new ResizeAdapter(this);
        this.addMouseListener(resizeAdapter);
        this.addMouseMotionListener(resizeAdapter);

        //使用绝对布局管理器
        this.setLayout(null);

    }

    /**
     * <p>
     * 用于启动程序的方法. 抄隔壁Spring的启动名称.
     * <p>
     * 这个方法会依次调用每一个主窗体上的控件的初始化方法来完成整个窗体的初始化
     */
    public void run() {

        Tools.println("主窗体初始化");

        //获得屏幕的宽高比
        int[] screenSize = Tools.getScreenSize();

        //写入配置文件
        PropertiesReader.set("ScreenWidth",String.valueOf(screenSize[0]));
        PropertiesReader.set("ScreenHeight", String.valueOf(screenSize[1]));

        //初始化颜色类
        MColor.setColorStyle(Integer.parseInt(PropertiesReader.get("ColorStyle")));

        //初始化字体
        Tools.InitGlobalFont(new Font(PropertiesReader.get("GlobalFont"), Font.PLAIN, 20));

        init();
    }

    /**
     * 为了不让run()方法长得太胖就把它的一部分掰到这里了.
     * <p>
     * 这里是整个窗体初始化的最后一个本类方法... 不会再踢皮球给其他方法了
     * <p>
     * 然后大部分的绘制非本窗体的内容都会在这个方法中被调用.
     */
    private void init() {

        //初始化面板们
        titlePanel = new TitlePanel();
        sidePanel = new SidePanel();
        mainPanel = new MainPanel();

        //重绘面板的颜色
//        rePaint();

        this.add(titlePanel);
        this.add(sidePanel);
        this.add(mainPanel);

        //初始化标题面板
        titlePanel.init();

        //初始化侧栏面板
        sidePanel.init();

        //初始化主面板
        mainPanel.init();

        this.loadData();

        //设置窗口可见性为可见
        this.setVisible(true);


    }


    /**
     * 从配置文件和数据中加载
     */
    private void loadData(){
        loadCard();
    }

    private void loadCard(){

        String name = sidePanel.getFocusButtonName();
        this.switchPanel(name);
    }

    /**
     * 对UI界面上色
     * <p>
     * 从配置文件中调用UI的主题类型来获得不同的颜色集
     */
    private void rePaint() {
        //设置UI配色
        this.updateIsFullScreen();
    }

    /**
     * 更新该窗体是否是全屏状态
     */
    private void updateIsFullScreen(){

        int oriWidth = Integer.parseInt(PropertiesReader.get("ScreenWidth"));
        int oriHeight = Integer.parseInt(PropertiesReader.get("ScreenHeight"));


        //这个表达式大概意思就是如果这个窗体的坐标不在0,0. 或者他的大小和初始化窗体时检测的屏幕大小不一致就将值置为false
        if(this.getX() == 0 && this.getY() == 0 &&
                this.getWidth() == oriWidth &&
                this.getHeight() == oriHeight){
            this.setFullScreen(true);
            return;
        }
        this.setFullScreen(false);

    }

    /**
     * 让子窗体回调用的函数, 用来调整窗口的大小.
     * @param state 设置窗体状态的常量
     * @see JFrame#NORMAL
     */
    public void reState(int state) {
        this.setExtendedState(state);

        if (state == JFrame.NORMAL) {
            this.isFullScreen = false;

        }

        if (state == JFrame.MAXIMIZED_BOTH) {
            this.setLocation(0,0);
            this.isFullScreen = true;
        }

        this.rePaintAll();
    }

    /**
     * 当窗体大小改变时, 通过这个函数修改内部控件的大小
     * <p>
     * 这要求所有子控件都重写repaint方法来重绘他们的大小
     * </p>
     */
    public void rePaintAll() {


        this.rePaint();
        titlePanel.rePaint();
        sidePanel.rePaint();
        mainPanel.rePaint();
    }

    /**
     * 超级难看的方法. 用来让这个窗体中的子类控件MainPanel切换显示
     * 一人提高了耦合度
     * @param panelName 组件的名字
     * @see tools.Constant
     */
    public void switchPanel(String panelName){
        mainPanel.switchPanel(panelName);
    }

    /**
     * 退出程序
     */
    public void exit() {
        Tools.println("退出程序");

        sidePanel.exit();

        System.exit(0);
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean isFullScreen){
        this.isFullScreen = isFullScreen;
    }

    @Override
    public void repaint() {
        this.rePaintAll();
        super.repaint();
    }
}
