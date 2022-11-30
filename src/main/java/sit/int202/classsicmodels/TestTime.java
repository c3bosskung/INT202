package sit.int202.classsicmodels;

import java.util.Date;

public class TestTime {

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println(now);
        Date d = new Date(now);
        System.out.println(d);
        Date start = new Date(0);
        System.out.println(start);
        Date oneDay = new Date(1000*60*60*24);
        System.out.println(oneDay);
        System.out.println(new Date(1000L*60*60*24*365*52));
    }

}
