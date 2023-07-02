

import pro.MainFrame;

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
        new MainFrame().run();
    }

    
}
