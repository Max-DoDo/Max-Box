package tools;

import javax.swing.border.Border;
import java.awt.*;

public class TextAreaBorder implements Border {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        g.setColor(MColor.LINE);

        g.drawLine(0,c.getHeight() - 1,c.getWidth(),c.getHeight() - 1);
        g.drawLine(0,c.getHeight(),c.getWidth(),c.getHeight());

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
