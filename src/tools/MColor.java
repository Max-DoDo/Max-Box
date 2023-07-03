package tools;

import java.awt.Color;

/**
 * <p>
 * 本类用于存储UI所用到的颜色
 * 
 * @author Maxwell
 */
public class MColor {

    public static final int LIGHT_STYLE = 0;
    public static final int DARK_STYLE = 1;
    public static final int USER_DEFINE_STYLE = -1;

    /**
     * 存储当前的颜色模式
     */
    public static int colorStyle;

//===========================================================================

    /**
     * 分割线颜色
     */
    public static Color LINE;

    /**
     * 主面板颜色
     */
    public static Color MAIN_PANEL;

    /**
     * 标题栏颜色
     */
    public static Color TITLE_PANEL;

    /**
     * 侧边功能栏颜色
     */
    public static Color SIDE_PANEL;

    /**
     * 重要按钮颜色
     */
    public static Color SPC_BUTTON_NORMAL;

    /**
     * 普通按钮选中颜色
     */
    public static Color BUTTON_SELECTING;

    /**
     * 普通按钮点击时颜色
     */
    public static Color BUTTON_PRESSING;

    /**
     * 今天及以后日期格子正常颜色
     */
    public static Color CB_FUTURE;

    /**
     * 今天以前日期格子正常颜色
     */
    public static Color CB_PASSED;

    /**
     * 被选择的日期格子颜色
     */
    public static Color CB_S;

    /**
     * 正在点击的今天及以后日期格子颜色
     */
    public static Color CB_FUTURE_P;

    /**
     * 正在点击的今天之前的日期格子颜色
     */
    public static Color CB_PASSED_P;

    public static Color CB_HIGHLIGHT;

    /**
     * 字体颜色
     */
    public static Color FONT;


    /**
     * 设置颜色模式. 这将会影响所有静态颜色的值
     * @param style 颜色模式常数. 详见{@link MColor#colorStyle}
     */
    public static void setColorStyle(int style){

        colorStyle = style;

        switch (style) {
            case DARK_STYLE -> darkStyle();
            case LIGHT_STYLE -> lightStyle();
            case USER_DEFINE_STYLE -> userDefineStyle();
        }
    }

    private static void darkStyle() {
        LINE = new Color(82, 82, 82);
        FONT = new Color(214, 214, 214);

        MAIN_PANEL = new Color(100, 100, 100);
        TITLE_PANEL = new Color(10, 10, 10);
        SIDE_PANEL = new Color(10, 10, 10);

        SPC_BUTTON_NORMAL = new Color(71, 158, 245);
        BUTTON_SELECTING = new Color(46, 46, 46);
        BUTTON_PRESSING = new Color(8, 35, 56);

        //日历
        CB_FUTURE = new Color(41, 41, 41);
        CB_PASSED = new Color(31, 31, 31);

        CB_S = new Color(8, 35, 56);
        CB_FUTURE_P = new Color(61, 61, 61);
        CB_PASSED_P = new Color(0, 0, 0);

        CB_HIGHLIGHT = new Color(71, 158, 245);
    }

    private static void lightStyle() {
        LINE = new Color(224, 224, 224);
        FONT = new Color(66,66,66);

        MAIN_PANEL = new Color(250, 250, 250);
        TITLE_PANEL = new Color(240, 240, 240);
        SIDE_PANEL = new Color(240, 240, 240);

        SPC_BUTTON_NORMAL = new Color(15, 108, 189);
        BUTTON_SELECTING = new Color(235, 235, 235);
        BUTTON_PRESSING = new Color(207, 228, 250);

        //日历
        CB_FUTURE = new Color(255,255,255);
        CB_PASSED = new Color(250,250,250);

        CB_S = new Color(207, 228, 250);
        CB_FUTURE_P = new Color(240, 240, 240);
        CB_PASSED_P = new Color(235, 235, 235);

        CB_HIGHLIGHT = new Color(15, 108, 189);

    }

    private static void userDefineStyle() {

    }


    
}
