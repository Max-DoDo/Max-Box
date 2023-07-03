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
 * @date 2023/07/04 00:48
 **/
public class HighLightBorder implements Border {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        //从隔壁GridBorder抄来的代码

        g.setColor(MColor.LINE);

        //这个graphics好像不能修改画笔的粗细... 查了一下需要用什么2d什么的. 我就画根线还要这么麻烦吗
        //我tm直接画两根然后合体!
        g.drawLine(0,0,c.getWidth(),0);
        g.drawLine(0,1,c.getWidth(),1);

        g.drawLine(0,c.getHeight(),c.getWidth(),c.getHeight());
        g.drawLine(0,c.getHeight() - 1,c.getWidth(),c.getHeight() - 1);

        //这是自己的代码
        g.setColor(MColor.CB_HIGHLIGHT);
        g.fillRect(0,2,c.getWidth(),5);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        Insets insets = new Insets(0, 0, 0, 0);
        return insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
