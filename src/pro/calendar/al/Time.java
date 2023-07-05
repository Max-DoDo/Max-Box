package pro.calendar.al;

/**
 * <h2>
 *
 * </h2>
 * <p>
 *
 * </p>
 *
 * @author Max
 * @date 2023/07/03 14:04
 **/
public class Time {

    private int year;

    private int month;

    private int day;

    public static final int EQUAL = 0;

    public static final int OLDER = 1;

    public static final int YOUNGER = 2;

    public Time(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Time(int year, int month){
        this.year = year;
        this.month = month;
        this.day = 1;
    }

    public int compare(Time time){

        if(this.year > time.year){
            return YOUNGER;
        } else if (this.year < time.year) {
            return  OLDER;
        }

        if(this.month < time.month){
            return OLDER;
        } else if (this.month > time.month) {
            return  YOUNGER;
        }

        if(this.day < time.day){
            return OLDER;
        } else if (this.day > time.day) {
            return  YOUNGER;
        }

        return EQUAL;
    }

    public void stepMonth(boolean toward){

        if(toward){
            if(this.month == 12){
                this.month = 1;
                year++;
            }else{
                this.month++;
            }
        }else{
            if(this.month == 1){
                this.month = 12;
                year--;
            }else{
                this.month--;
            }
        }


    }


    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return this.year;
    }

    @Override
    public String toString() {

        return "Time: " + year + "\t" + month + "\t" + day;
    }
}
