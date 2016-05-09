package br.com.wjaa.ranchucrutes.commons.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wagner on 04/11/15.
 */
public class DateUtils {

    private static final Log LOG = LogFactory.getLog(DateUtils.class);
    private static final Locale locale = new Locale("pt", "BR");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat sdfyyyyMMddTHHmmss = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static SimpleDateFormat sdfyyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdfyyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
    private static Date lastDayActualWeek;

    public static Date now(){
        Calendar c = Calendar.getInstance(locale);
        return c.getTime();
    }


    public static Calendar nowCalendar() {
        Calendar c = Calendar.getInstance(locale);
        return c;
    }

    public static String formatyyyyMMdd(Date date){
        return sdf.format(date);
    }

    public static String formatyyyyMMddTHHmmss(Date date){
        return sdfyyyyMMddTHHmmss.format(date);
    }

    public static Date getFirstDayActualWeek() {
        Calendar c = nowCalendar();
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    public static Date getLastDayActualWeek() {
        Calendar c = nowCalendar();
        c.set(Calendar.DAY_OF_WEEK,c. getActualMaximum(Calendar.DAY_OF_WEEK));
        return c.getTime();
    }

    public static String formatddMMyyyy(Date dataAgendamento) {
        return sdfddMMyyyy.format(dataAgendamento);
    }

    public static String formatISO8601(Date date) {
        return sdfyyyymmdd.format(date);
    }

    public static Date getDateyyyymmddhhmm(String s) {
        try {
            return sdfyyyyMMddHHmm.parse(s);
        } catch (ParseException e) {
            LOG.error("Erro no parse da data", e);
        }
        return null;
    }
}
