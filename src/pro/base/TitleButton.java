package pro.base;

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
 * @date 2023/07/06 18:29
 **/
public class TitleButton extends JButton{

    private Color pressingColor;

    private Color rolloverColor;




    public TitleButton() {
        init();

    }

    public TitleButton(Icon icon) {
        super(icon);
        init();
    }

    public TitleButton(String text) {
        super(text);
        init();
    }

    private void init() {

        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

    }

    @Override
    protected void paintComponent(Graphics g) {

        ButtonModel model = this.getModel();

        if (model.isArmed()) {
            if(pressingColor != null){
                g.setColor(pressingColor);
            }else{
                g.setColor(MColor.BUTTON_PRESSING1);
            }
        } else if(model.isRollover()){

            if(rolloverColor != null){
                g.setColor(rolloverColor);
            }else{
                g.setColor(MColor.BUTTON_ROLLOVER1);
            }

        }else{
            g.setColor(this.getBackground());

        }
        //填充圆角矩形边界
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        super.paintComponent(g);
    }

    public Color getPressingColor() {
        return pressingColor;
    }

    public void setPressingColor(Color pressingColor) {
        this.pressingColor = pressingColor;
    }

    public Color getRolloverColor() {
        return rolloverColor;
    }

    public void setRolloverColor(Color rolloverColor) {
        this.rolloverColor = rolloverColor;
    }
}
