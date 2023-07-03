package pro.calendar.ui;

import pro.calendar.al.Event;
import pro.calendar.al.Time;
import tools.MColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>
 *      主面板上日历格子的实现类.
 * </h2>
 * <p>
 *      本类的每一个对象都是一个日历格子的UI, 代表了某一天, 并且拥有相对应的功能. 它是日历格子的最小单元.
 * </p>
 *
 * @author Max
 * @date 2023/07/01 22:16
 **/
public final class DateGrid extends JPanel implements MouseListener {

    /**
     * 这个格子包含的事件
     */
    private List<Event> events;

    /**
     * 顶部显示当前日期的按钮
     */
    private GridTextButton textButton;

    /**
     * 日期时间的抽象
     */
    private Time time;

    /**
     * 格子是否展示的是今天之前的日期
     */
    private boolean isPasted;

    /**
     * 格子顶部是否显示月份
     */
    private boolean isShowMonth;

    /**
     * 无参构造函数, 需要在稍后指定参数
     */
    public DateGrid(){
        init();
    }

    /**
     * 通过该格子是否是一个本月的格子来创建新的本类对象
     */
    public DateGrid(Time time,boolean isPasted){
        //初始化成员变量们
        this.time = time;
        this.isPasted = isPasted;
        init();

    }

    private void init(){
        this.isShowMonth = false;
        events = new ArrayList<>();

        this.setLayout(null);
        this.setBorder(new GridBorder());
        this.addMouseListener(this);

        this.initTitle();
        this.reColorThis();
    }

    /**
     * 初始化标题按钮
     */
    private void initTitle(){

        textButton = new GridTextButton(this,time,isShowMonth);

        textButton.setLocation(0,0);
        textButton.setSize(this.getWidth()+ 10,100);

        this.add(textButton);
    }

    /**
     * 重绘方法. 本类对象的重绘方法关注两个细节:
     * <p>
     *     1. 对象是否是当月的格子, 用于重绘其背景颜色
     * <p>
     *     2. 是否顶部日期显示
     * <p>
     *
     */
    public void rePaint(){

        this.reColor();
        this.drawTitle();

    }

    private void reColor(){

        reColorThis();
    }

    private void drawTitle(){
        textButton.setTime(this.time);
    }

    private void reColorThis(){
        if(isPasted){
            this.setBackground(MColor.CAL_BUTTON_ENABLE);
        }else{
            this.setBackground(MColor.CAL_BUTTON_DISABLE);
        }
    }

    /**
     * 鼠标单击本面板的实现方法
     */
    public void click(){
        this.reColor();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(MColor.BUTTON_PRESSING);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.click();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(MColor.BUTTON_SELECTING);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.reColor();
    }

    @Override
    protected void paintComponent(Graphics g) {
        textButton.setSize(this.getWidth(),this.getHeight()/6);
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public void setTime(Time time) {
        this.time = time;
        this.drawTitle();
    }
    private void setShowMonth(boolean isShowMonth){
        this.isShowMonth = isShowMonth;
        this.rePaint();
    }

    private boolean isShowMonth(){
        return isShowMonth;
    }

    public Time getTime() {
        return time;
    }

    public boolean isPasted() {
        return isPasted;
    }

    public void setPasted(boolean pasted) {
        isPasted = pasted;
        reColor();
    }
}
