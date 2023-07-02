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

    public final static Color BLACK = new Color(10, 10, 10);
    public final static Color LIGHT_BLACK = new Color(31, 31, 31);

    public final static Color DARK_GRAY = new Color(100, 100, 100);
    public final static Color GRAY = new Color(167, 171, 175);
    public final static Color LIGHT_GRAY = new Color(200, 200, 200);

    public final static Color GHOST_WHITE = new Color(240, 240, 240);
    public final static Color WHITE = new Color(255, 255, 255);
    public final static Color DARK_WHITE = new Color(250, 250, 250);

    public final static Color GREEN = new Color(82, 196, 26);
    public final static Color LIGHT_GREEN = new Color(183, 235, 143);
    public final static Color WHITE_GREEN = new Color(246, 255, 237);

    public final static Color ORANGE = new Color(250, 173, 20);
    public final static Color LIGHT_ORANGE = new Color(255, 229, 143);
    public final static Color WHITE_ORANGE = new Color(255, 251, 230);

    public final static Color RED = new Color(232, 17, 35);
    public final static Color LIGHT_RED = new Color(255, 163, 158);
    public final static Color WHITE_RED = new Color(255, 241, 240);

    public final static Color BLUE = new Color(24, 144, 255);
    public final static Color LIGHT_BLUE = new Color(145, 213, 255);
    public final static Color WHITE_BLUE = new Color(230, 247, 255);

    /**
     * 存储当前的颜色模式
     */
    public static int colorStyle;

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
     * 分割线颜色
     */
    public static Color LINE;

    /**
     * 按钮颜色
     */
    public static Color BUTTON_NORMAL;

    /**
     * 按钮选中颜色
     */
    public static Color BUTTON_SELECTING;

    /**
     * 按钮点击时颜色
     */
    public static Color BUTTON_PRESSING;


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
        MAIN_PANEL = new Color(100,100,100);
        TITLE_PANEL = BLACK;
        SIDE_PANEL = BLACK;
        LINE = new Color(82, 82, 82);
        BUTTON_NORMAL = new Color(71, 158, 245);
        BUTTON_SELECTING = Color.BLACK;
        BUTTON_PRESSING = new Color(8, 35, 56);

    }

    private static void lightStyle() {
        MAIN_PANEL = DARK_WHITE;
        TITLE_PANEL = GHOST_WHITE;
        SIDE_PANEL = GHOST_WHITE;
        LINE = new Color(224, 224, 224);
        BUTTON_NORMAL = new Color(15, 108, 189);
        BUTTON_SELECTING = new Color(235, 235, 235);
        BUTTON_PRESSING = new Color(207, 228, 250);

    }

    private static void userDefineStyle() {

    }


    
}
