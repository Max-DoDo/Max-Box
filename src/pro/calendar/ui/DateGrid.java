package pro.calendar.ui;

import pro.calendar.al.Event;
import pro.calendar.al.Time;
import pro.calendar.eventui.EventFrame;
import tools.MColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>
 * 主面板上日历格子的实现类.
 * </h2>
 * <p>
 * 本类的每一个对象都是一个日历格子的UI, 代表了某一天, 并且拥有相对应的功能. 它是日历格子的最小单元.
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
     * 上层控件的引用
     */
    private MainCalPanel father;

    /**
     * 格子是否展示的是今天之前的日期
     */
    private boolean isPasted;

    /**
     * 格子顶部是否显示月份
     */
    private boolean isShowMonth;

    /**
     * 格子顶部是否显示年份
     */
    private boolean isShowYear;

    /**
     * 格子是否高亮显示
     */
    private boolean isHighLight;

    /**
     * 当前鼠标指针是否指在按钮上
     */
    private boolean isSelecting;

    /**
     * 当前按钮是否被选中. 用于高亮按钮和预备创建事件
     */
    private boolean isSelected;

    /**
     * 当前按钮是否被点击过两次. 用于创建一个新的事件
     */
    private boolean isPreSelected;

    /**
     * 当前鼠标是否正在点击着按钮, 用于更改UI配色
     */
    private boolean isPressing;

    /**
     * 无参构造函数, 需要在稍后指定参数
     */
    public DateGrid() {
        init();
    }

    /**
     * 通过指定上层组件来构造本类对象. 上层组件用于在某些情况下传递信息给其他同级组件(没找到更好的方法了)
     * @param father 上层组件的引用
     */
    public DateGrid(MainCalPanel father){
        this.father = father;
        init();
    }

    /**
     * 通过该格子是否是一个本月的格子来创建新的本类对象
     */
    public DateGrid(Time time, boolean isPasted) {
        //初始化成员变量们
        this.time = time;
        this.isPasted = isPasted;
        init();

    }

    /**
     * 初始化的方法. 设置一些基本的参数和实例化一些成员变量
     */
    private void init() {

        events = new ArrayList<>();

        this.setLayout(null);
        this.setBorder(new GridBorder());
        this.addMouseListener(this);

        this.initTitle();
        this.rePaint();
    }

    /**
     * 初始化标题按钮
     */
    private void initTitle() {

        textButton = new GridTextButton(this, time);

        textButton.setLocation(0, 20);
        textButton.setSize(this.getWidth() + 10, 100);

        this.add(textButton);
    }

    /**
     * 重绘方法. 本类对象的重绘方法关注两个细节:
     * <p>
     * 1. 对象是否是当月的格子, 用于重绘其背景颜色
     * <p>
     * 2. 是否顶部日期显示
     * <p>
     */
    public void rePaint() {
        this.rePaintThis();
        this.drawTitle();
    }

    /**
     * 重绘在不同情况下的控件的颜色显示
     */
    private void rePaintThis() {

        if(isHighLight){
            this.setBorder(new HighLightBorder());
        }else{
            this.setBorder(new GridBorder());
        }

        if(isPreSelected){
            this.setBackground(MColor.CB_S);
            return;
        }

        if(isPressing){
            if(isPasted){
                this.setBackground(MColor.CB_PASSED_P);
            }else{
                this.setBackground(MColor.CB_FUTURE_P);
            }
        }else{
            if(isPasted){
                this.setBackground(MColor.CB_PASSED);
            }else{
                this.setBackground(MColor.CB_FUTURE);
            }
        }
    }

    /**
     * 鼠标单击本面板的实现方法
     */
    public void click() {
        if(this.isPreSelected){
            //点击过两次
            new EventFrame();
        }else{
            //点击了一次
            this.isPreSelected = true;
            this.father.updateSelected(this);
        }
        this.rePaint();
    }

    /**
     * 设置时间. 这个方法会自动在设置时间后重绘该控件, 以此标题栏上显示的内容
     * @param time 时间的抽象实例
     */
    public void setTime(Time time) {
        this.time = time;
        this.rePaint();
    }

    private void drawTitle() {
        textButton.setTime(this.time);
    }

//===================================get和set方法=======================================
    public void setShowMonth(boolean isShowMonth) {
        this.isShowMonth = isShowMonth;
        this.textButton.setShowMonth(isShowMonth);
    }

    private boolean isShowMonth() {
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
        rePaintThis();
    }

    public boolean isHighLight() {
        return isHighLight;
    }

    public void setHighLight(boolean highLight) {
        isHighLight = highLight;
        this.rePaint();
    }

    public MainCalPanel getFather() {
        return father;
    }

    public void setFather(MainCalPanel father) {
        this.father = father;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        this.rePaint();
    }

    public boolean isPreSelected() {
        return isPreSelected;
    }

    public void setPreSelected(boolean preSelected) {
        isPreSelected = preSelected;
        this.rePaint();
    }

    public boolean isShowYear() {
        return isShowYear;
    }

    public void setShowYear(boolean showYear) {
        isShowYear = showYear;
        this.textButton.setShowYear(showYear);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.isPressing = true;
        this.rePaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.isPressing = false;
        this.rePaint();

        if(this.isSelecting){
            this.click();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.isSelecting = true;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.isSelecting = false;
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.rePaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        textButton.setSize(this.getWidth(), this.getHeight() / 6);
        super.paintComponent(g);
    }

    @Override
    public String toString(){
        if(this.time == null){
            return super.toString();
        }
        return this.time.getMonth() + " " + this.time.getDay();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
