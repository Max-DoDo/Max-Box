package pro.calendar.ui;

import tools.MColor;

import javax.swing.*;
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
 * @date 2023/07/06 17:30
 **/
public class CalButton extends JButton {

    public CalButton() {
        init();
    }

    public CalButton(Icon icon) {
        super(icon);
        init();

    }

    public CalButton(String text) {
        super(text);
        init();

    }

    private void init(){
        this.setFocusPainted(false);
        this.setBackground(MColor.SIDE_PANEL);
        this.setForeground(MColor.FONT);
        this.setContentAreaFilled(false);
        this.setBorder(new PanelBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {

        ButtonModel model = this.getModel();

        if (model.isArmed()) {
            g.setColor(MColor.BUTTON_PRESSING1);
        } else if(model.isRollover()){
            g.setColor(MColor.BUTTON_ROLLOVER1);
        }else{
            g.setColor(this.getBackground());

        }

        //填充圆角矩形边界
        g.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1,5,5);

        super.paintComponent(g);
;


    }
}
