package tools;

import javax.swing.border.Border;
import java.awt.*;

/**
 * <h2>
 * 边界类, 用于绘制圆角矩形的边界
 * </h2>
 * <p>
 * 构造本类对象可以让swing控件的边界被绘制为圆角矩形
 * </p>
 *
 * @author Max
 * @date 2023/07/02 17:10
 **/
public class RoundBorder implements Border {

    /**
     * 从接口重写的方法. 用于绘制圆角矩形
     *
     * @param c      the component for which this border is being painted
     * @param g      the paint graphics
     * @param x      the x position of the painted border
     * @param y      the y position of the painted border
     * @param width  the width of the painted border
     * @param height the height of the painted border
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        //设置边框颜色
        g.setColor(MColor.LINE);
        g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 5, 5);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
