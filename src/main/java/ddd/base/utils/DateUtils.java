package ddd.base.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
* DateUtils
*@author  likongpeng
*@date 2021/4/16
*/
@Slf4j
public class DateUtils {

  public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
  public static final String DEFAULT_DATE_MS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
  public static final String DEFAULT_DATE_T_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

  public static final String DEFAULT_DATE_SECOND_FORMAT = "yyyyMMddHHmmss";
  public static final String DEFAULT_DATE_MINUTE_FORMAT = "yyyyMMddHHmm";
  public static final String DEFAULT_DATE_TEN_MINUTE_FORMAT = "yyyyMMddHHm0";

  static final String[] PATTERNS = new String[]{DEFAULT_DATETIME_FORMAT,
                                                DEFAULT_DATE_MS_FORMAT, DEFAULT_DATE_T_FORMAT,
                                                DEFAULT_DATE_FORMAT};


  /**
   * 给指定日期增减加天数
   *
   * @param days 需要增加的天数
   * @param date 日期
   */
  public static Date addDays(Date date, int days) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, days);
    return cal.getTime();
  }

  /**
   * 给指定日期增减加小时
   *
   * @param hours 需要增加的小时
   * @param date 日期
   */
  public static Date addHours(Date date, int hours) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.HOUR_OF_DAY, hours);
    return cal.getTime();
  }

  /**
   * @param second 需要增加的秒
   * @param date 日期
   */
  public static Date addSecond(Date date, int second) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.SECOND, second);
    return cal.getTime();
  }

  /**
   * 获取零点的时间戳
   *
   * @param days 天
   * @return 时间戳
   */
  public static long zeroTime(int days){
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.add(Calendar.DAY_OF_MONTH, days);
    return cal.getTimeInMillis()/1000;
  }

  /**
   * 检查日期大小，包含时分秒
   *
   * @return true date1 > date2
   */
  public static boolean after(Date date1, Date date2) {
    Timestamp ts1 = new Timestamp(date1.getTime());
    Timestamp ts2 = new Timestamp(date2.getTime());
    return ts1.after(ts2);
  }

  /**
   * 检查日期大小，包含时分秒
   *
   * @return true date1 > date2
   */
  public static boolean after(String date1, String date2) {
    if (date1.compareTo(date2) >= 0) {
      return true;
    }
    return false;
  }

  /**
   * 当前时间格式化
   */
  public static String dateToString() {
    return dateToString(new Date(), DEFAULT_DATETIME_FORMAT);
  }

  /**
   * 指定时间格式化
   */
  public static long dateToLong(Date date) {
    return Long.parseLong(dateToString(date, DEFAULT_DATE_SECOND_FORMAT));
  }

  /**
   * 指定时间格式化
   */
  public static String dateToString(Date date) {
    return dateToString(date, DEFAULT_DATETIME_FORMAT);
  }

  /**
   * 将时间转化成规定的格式的字符串
   *
   * @param date    转换时间
   * @param pattern 转换格式
   * @return 转换后时间字符串
   */
  public static String dateToString(Date date, String pattern) {
    if (date == null) {
      return null;
    }
    return DateFormatUtils.format(date, pattern);
  }

  /**
   * 字符串时间转Date格式
   *
   * @return date类型时间
   */
  public static Date stringToDate(String dateStr, String pattern) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.parse(dateStr);
    } catch (Exception e) {
      log.error("DateUtils.SimpleDateFormat.dateStr[" + dateStr + "]解析异常！ ", e);
      throw new RuntimeException(e);
    }
  }

  public static long nextStartTime(long timeStamp){
    Date date = new Date(timeStamp);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.add(Calendar.DAY_OF_MONTH , 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE , 0);
    calendar.set(Calendar.SECOND, 0);

    return calendar.getTime().getTime();
  }

  /**
   * 将"yyyy-MM-dd"格式的日期字符串转化为秒
   *
   * @param dateStr 日期
   * @return 时间戳秒
   */
  public static Long parseDefaultDateFormat2Seconds(String dateStr) {
    if (StringUtils.isBlank(dateStr)) {
      return null;
    }

    try {
      Date date = DateUtils.stringToDate(dateStr, DateUtils.DEFAULT_DATE_FORMAT);
      return date.getTime() / 1000;
    }
    catch (Exception e) {
      log.warn("时间转化异常!dateStr={}",dateStr, e);
      return null;
    }
  }

  /**
   * 将"yyyy-MM-dd HH:mm:ss"格式的日期字符串转化为秒
   *
   * @param dateStr 日期
   * @return 时间戳秒
   */
  public static Long parseDefaultDateTimeFormat2Seconds(String dateStr) {
    if (StringUtils.isBlank(dateStr)) {
      return null;
    }

    try {
      Date date = DateUtils.stringToDate(dateStr, DateUtils.DEFAULT_DATETIME_FORMAT);
      return date.getTime() / 1000;
    }
    catch (Exception e) {
      log.warn("时间转化异常!dateStr={}",dateStr, e);
      return null;
    }
  }

  /**
   * 将秒转换过成制定规则的日期字符串
   *
   * @param seconds 秒
   * @param pattern 格式
   * @return 格式化时间
   */
  public static String parseSecondsFormat2DateTimeStr(long seconds, String pattern) {
    if(StringUtils.isBlank(pattern)) {
      return null;
    }
    return dateToString(new Date(seconds * 1000),pattern);
  }

  /**
   * 获得昨日开始时间
   */
  public static Date yesterBeginDay(Date currentDay) {
    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_MONTH,-1);

    calendar.set(Calendar.HOUR_OF_DAY,0);
    calendar.set(Calendar.MINUTE,0);
    calendar.set(Calendar.SECOND,0);
    calendar.set(Calendar.MILLISECOND,0);
    return calendar.getTime();
  }

  /**
   * 获得昨日结束时间
   */
  public static Date yesterEndDay(Date currentDay) {
    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_MONTH,-1);
    calendar.set(Calendar.HOUR_OF_DAY,23);
    calendar.set(Calendar.MINUTE,59);
    calendar.set(Calendar.SECOND,59);
    calendar.set(Calendar.MILLISECOND,999);
    return calendar.getTime();
  }

  public static void main(String[] args) {
    Date cureentDate = new Date();
    System.out.println(dateToString(yesterBeginDay(cureentDate)));
    System.out.println(dateToString(yesterEndDay(cureentDate)));
  }
}
