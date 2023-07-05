import pro.base.MainFrame;
import pro.calendar.ui.EventFrame;
import tools.MColor;
import tools.PropertiesReader;
import tools.Tools;

import java.awt.*;

/**
 * <p> 
 * 这个类是程序的主类, 只用于存放主方法. 
 * <p>
 * 这是面向对象一辈子的耻辱
 *  @author Maxwell
 */
public final class MyMain {

    /**
     * <p> 
     *  私有化的构造方法, 用于防止构造本类对象. 这个类的对象没一点用
     */    
    private MyMain(){}

    /**
     * <p> 
     *  主方法, 启动程序的GUI
     */
    public static void main(String[] args){
        setting();
        new MainFrame();

        //TODO TEST
        new EventFrame();
    }

    private static void setting(){
        //获得屏幕的宽高比
        int[] screenSize = Tools.getScreenSize();

        //写入配置文件
        PropertiesReader.set("ScreenWidth",String.valueOf(screenSize[0]));
        PropertiesReader.set("ScreenHeight", String.valueOf(screenSize[1]));

        //初始化颜色类
        MColor.setColorStyle(Integer.parseInt(PropertiesReader.get("ColorStyle")));

        //初始化字体
        Tools.InitGlobalFont(new Font(PropertiesReader.get("GlobalFont"),
                Font.PLAIN,
                Integer.parseInt(PropertiesReader.get("FontSize"))));
    }

}
