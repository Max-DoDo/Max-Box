package pro.calendar.ui;

import pro.calendar.al.Time;
import tools.MColor;
import tools.Tools;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <h2>
 *      日期格子中展示当前日期的按钮/或者可以理解为标签
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/03 15:16
 **/
public class GridTextButton extends JButton implements MouseListener {

    /**
     * 本控件所在父类控件
     */
    DateGrid father;

    /**
     * 这个标签是否显示月份
     */
    boolean isShowMonth;

    /**
     * 本类对象所代表的时间
     */
    Time time;


    public GridTextButton(DateGrid father, Time time, boolean isShowMonth){

        this.father = father;

        this.time = time;

        this.isShowMonth = isShowMonth;

        //设置字体靠左
        this.setHorizontalAlignment(JButton.LEFT);

        //设置字体颜色
        this.setForeground(MColor.FONT);

        this.setBorder(null);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);

        this.setText();

        this.addMouseListener(this);
    }


    public void setText(){

        if(time == null){
            return;
        }

        if(isShowMonth){
            this.setText(Tools.format_MD(time.getMonth(),time.getDay()));
        }else{
            this.setText(Tools.format_D(time.getDay()));
        }
    }

    public void setTime(Time time){
        this.time = time;
        this.setText();
    }

    /**
     * 是否显示月份, 这会同步更新UI上的内容显示
     * @param isShowMonth true: 显示月份 false: 不显示月份
     */
    public void setShowMonth(boolean isShowMonth){
        this.isShowMonth = isShowMonth;
        this.setText();
    }

    @Override
    public String toString() {

        if(time == null){
            return "null Object";
        }

        return Tools.format_MD(time.getMonth(),time.getDay());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        father.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        father.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        father.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        father.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        father.mouseExited(e);
    }
}
