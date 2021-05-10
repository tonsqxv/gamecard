package com.macower.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils
{
  private static Log log = LogFactory.getLog(DateUtils.class);

  private static final String[] FORMATS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm", 
    "yyyy-MM-dd HH:mm:ss", "HH:mm", "HH:mm:ss", "yyyy-MM" };

  public static Date convert(String str)
  {
    if ((str != null) && (str.length() > 0)) {
      if ((str.length() > 10) && (str.charAt(10) == 'T'))
        str = str.replace('T', ' ');
      for (String format : FORMATS) {
        if (str.length() != format.length()) continue;
        try {
          log.debug("convert " + str + " to date format " + 
            format);
          Date date = new SimpleDateFormat(format).parse(str);
          log.debug("****" + date + "****");
          return date;
        } catch (ParseException e) {
          log.warn(e.getMessage());
        }
      }
    }

    return null;
  }
  
  public static Date convert(String str ,String format)
  {
    if ((str != null) && (str.length() > 0)) {
        try {
          Date date = new SimpleDateFormat(format).parse(str);
          return date;
        } catch (ParseException e) {
          log.warn(e.getMessage());
        }
    }
    return null;
  }

  public static String UDateToString(Date udate, String format)
  {
    String sdate = null;
    try {
      sdate = new SimpleDateFormat(format).format(udate);
    } catch (Exception e) {
      log.error(
        "Can not convert [java.util.date] to [java.lang.String]. [FORMAT]:" + 
        format, e);
    }
    return sdate;
  }
  

  
}
