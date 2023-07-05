package pro.base;

import tools.Constant;

import javax.swing.*;

/**
 * <h2>
 *
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/04 21:41
 **/
public class SuperCPanel extends JPanel {


    public void rePaint(){
        updateSize();
    }

    public void updateSize(){
        MFrame mf = (MFrame) this.getRootPane().getParent();
        int width = mf.getWidth() - Constant.SIDE_PANEL_WIDTH;
        int height = mf.getHeight() - Constant.TITLE_PANEL_HEIGHT;

        this.setSize(width,height);
    }
}
