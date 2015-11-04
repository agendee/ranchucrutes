package br.com.wjaa.ranchucrutes.commons.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wagner on 04/11/15.
 */
public class DateUtils {

    private static final Locale locale = new Locale("pt", "BR");

    public static Date now(){
        Calendar c = Calendar.getInstance(locale);
        return c.getTime();
    }


    public static Calendar nowCalendar() {
        Calendar c = Calendar.getInstance(locale);
        return c;
    }
}
