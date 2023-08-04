package pro.calendar.al;

/**
 * <h2>
 *      事件对象
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/03 14:02
 **/
public class Event {

    /**
     * 事件的时间
     */
    private Time time;

    /**
     * 事件的持续时间. 以分钟为单位. 默认为60.
     */
    private int duration;

    /**
     * 事件的标题
     */
    private String title;

    /**
     * 事件的注释
     */
    private String comment;

    public Event(){

        init();
    }

    /**
     *
     * 通过初始化事件的时间, 标题和注释来创建本类对象
     *
     * @param time 事件的开始时间
     * @param title 事件的标题
     * @param comment 事件的注释
     */
    public Event(Time time, String title, String comment){
        this.time = time;
        this.title = title;
        this.comment = comment;
    }

    /**
     * 构造函数调用的初始化方法
     */
    private void init(){

        duration = 60;
        title = "新建事件";
        comment = "";

    }

    /**
     * 设置开始时间
     * @param time 事件开始的时间
     */
    public void setTime(Time time){
        this.time = time;
    }

    /**
     * 返回事件开始的时间
     * @return Time 时间对象
     * @see Time
     */
    public Time getTime(){
        return this.time;
    }



}
