package pro.calendar.ui;

import tools.MColor;

import javax.swing.border.Border;
import java.awt.*;

/**
 * <h2>
 *
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/05 23:50
 **/
public class PanelBorder implements Border {

    /**
     * 周围四条线的边框
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        g.setColor(MColor.LINE);

        g.drawLine(0,0,c.getWidth(),0);
        g.drawLine(c.getWidth(),0,c.getWidth(),c.getHeight());

        g.drawLine(c.getWidth(),c.getHeight(),0,c.getHeight());
        g.drawLine(0,c.getHeight(),0,0);
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
