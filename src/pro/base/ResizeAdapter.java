package pro.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <h2>
 * 窗体的拉伸等操作的实现类
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/04 17:57
 **/

public class ResizeAdapter extends MouseAdapter {

    //super swing
    public JFrame jf;

    //鼠标位置的屏幕坐标
    private Point scPos;

//    窗体的坐标
    private Point jfPos;

    //鼠标在窗体中的坐标
    private Point msPos;

    private int moveX;

    private int moveY;

    private int jfWidth;

    private int jfHeight;

    private static final double RANGE = 5.0;//边界拉伸范围


    private int dragType;

    private static final int DRAG_NONE = 0;

    //上下左右
    private static final int DRAG_U = 1;

    private static final int DRAG_B = 2;

    private static final int DRAG_L = 4;

    private static final int DRAG_R = 8;

    //左上 右上 左下 右下

    private static final int DRAG_LU = 5;

    private static final int DRAG_RU = 9;

    private static final int DRAG_LB = 6;

    private static final int DRAG_RB = 10;


    public ResizeAdapter(JFrame jf) {
        this.jf = jf;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        this.updateValue(e);
        moveX = scPos.x;
        moveY = scPos.y;

    }

    /**
     * 判断鼠标要从哪个方向开始拖动
     */
    private void updateType() {

        double up = msPos.getY();
        double bottom = jfHeight - msPos.getY();
        double left = msPos.getX();
        double right = jfWidth - msPos.getX();

        //左上
        if (left < RANGE && up < RANGE) {
            dragType = DRAG_LU;
            return;
        }

        //右上
        if (right < RANGE && up < RANGE) {
            dragType = DRAG_RU;
            return;
        }

        //左下
        if (left < RANGE && bottom < RANGE) {
            dragType = DRAG_LU;
            return;
        }

        //右下
        if (right < RANGE && bottom < RANGE) {
            dragType = DRAG_RB;
            return;
        }

        //上
        if (up < RANGE) {
            dragType = DRAG_U;
            return;
        }

        //下
        if (bottom < RANGE) {
            dragType = DRAG_B;
            return;
        }

        //左
        if (left < RANGE) {
            dragType = DRAG_L;
            return;
        }

        //右
        if (right < RANGE) {
            dragType = DRAG_R;
            return;
        }

        //没有类型, 用户没想着拖动
        dragType = DRAG_NONE;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        this.dragAction();
        this.updateValue(e);
    }

    private void dragAction() {

        int x = jf.getX();
        int y = jf.getY();
        int width = jf.getWidth();
        int height = jf.getHeight();

        //change in value x and y
        int cX = moveX - scPos.x;
        int cY = moveY -scPos.y;

        switch (dragType) {

            case DRAG_NONE -> {
                return;
            }
            case DRAG_U -> {
                height += cY;
                y -= cY;
                break;
            }
            case DRAG_B -> {
                height -= cY;
                break;
            }
            case DRAG_L -> {
                x -= cX;
                width += cX;
                break;
            }
            case DRAG_R -> {
                width -= cX;
            }

        }

        moveX = scPos.x;
        moveY = scPos.y;
        jf.setBounds(x,y,width,height);
        jf.repaint();
    }

    private void updateValue(MouseEvent e){
        scPos = e.getLocationOnScreen();
        jfPos = jf.getLocation();
        msPos = e.getPoint();

        jfWidth = jf.getWidth();
        jfHeight = jf.getHeight();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.updateValue(e);
        this.updateType();
        this.updateCursor();
    }

    private void updateCursor(){

        if(dragType == DRAG_NONE){

            //没有拖动迹象设置回默认鼠标
            jf.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        }else if(dragType == DRAG_L || dragType == DRAG_R){

            //左右拖动
            jf.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));

        }else if(dragType == DRAG_U || dragType == DRAG_B){

            //上下拖动
            jf.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
        }
    }
}
