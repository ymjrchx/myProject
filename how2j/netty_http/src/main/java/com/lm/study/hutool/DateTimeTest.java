package com.lm.study.hutool;

import cn.hutool.core.date.*;
import cn.hutool.core.thread.ThreadUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.lm.study.hutool.PrintUtil.*;

/**
 * @author chenxin
 * @date 2019/7/15 13:31
 */
public class DateTimeTest {
    @Test
    public void DateUtil(){
        Date date = DateUtil.date();
        pl(date);
        Date date1 = DateUtil.date(Calendar.getInstance());
        System.out.println(date1);
        Date date2 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date2);

        String now = DateUtil.now();
        System.out.println(now);
        String today = DateUtil.today();
        System.out.println(today);
        String dataStr = "2017-03-01";
        Date date3 = DateUtil.parse(dataStr);
        System.out.println(date3);

        String datestr1 = "2017-03-01";
        Date date4 = DateUtil.parse(datestr1,"yyyy-MM-dd");

        String format = DateUtil.format(date4,"yyyy/MM/dd");

        System.out.println(format);

        String formatDate = DateUtil.formatDate(date4);
        System.out.println(formatDate);

        String formatDateTime = DateUtil.formatDateTime(date4);
        System.out.println(formatDateTime);
        String formatTime = DateUtil.formatTime(date4);
        System.out.println(formatTime);
        Date date5 = DateUtil.date();
        System.out.println(DateUtil.year(date5));
        System.out.println(DateUtil.month(date5));
        System.out.println(DateUtil.monthEnum(date));

        String dateStr2 = "2017-03-01 22:33:23";
        Date date6 = DateUtil.parse(dateStr2);
        System.out.println(date6);
        Date date61 = DateUtil.parseDate(dateStr2);
        System.out.println(date61);

        Date beginOfDay = DateUtil.beginOfDay(date6);
        System.out.println(beginOfDay);
        Date endOfDay = DateUtil.endOfDay(date6);
        System.out.println(endOfDay);

        Date newDate = DateUtil.offset(date6, DateField.DAY_OF_MONTH,2);
        System.out.println(newDate);
        System.out.println(date6);

        DateTime newDate2 = DateUtil.offsetDay(date,3);
        System.out.println(newDate2);
        DateTime newDate3 = DateUtil.offsetHour(date6,-3);
        System.out.println(newDate3);

        System.out.println(DateUtil.yesterday());
        System.out.println(DateUtil.tomorrow());
        System.out.println(DateUtil.lastWeek());
        System.out.println(DateUtil.nextWeek());
        System.out.println(DateUtil.lastMonth());
        System.out.println(DateUtil.nextMonth());

        String dateStr1 = "2017-03-01 22:33:23";
        Date date7 = DateUtil.parse(dateStr1);
        String dateStr = "2017-04-01 23:33:23";
        Date date8 = DateUtil.parse(dateStr);

        long betweenDay = DateUtil.between(date7,date8,DateUnit.DAY);
        System.out.println(betweenDay);
        String formatBetween = DateUtil.formatBetween(betweenDay, BetweenFormater.Level.MINUTE);
        System.out.println(formatBetween);

        TimeInterval timer = DateUtil.timer();
        ThreadUtil.sleep(30,TimeUnit.SECONDS);
        System.out.println(timer.interval());
        System.out.println(timer.intervalRestart());
        System.out.println(timer.intervalMinute());
        System.out.println(DateUtil.ageOfNow("1990-01-30"));
        System.out.println(DateUtil.isLeapYear(2017));
    }

    @Test
    public void DateTime(){
        Date date = new Date();
        DateTime time = new DateTime(date);
        pl(time);

        DateTime now = DateTime.now();
        DateTime dt = DateTime.of(date);
        pl(now);
        pl(dt);
        DateTime now1 = DateUtil.offsetDay(now,10);
        pl("dddd");
        pl(now1);
        pl(now);
        DateTime dateTime = new DateTime("2017-01-05 12:34:23",DatePattern.NORM_DATETIME_FORMAT);
        pl(dateTime);
        pl(dateTime.year());
        pl(dateTime.quarter());
        pl(dateTime.monthEnum());
        pl(dateTime.dayOfMonth());
        pl(dateTime);
        dateTime.offset(DateField.DAY_OF_MONTH,23);
        pl(dateTime);
        dateTime.setMutable(false);
        DateTime offset = dateTime.offset(DateField.MONTH,2);
        pl(offset);
        pl(dateTime);
        pl(dateTime.toString(DatePattern.NORM_TIME_FORMAT));
        pl(dateTime.toString(DatePattern.NORM_DATE_FORMAT));

    }








































































}
