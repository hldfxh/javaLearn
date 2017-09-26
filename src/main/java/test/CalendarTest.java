package test;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by fanxuehui on 2017/7/9.
 */
public class CalendarTest {

    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();

        //今天是今年第几个月，本月第几天
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        //本月第一天是一周的第几天
        calendar.set(Calendar.DAY_OF_MONTH,1);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        //一周的第一天是从星期几开始
        int firstDayOfWeek = calendar.getFirstDayOfWeek();

        //获取日历缩进
        int indent = 0;
        while (weekday!=firstDayOfWeek) {
            indent++;
            calendar.add(Calendar.DAY_OF_WEEK,-1);
            weekday = calendar.get(Calendar.DAY_OF_WEEK);
        }
        Locale.setDefault(Locale.ENGLISH);
        String[] weekNames = new DateFormatSymbols().getShortWeekdays();
        do {
            System.out.printf("%4s",weekNames[weekday]);
            calendar.add(Calendar.DAY_OF_WEEK,1);
            weekday = calendar.get(Calendar.DAY_OF_WEEK);
        } while (weekday!=firstDayOfWeek);
        System.out.println();

        for (int i = 0; i <= indent; i++) {
            System.out.print("   ");
        }
        calendar.set(Calendar.DAY_OF_MONTH,1);
        do {
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d",day);

            if (day == today) {
                System.out.printf("*");
            } else {
                System.out.printf(" ");
            }

            calendar.add(Calendar.DAY_OF_MONTH,1);
            weekday = calendar.get(Calendar.DAY_OF_WEEK);

            if (weekday == firstDayOfWeek) {
                System.out.println();
            }
        } while (calendar.get(Calendar.MONTH) == month);

    }
}
