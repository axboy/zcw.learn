package local.zcw.demo.calendar;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/1/1 0:53
 */
public class Main {
    public static void main(String[] args) {
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println("current timezone : " + defaultZone);
        System.out.println("now    ---> " + new Date());

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Pontianak"));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println("error  ---> " + cal.getTime());

        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeZone(TimeZone.getTimeZone("Asia/Pontianak"));
        System.out.println("id date: " + cal1.get(Calendar.DAY_OF_MONTH));    //增加一行
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        System.out.println("target ---> " + cal1.getTime());
    }
}
