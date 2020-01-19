package com.sy.common.utils;


import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_00_FORMAT = "yyyy-MM-dd 00:00:00";

    public static final String YYYY_MM_DD_23_FORMAT = "yyyy-MM-dd 23:59:59";


    public static final String DATE_FORMAT_YYYYMM = "yyyy-MM";

    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss.SSS";

    private static final String[] MONTH_STRING = {"January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};


    ThreadLocal<Date> dateLocal = new ThreadLocal<Date>();

    /**
     * @return
     * @Author lichengyi
     * @Description
     * @Date 9:16 2018-12-03
     * @Param
     **/
    public static Date getYMD000(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_00_FORMAT);
        return format.parse(format.format(date));
    }

    public static boolean isMonthLastDay() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        String monthLastTime = format.format(ca.getTime());
        String nowTime = format.format(new Date());
        return monthLastTime.equals(nowTime);
    }


    /**
     * @return java.lang.String
     * @Author lichengyi
     * @Description 获取今天得哪个小时点
     * @Date 15:49 2018-11-30
     * @Param [hours]
     **/
    public static String getDateByHours(int hours) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd " + hours + ":00:00");
        return format.format(new Date());
    }

    //获取当前时间yyyymmddhhmmss
    public static String getNowYMDHMS() {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(new Date());
    }

    //计算当前时间加几天或者减几天yyyymmddhhmmss
    public static String addDays(Date date, int day) {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(addDay(date, day));
    }

    /**
     * 对日期格式做转换 yyyy-MM-dd
     */
    public static String toYMDString(Date date) {
        if (date != null) {
            return new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).format(date);
        } else {
            return null;
        }
    }

    public static String toYMDZeroString(Date date) {
        if (null != date) {
            return new SimpleDateFormat(YYYY_MM_DD_00_FORMAT).format(date);
        } else {
            return null;
        }
    }

    public static String toYMD23String(Date date) {
        if (null != date) {
            return new SimpleDateFormat(YYYY_MM_DD_23_FORMAT).format(date);
        } else {
            return null;
        }
    }

    public static boolean isInTheDay(Date date) {
        if (null != date) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date());
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        }
        return false;
    }

    /**
     * 对日期格式做转换 yyyy-MM-dd HH:mm:ss
     */
    public static String toYMDHSString(Date date) {
        if (date != null) {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
        } else {
            return null;
        }
    }

    /**
     * 转换成日期格式  yyyy-MM-dd HH:mm:ss
     */
    public static Date parseDateYMDHS(String dateStr) {
        return parse(dateStr, DEFAULT_DATE_FORMAT);
    }

    public static Date parseDateYMDHSNew(String dateStr) {
        return parseNew(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * 转换成日期格式  yyyy-MM-dd
     */
    public static Date parseDate(String dateStr) {
        return parse(dateStr, DATE_FORMAT_YYYYMMDD);
    }

    /**
     * 转换成日期格式  yyyy-MM-dd
     */
    public static Date ToShortDate(Date date) {
        return parse(toYMDString(date), DATE_FORMAT_YYYYMMDD);
    }


    private static Date parse(String yearMonth, String dateFormat) {
        Date date;
        try {
            date = new SimpleDateFormat(dateFormat).parse(yearMonth);
        } catch (Exception pe) {
            return null;
        }
        return date;
    }

    private static Date parseNew(String yearMonth, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date date;
        try {
            date = sdf.parse(yearMonth);
        } catch (Exception pe) {
            return null;
        }
        return date;
    }

    public static Date getMonthDate(Date date) {
        return getMonthDate(date, -2);
    }

    public static Date getMonthDate(Date date, int num) {
        Calendar c = toCalendar(date);
        if (c != null) {
            int m = (num <= 0 ? c.get(Calendar.DAY_OF_MONTH) - 1 : num);
            c.add(Calendar.MONTH, -m);
            return c.getTime();
        } else {
            return null;
        }
    }

    public static int getMonth(Date date) {
        Calendar c = toCalendar(date);
        if (c != null) {
            return c.get(Calendar.MONTH) + 1;
        } else {
            return -1;
        }
    }

    public static Calendar toCalendar(Date date) {
        Calendar c = null;
        if (date != null) {
            c = Calendar.getInstance();
            c.setTime(date);
        }
        return c;
    }

    @SuppressWarnings("deprecation")
    public static int getToday() {
        int todayWeek = Calendar.getInstance().getTime().getDay();
        if (todayWeek == 0) {
            return 7;
        }
        return todayWeek;
    }

    public static Date getDate() {
        Calendar canlendar = Calendar.getInstance();
        return canlendar.getTime();
    }

    /**
     * 用年，月，日，时，分，秒构造日期类型
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour,
                               int iMinute, int iSecond) {
        iMonth--;
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.set(iYear, iMonth, iDate, iHour, iMinute, iSecond);
        return canlendar.getTime();
    }

    /**
     * 用年，月，日，时，分构造日期类型
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour,
                               int iMinute) {
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, iMinute, 0);
    }

    /**
     * 用年，月，日，时构造日期类型
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour) {
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, 0, 0);
    }

    /**
     * 用年，月，日构造日期类型
     */
    public static Date getDate(int iYear, int iMonth, int iDate) {
        return DateUtil.getDate(iYear, iMonth, iDate, 0, 0, 0);
    }

    /**
     * 用年，月构造日期类型
     */
    public static Date getDate(int iYear, int iMonth) {
        return DateUtil.getDate(iYear, iMonth, 1, 0, 0, 0);
    }

    /**
     * 用年构造日期类型
     */
    public static Date getDate(int iYear) {
        return DateUtil.getDate(iYear, 1, 1, 0, 0, 0);
    }

    public static Date getDate(String sYear) {
        int iYear = DateUtil.getRightNumber(sYear);
        return DateUtil.getDate(iYear);
    }

    public static Date getDate(String sYear, String sMonth) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        return DateUtil.getDate(iYear, iMonth);
    }

    public static Date getDate(String sYear, String sMonth, String sDate) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        return DateUtil.getDate(iYear, iMonth, iDate);
    }

    public static Date getDate(String sYear, String sMonth, String sDate,
                               String sHour) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        int iHour = DateUtil.getRightNumber(sHour);
        return DateUtil.getDate(iYear, iMonth, iDate, iHour);
    }

    public static Date getDate(String sYear, String sMonth, String sDate,
                               String sHour, String sMinute) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        int iHour = DateUtil.getRightNumber(sHour);
        int iMinute = DateUtil.getRightNumber(sMinute);
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, iMinute);
    }

    public static Date getDate(String sYear, String sMonth, String sDate,
                               String sHour, String sMinute, String sSecond) {
        int iYear = DateUtil.getRightNumber(sYear);
        int iMonth = DateUtil.getRightNumber(sMonth);
        int iDate = DateUtil.getRightNumber(sDate);
        int iHour = DateUtil.getRightNumber(sHour);
        int iMinute = DateUtil.getRightNumber(sMinute);
        int iSecond = DateUtil.getRightNumber(sSecond);
        return DateUtil.getDate(iYear, iMonth, iDate, iHour, iMinute, iSecond);
    }

    public static Date parseDate(String day, String hour, String minute) {
        Date date = parseDate(day, "yyyy-MM-dd");
        date = addHo(date, hour);
        date = addMi(date, minute);
        return date;
    }

    private static int getRightNumber(String sNumber) {
        try {
            return Integer.parseInt(sNumber);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static boolean isMax(Date date0, Date date1) {
        if (date0 == null || date1 == null) {
            return false;
        }
        if (date0.getTime() > date1.getTime()) {
            return true;
        }
        return false;

    }

    public static Date Max(Date date0, Date date1) {
        if (date0 != null && date1 != null) {
            if (date0.getTime() > date1.getTime()) {
                return date0;
            }
            return date1;
        } else if (date0 != null && date1 == null) {
            return date0;
        } else if (date0 == null && date1 != null) {
            return date1;
        } else {
            return null;
        }
    }

    public static Date Min(Date date0, Date date1) {
        if (date0 != null && date1 != null) {
            if (date0.getTime() < date1.getTime()) {
                return date0;
            }
            return date1;
        } else {
            return null;
        }
    }

    /**
     * 得到两个日期毫秒级差
     */
    public static long getMillisecondDif(Date date0, Date date1) {
        if (date0 == null || date1 == null) {
            return 0;
        }
        return date0.getTime() - date1.getTime();
    }

    /**
     * 得到两个日期秒级差
     */
    public static long getSecondDif(Date date0, Date date1) {
        return DateUtil.getMillisecondDif(date0, date1) / 1000;
    }

    /**
     * 得到两个日期分钟差
     */
    public static long getMinuteDif(Date date0, Date date1) {
        return DateUtil.getSecondDif(date0, date1) / 60;
    }

    /**
     * 得到两个日期小时差
     */
    public static int getHourDif(Date date0, Date date1) {
        return (int) DateUtil.getMinuteDif(date0, date1) / 60;
    }

    /**
     * 得到两个日期天数差
     */
    public static int getDayDif(Date date0, Date date1) {
        return (int) DateUtil.getHourDif(date0, date1) / 24;
    }

    /**
     * 两个日期月份差
     */
    public static int getMonthDif(Date date0, Date date1) {
        int elapsed = 0;
        GregorianCalendar gc0 = (GregorianCalendar) GregorianCalendar.getInstance();
        GregorianCalendar gc1 = (GregorianCalendar) GregorianCalendar.getInstance();

        if (date1.getTime() > date0.getTime()) {
            gc0.setTime(date0);
            gc1.setTime(date1);
        } else {
            gc1.setTime(date0);
            gc0.setTime(date1);
        }
        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        gc1.clear(Calendar.DATE);

        gc0.clear(Calendar.MILLISECOND);
        gc0.clear(Calendar.SECOND);
        gc0.clear(Calendar.MINUTE);
        gc0.clear(Calendar.HOUR_OF_DAY);
        gc0.clear(Calendar.DATE);

        while (gc0.before(gc1)) {
            gc0.add(Calendar.MONTH, 1);
            elapsed++;
        }
        return elapsed;
    }

    private static Date addDate(Date date, int iArg0, int iDate) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(iArg0, iDate);
        return canlendar.getTime();
    }

    /**
     * 日期增加秒
     */
    public static Date addSecond(Date date, int iSecond) {
        return addDate(date, Calendar.SECOND, iSecond);
    }

    /**
     * 日期增加分钟
     */
    public static Date addMinute(Date date, int iMinute) {
        return addDate(date, Calendar.MINUTE, iMinute);
    }

    /**
     * 日期增加小时
     */
    public static Date addHour(Date date, int iHour) {
        return addDate(date, Calendar.HOUR, iHour);
    }

    /**
     * 日期增加天数
     */
    public static Date addDay(Date date, int iDate) {
        return addDate(date, Calendar.DAY_OF_MONTH, iDate);
    }

    /**
     * 日期增加月
     */
    public static Date addMonth(Date date, int iMonth) {
        return addDate(date, Calendar.MONTH, iMonth);
    }

    /**
     * 日期增加年
     */
    public static Date addYear(Date date, int iYear) {
        return addDate(date, Calendar.YEAR, iYear);
    }

    /**
     * 日期增加秒
     */
    public static Date addSecond(Date date, String sSecond) {
        return addSecond(date, getRightNumber(sSecond));
    }

    /**
     * 日期增加分
     */
    public static Date addMi(Date date, String sMinute) {
        return addMinute(date, getRightNumber(sMinute));
    }

    /**
     * 日期增加小时
     */
    public static Date addHo(Date date, String sHour) {
        return addHour(date, getRightNumber(sHour));
    }

    /**
     * 日期增加天
     */
    public static Date addDay(Date date, String sDate) {
        return addDay(date, getRightNumber(sDate));
    }

    /**
     * 日期增加月
     */
    public static Date addMo(Date date, String sMonth) {
        return addMonth(date, getRightNumber(sMonth));
    }

    /**
     * 日期增加年
     */
    public static Date addYe(Date date, String sYear) {
        return addYear(date, getRightNumber(sYear));
    }

    public static String getDateFormate(Date date, String formate) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        return simpleDateFormate.format(date);
    }

    public static String get4yMdHmsS(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static String get4yMdHms(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String get4yMdHm(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm");
    }

    public static String get4yMd(Date date) {
        return getDateFormate(date, "yyyy-MM-dd");
    }

    public static String get4yMM(Date date) {
        return getDateFormate(date, "yyyyMM");
    }

    public static String get4yHh(Date date) {
        return getDateFormate(date, "hh:mm:ss");
    }

    public static String get4yMdNoDash(Date date) {
        return getDateFormate(date, "yyyyMMdd");
    }

    public static Date parseDateFullYear(String sDate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDate(String sDate, String formate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        try {
            return sDate == null ? null : simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 得到时间的部分
     */
    public static int getPartOfTime(Date date, String part) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTime(date);
        if (part.equals("year")) {
            return canlendar.get(Calendar.YEAR);
        }
        if (part.equals("month")) {
            return canlendar.get(Calendar.MONTH);
        }
        if (part.equals("date")) {
            return canlendar.get(Calendar.DAY_OF_MONTH);
        }
        if (part.equals("hour")) {
            return canlendar.get(Calendar.HOUR_OF_DAY);
        }
        if (part.equals("minute")) {
            return canlendar.get(Calendar.MINUTE);
        }
        if (part.equals("second")) {
            return canlendar.get(Calendar.SECOND);
        }
        if (part.equals("milliSecond")) {
            return canlendar.get(Calendar.MILLISECOND);
        }
        return -1;
    }

    /**
     * 判断一个日期是不是今天
     *
     * @return
     */
    public static boolean isToday(Date date) {
        if (DateUtil.get4yMd(date).equals(
                DateUtil.get4yMd(Calendar.getInstance().getTime()))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        /** 判断是否为闰年，赋值给一标识符flag */
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
            isLeep = true;
        } else if (yearNum % 400 == 0) {
            isLeep = true;
        } else {
            isLeep = false;
        }
        return isLeep;
    }

    /**
     * 计算某年某月的结束日期
     */
    public static String getYearMonthEndDay(int yearNum, int monthNum)
            throws ParseException {
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "31";
        if (tempMonth.equals("1") || tempMonth.equals("3")
                || tempMonth.equals("5") || tempMonth.equals("7")
                || tempMonth.equals("8") || tempMonth.equals("10")
                || tempMonth.equals("12")) {
            tempDay = "31";
        }
        if (tempMonth.equals("4") || tempMonth.equals("6")
                || tempMonth.equals("9") || tempMonth.equals("11")) {
            tempDay = "30";
        }
        if (tempMonth.equals("2")) {
            if (isLeapYear(yearNum)) {
                tempDay = "29";
            } else {
                tempDay = "28";
            }
        }

        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(sdf.parse(tempDate));
        return sDate;
    }

    /**
     * 计算当前日期是星期几(星期日为0)
     */
    public static int getWeekDay(Date strDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(strDate);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 当前时间是否在 两个时间之间
     */
    public static boolean nowIsBetweenDates(Date start, Date end) {
        Date temp = null;
        if (start.getTime() > end.getTime()) {// 换位
            temp = start;
            start = end;
            end = temp;
        }

        long nowTime = new Date().getTime();
        if (nowTime < start.getTime() || nowTime > end.getTime()) {// 直接返回
            return false;
        }

        return true;
    }

    /**
     * 获取某一天的开始时间
     *
     * @param date
     */
    public static Date getStartDateByDate(Date date) {
        Date firstDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        firstDate = cal.getTime();
        return firstDate;
    }

    /**
     * 获取某一天的结束时间
     *
     * @param date
     */
    public static Date getEndDateByDate(Date date) {
        Date firstDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        firstDate = cal.getTime();
        return firstDate;
    }

    /**
     * 获取一周的第一天
     */
    public static Date getFirstDayOfWeek(Date date) {
        Date firstDay = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        firstDay = addDay(cal.getTime(), 1);
        return firstDay;
    }

    /**
     * 获取一周的最后一天
     */
    public static Date getLastDayOfWeek(Date date) {
        Date lastDay = null;
        lastDay = addDay(getFirstDayOfWeek(date), 6);
        return lastDay;
    }

    /**
     * 获取日期的年份
     */
    public static int getYearByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月份
     */
    public static int getMonthByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = 0;
        month = cal.get(Calendar.MONTH) + 1;
        if (month == 13) {
            month = 1;
        }
        return month;
    }

    /**
     * 获取日期
     */
    public static int getDayByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取小时
     */
    public static int getHourByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     */
    public static int getMinuteByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    /**
     * 获取某一月的开始时间
     */
    public static Date getStartDateByMonth(Date date) {
        Date firstDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        firstDate = cal.getTime();
        return firstDate;
    }

    /**
     * 获取当天月的最后一天的时分秒
     */
    public static Date getEndDateByMonth(Date date) {
        Date firstDate = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        firstDate = cal.getTime();
        return firstDate;
    }

    /**
     * 得到日期部分
     */
    public static Date getDatePart(Date date) throws ParseException {
        String strdate = get4yMd(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strdate);
    }

    /**
     * 根据日期获得星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 获取时间的毫秒数
     */
    public static Long getTimeInMillis(Date date) {
        Calendar cal = Calendar.getInstance();
        Date curDate = null;
        if (date == null) {
            curDate = new Date();
        } else {
            curDate = date;
        }
        cal.setTime(curDate);
        return cal.getTimeInMillis();
    }

    /**
     * 获取没有时间部分的日期
     */
    public static Date getDateNoTime(Date date) {
        return getDate(getYearByDate(date), getMonthByDate(date),
                getDayByDate(date));
    }


    public static Date getWeekDate(Date date) {
        Calendar c = toCalendar(date);
        if (c != null) {
            int m = c.get(Calendar.DAY_OF_WEEK);
            if (m - 1 == 0) {
                c.add(Calendar.DAY_OF_WEEK, -6);
            } else {
                c.add(Calendar.DAY_OF_WEEK, -(m - 2));
            }
            DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            df.format(c.getTime());
            return c.getTime();
        } else {
            return null;
        }
    }

    /**
     * 转换成GMT格式
     */
    public static String toGMTString(Date source) {
        if (null == source) {
            return "";
        }
        // 19 Aug 2010 12:48:49 GMT
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        DateFormat df = new SimpleDateFormat("dd {0} yyyy HH:mm:ss");
        df.setCalendar(cal);
        sb.append(df.format(source)).append(" GMT");
        int index = getMonth(source) - 1;
        String m = MONTH_STRING[index].substring(0, 3);
        return MessageFormat.format(sb.toString(), m);
    }

    /**
     * 把毫秒转换成时间长度 时分秒
     *
     * @param milliseconds
     * @return
     */
    public static String getTime(int milliseconds) {
        //计算小时,用毫秒总数除以(1000*60*24),后去掉小数点
        int hour = milliseconds / (1000 * 60 * 60);
        //计算分钟,用毫秒总数减去小时乘以(1000*60*24)后,除以(1000*60),再去掉小数点
        int min = (milliseconds - hour * (1000 * 60 * 60)) / (1000 * 60);
        //同上
        int sec = (milliseconds - hour * (1000 * 60 * 60) - min * (1000 * 60)) / 1000;
        int msec = milliseconds - hour * (1000 * 60 * 60) - min * (1000 * 60) - sec * 1000;
        //拼接字符串
        String timeString = hour + ":" + min + ":" + sec + "." + msec;
        return timeString;
    }


    //获取当前月第一天：yyyy-mm-dd 00:00:00
    public static String getMonthFirstDay() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return new SimpleDateFormat(YYYY_MM_DD_00_FORMAT).format(c.getTime());
    }

    //获取当前月最后一天：yyyy-mm-dd 23:59:59
    public static String getMonthLastDay() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat(YYYY_MM_DD_23_FORMAT).format(ca.getTime());
    }

    //获取当年第一天：
    public static String getYearFirstDay() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        Date currYearFirst = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_00_FORMAT).format(currYearFirst);
    }

    //获取当年最后一天：
    public static String getYearLastDay() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_23_FORMAT).format(currYearLast);
    }

    /**
     * 获取两个时间之间的每天的日期段
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回每天的开始时间，结束时间 key为开始时间 value为结束时间
     */
    public static Map<String, String> getDays(String startTime, String endTime) {
        Map<String, String> days = new LinkedHashMap(128);
        String defaultStartTime = "00:00:00";
        String defaultEndTime = "23:59:59";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = df.parse(startTime);
            Date end = df.parse(endTime);
            DateFormat df1 = DateFormat.getDateInstance();
            String startDate = df1.format(start);
            String endDate = df1.format(end);
            if (startDate.equals(endDate)) {
                days.put(startTime, endTime);
                return days;
            }

            DateFormat df2 = DateFormat.getTimeInstance();
            String startTimes = df2.format(start);
            String endTimes = df2.format(end);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DAY_OF_YEAR, 1);
            while (tempStart.before(tempEnd)) {
                String date = sfd.format(tempStart.getTime()) + " ";
                if (days.size() == 0) {
                    days.put(date + startTimes, date + defaultEndTime);
                    tempStart.add(Calendar.DAY_OF_YEAR, 1);
                    continue;
                }

                if (endDate.equals(df1.format(tempStart.getTime()))) {
                    days.put(date + defaultStartTime, date + endTimes);
                    tempStart.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                }

                days.put(date + defaultStartTime, date + defaultEndTime);
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return days;
    }

    public static String getDateNew(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 1) + "" + calendar.get(Calendar.DATE);
    }
}
