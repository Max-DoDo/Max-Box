package pro.base;

import tools.MColor;

import javax.swing.*;
import java.awt.*;

/**
 * <h2>
 *  圆角矩形按钮
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/02 18:01
 **/
public class RoundButton extends JButton{

    public RoundButton(ImageIcon s)
    {
        super(s);
        this.setBackground(MColor.SIDE_PANEL);
        this.setBorder(new RoundBorder());//圆角矩形边界
        this.setContentAreaFilled(false);//取消原先画矩形的设置
        this.setFocusPainted(false);//去除文字周围的虚线框

    }

    /**
     * 重写的源自超类的方法
     * <p>
     *     这个方法我也是第一次用, 实际上这段代码都是从网上粘下来的自己改了改
     * <p>
     *     这是我的理解. 根据我的测试, 这个方法首先会自动的被调用(甚至调用两次我也不知道为啥), 是控件初始化的一部分.
     *     其次, 每当按钮的状态发生了改变, 例如我的鼠标挪了进去或者挪了出来或者点他或者松开点他等等很多动作.
     *     都会自动的调用这个方法, 所以这个方法大概是一个用来重绘这个按钮的很底层的一个方法.
     * <p>
     *     然后学到了一个新的玩意. Button组件有一个玩意叫getModel. 我简单看了一下源码(虽然没怎么看懂)
     *     这个东西是一个用来表示这个控件当前状态的对象? 大概可以这么理解
     *     他有一个boolean属性叫isArmed, 每当按钮被点下去但没有松开的时候这个值会设为true.
     *     所以可以通过这个方法来在它重绘的时候加一些我想干的事情.
     * <p>
     *     这个方法通过重写源自超类的方法来在按钮被点击的时候画一个和它的边框外观相同的实心矩形来让UI更有操作感
     * <p>
     *     至于为什么要把超类的方法放在最后调用我没看明白, 试了一下放在最前面会画不出东西.
     *     如果我写的这些玩意真的被人看见了的话我很荣幸能听到上述问题的答案. 提前谢谢你!
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {

        // 按钮被点了下去, 但又不是被点了下去
        //在这里设置不同的画笔颜色
        if (this.getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(this.getBackground());
        }

        //填充圆角矩形边界
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,5,5);

        super.paintComponent(g);
    }


}
