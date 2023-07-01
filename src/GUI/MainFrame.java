package GUI;

import Tools.MColor;
import Tools.PropertiesReader;
import Tools.Tools;

import javax.swing.*;


/**
 * <p>
 * GUI类的主类, 本类对象构造了程序的主窗口
 *
 * @author Maxwell
 */
public class MainFrame extends JFrame {

    /**
     * 程序的底层面板, 所有的其他面板都在这个面板之上
     * 理论上来说这个面板应该会被覆盖的很惨所以没啥必要设置它的一大堆属性
     * 这是最底层的面板
     */
    private JPanel basePanel;

    /**
     * 标题面板. 因为Swing框架会调用系统自带的标题栏, 那个标题栏丑死了. 所以我隐藏了这个标题栏然后自己画一个新的上去
     * 这个标题面板会在底层面板的最顶端
     */
    private TitlePanel titlePanel;

    /**
     * 因为这个项目的名字叫做M"Tools". 因此我决定让这个程序拥有许许多多我日常可能会用到的奇怪的功能, 因此在底层面板的右侧有一个小小的竖着
     * 的面板, 这个面板用于存放不同的功能, 例如"日历"按钮和"记事本"按钮
     */
    private JPanel functionPanel;

    /**
     * 主面板, 用于承载主要面板控件
     */
    private JPanel mainPanel;

    private int screenWidth;

    private int screenHeight;

    private boolean isFullScreen;


    /**
     * <p>
     * 无参构造函数
     */
    public MainFrame() {

        //初始化成员变量


        //初始化面板们
        basePanel = (JPanel) this.getContentPane();
        titlePanel = new TitlePanel();
        functionPanel = new JPanel();
        mainPanel = new JPanel();

    }

    /**
     * <p>
     * 用于启动程序的方法
     */
    public void run() {

        //获得屏幕的宽高比
        int[] screenSize = Tools.getScreenSize();

        //初始化颜色类
        MColor.setColorStyle(Integer.parseInt(PropertiesReader.get("ColorStyle")));
        screenWidth = screenSize[0];
        screenHeight = screenSize[1];
        init();
    }

    private void init() {

        //重绘面板的颜色
        reColor();

        //初始化主窗体框架
        initMainFrame();

        //初始化标题面板
        titlePanel.init();
        this.add(titlePanel);

        //设置窗口可见性为可见
        this.setVisible(true);


    }

    private void initMainFrame() {
        this.setTitle(PropertiesReader.get("Title"));

        //设置退出按钮
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //窗体总是在最前端
//        this.setAlwaysOnTop(true);

        this.setBounds(0, 0, 400, 1100);

        //最大化
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.isFullScreen = true;

        //去边框
        this.setUndecorated(true);

        //使用绝对布局管理器
        this.setLayout(null);

    }

    /**
     * 对UI界面上色
     * <p>
     * 从配置文件中调用UI的主题类型来获得不同的颜色集
     */
    private void reColor() {
        //设置UI配色

        basePanel.setBackground(MColor.MAIN_PANEL);
        titlePanel.setBackground(MColor.SIDE_PANEL);
        functionPanel.setBackground(MColor.SIDE_PANEL);
        mainPanel.setBackground(MColor.MAIN_PANEL);

    }

    /**
     * 设置
     */
    public void reState(int state) {
        this.setExtendedState(state);

        if (state == JFrame.NORMAL) {
            this.isFullScreen = false;
        }

        if (state == JFrame.MAXIMIZED_BOTH) {
            this.isFullScreen = true;
        }

        resizeAll();
        Tools.println("修改窗体大小");

    }

    /**
     * 当窗体大小改变时, 通过这个函数修改内部控件的大小
     * <p>
     * 这要求所有子控件都重写repaint方法来重绘他们的大小
     * </p>
     */
    public void resizeAll() {
        titlePanel.reSize();
    }

    /**
     * 退出程序
     */
    public void exit() {
        Tools.println("退出程序");
        System.exit(0);
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }
}
