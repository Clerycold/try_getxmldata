package com.example.clery.try_getxmldata;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by clery on 2017/2/9.
 */

public class CalendarUtil {
        private int weeks = 0;// 用來全局控制 上一周，本周，下一周的周數變化
        private int MaxDate; // 一月最大天數
        private int MaxYear; // 一年最大天數

        /**
         * 測試
         *
         * @param args
         */
        public static void main(String[] args) {
//            CalendarUtil tt = new CalendarUtil();
            /**
            System.out.println("獲取當天日期:" + tt.getNowTime("yyyy-MM-dd"));
            System.out.println("獲取本周一日期:" + tt.getMondayOFWeek());
            System.out.println("獲取本周日的日期~:" + tt.getCurrentWeekday());
            System.out.println("獲取上周一日期:" + tt.getPreviousWeekday());
            System.out.println("獲取上周日日期:" + tt.getPreviousWeekSunday());
            System.out.println("獲取下周一日期:" + tt.getNextMonday());
            System.out.println("獲取下周日日期:" + tt.getNextSunday());
            System.out.println("獲得相應周的周六的日期:" + tt.getNowTime("yyyy-MM-dd"));
            System.out.println("獲取本月第一天日期:" + tt.getFirstDayOfMonth());
            System.out.println("獲取本月最後一天日期:" + tt.getDefaultDay());
            System.out.println("獲取上月第一天日期:" + tt.getPreviousMonthFirst());
            System.out.println("獲取上月最後一天的日期:" + tt.getPreviousMonthEnd());
            System.out.println("獲取下月第一天日期:" + tt.getNextMonthFirst());
            System.out.println("獲取下月最後一天日期:" + tt.getNextMonthEnd());
            System.out.println("獲取本年的第一天日期:" + tt.getCurrentYearFirst());
            System.out.println("獲取本年最後一天日期:" + tt.getCurrentYearEnd());
            System.out.println("獲取去年的第一天日期:" + tt.getPreviousYearFirst());
            System.out.println("獲取去年的最後一天日期:" + tt.getPreviousYearEnd());
            System.out.println("獲取明年第一天日期:" + tt.getNextYearFirst());
            System.out.println("獲取明年最後一天日期:" + tt.getNextYearEnd());
            System.out.println("獲取本季度第一天:" + tt.getThisSeasonFirstTime(11));
            System.out.println("獲取本季度最後一天:" + tt.getThisSeasonFinallyTime(11));
            System.out.println("獲取兩個日期之間間隔天數2008-12-1~2008-9.29:"
                    + CalendarUtil.getTwoDay("2008-12-1", "2008-9-29"));
            System.out.println("獲取當前月的第幾周：" + tt.getWeekOfMonth());
            System.out.println("獲取當前年份：" + tt.getYear());
            System.out.println("獲取當前月份：" + tt.getMonth());
            System.out.println("獲取今天在本年的第幾天：" + tt.getDayOfYear());
            System.out.println("獲得今天在本月的第幾天(獲得當前日)：" + tt.getDayOfMonth());
            System.out.println("獲得今天在本周的第幾天：" + tt.getDayOfWeek());
            System.out.println("獲得半年後的日期："
                    + tt.convertDateToString(tt.getTimeYearNext()));
             **/
        }

        /**
         * 獲得當前年份
         *
         * @return
         */
        public static int getYear() {
            return Calendar.getInstance().get(Calendar.YEAR);
        }

        /**
         * 獲得當前月份
         *
         * @return
         */
        public static int getMonth() {
            return Calendar.getInstance().get(Calendar.MONTH) + 1;
        }

        /**
         * 獲得今天在本年的第幾天
         *
         * @return
         */
        public static int getDayOfYear() {
            return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        }

        /**
         * 獲得今天在本月的第幾天(獲得當前日)
         *
         * @return
         */
        public static int getDayOfMonth() {
            return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        }

        /**
         * 獲得今天在本周的第幾天
         *
         * @return
         */
        public static int getDayOfWeek() {
            return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        }

        /**
         * 獲得今天是這個月的第幾周
         *
         * @return
         */
        public static int getWeekOfMonth() {
            return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
        }

        /**
         * 獲得半年後的日期
         *
         * @return
         */
        public static Date getTimeYearNext() {
            Calendar.getInstance().add(Calendar.DAY_OF_YEAR, 183);
            return Calendar.getInstance().getTime();
        }

        /**
         * 將日期轉換成字符串
         *
         * @param dateTime
         * @return
         */
        public static String convertDateToString(Date dateTime) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(dateTime);
        }

        /**
         * 得到二個日期間的間隔天數
         *
         * @param sj1
         * @param sj2
         * @return
         */
        public static String getTwoDay(String sj1, String sj2) {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            long day = 0;
            try {
                java.util.Date date = myFormatter.parse(sj1);
                java.util.Date mydate = myFormatter.parse(sj2);
                day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
            } catch (Exception e) {
                return "";
            }
            return day + "";
        }

        /**
         * 根據一個日期，返回是星期幾的字符串
         *
         * @param sdate
         * @return
         */
        public static String getWeek(String sdate) {
            // 再轉換为時間
            Date date = CalendarUtil.strToDate(sdate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            // int hour=c.get(Calendar.DAY_OF_WEEK);
            // hour中存的就是星期幾了，其範圍 1~7
            // 1=星期日 7=星期六，其他類推
            return new SimpleDateFormat("EEEE").format(c.getTime());
        }

        /**
         * 將短時間格式字符串轉換为時間 yyyy-MM-dd
         *
         * @param strDate
         * @return
         */
        public static Date strToDate(String strDate) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }

        /**
         * 兩個時間之間的天數
         *
         * @param date1
         * @param date2
         * @return
         */
        public static long getDays(String date1, String date2) {
            if (date1 == null || date1.equals(""))
                return 0;
            if (date2 == null || date2.equals(""))
                return 0;
            // 轉換为標准時間
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            java.util.Date mydate = null;
            try {
                date = myFormatter.parse(date1);
                mydate = myFormatter.parse(date2);
            } catch (Exception e) {
            }
            long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
            return day;
        }

        /**
         * 計算當月最後一天,返回字符串
         *
         * @return
         */
        public String getDefaultDay() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.DATE, 1);// 設为當前月的1號
            lastDate.add(Calendar.MONTH, 1);// 加一個月，變为下月的1號
            lastDate.add(Calendar.DATE, -1);// 減去一天，變为當月最後一天

            str = sdf.format(lastDate.getTime());
            return str;
        }

        /**
         * 上月第一天
         *
         * @return
         */
        public String getPreviousMonthFirst() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.DATE, 1);// 設为當前月的1號
            lastDate.add(Calendar.MONTH, -1);// 減一個月，變为下月的1號
            // lastDate.add(Calendar.DATE,-1);//減去一天，變为當月最後一天

            str = sdf.format(lastDate.getTime());
            return str;
        }

        /**
         * 獲取當月第一天
         *
         * @return
         */
        public String getFirstDayOfMonth() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.DATE, 1);// 設为當前月的1號
            str = sdf.format(lastDate.getTime());
            return str;
        }

        /**
         * 獲得本周星期日的日期
         *
         * @return
         */
        public String getCurrentWeekday() {
            weeks = 0;
            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
            Date monday = currentDate.getTime();

            DateFormat df = DateFormat.getDateInstance();
            String preMonday = df.format(monday);
            return preMonday;
        }

        /**
         * 獲取當天時間
         *
         * @param dateformat
         * @return
         */
        public static String getNowTime(String dateformat) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
            String hehe = dateFormat.format(now);
            return hehe;
        }

        /**
         * 獲得當前日期與本周日相差的天數
         *
         * @return
         */
        private int getMondayPlus() {
            Calendar cd = Calendar.getInstance();
            // 獲得今天是一周的第幾天，星期日是第一天，星期二是第二天......
            int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中國禮拜一作为第一天所以這裏減1
            if (dayOfWeek == 1) {
                return 0;
            } else {
                return 1 - dayOfWeek;
            }
        }

        /**
         * 獲得本周一的日期
         *
         * @return
         */
        public String getMondayOFWeek() {
            weeks = 0;
            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus);
            Date monday = currentDate.getTime();

            DateFormat df = DateFormat.getDateInstance();
            String preMonday = df.format(monday);
            return preMonday;
        }

        /**
         * 獲得相應周的周六的日期
         *
         * @return
         */
        public String getSaturday() {
            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
            Date monday = currentDate.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String preMonday = df.format(monday);
            return preMonday;
        }

        /**
         * 獲得上周星期日的日期
         *
         * @return
         */
        public String getPreviousWeekSunday() {
            weeks = 0;
            weeks--;
            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
            Date monday = currentDate.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String preMonday = df.format(monday);
            return preMonday;
        }

        /**
         * 獲得上周星期一的日期
         *
         * @return
         */
        public String getPreviousWeekday() {
            weeks--;
            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
            Date monday = currentDate.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String preMonday = df.format(monday);
            return preMonday;
        }

        /**
         * 獲得下周星期一的日期
         */
        public String getNextMonday() {
            weeks++;
            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
            Date monday = currentDate.getTime();
//            DateFormat df = DateFormat.getDateInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String preMonday = sdf.format(monday);
            return preMonday;
        }

        /**
         * 獲得下周星期日的日期
         */
        public String getNextSunday() {

            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
            Date monday = currentDate.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String preMonday = df.format(monday);
            return preMonday;
        }

        private int getMonthPlus() {
            Calendar cd = Calendar.getInstance();
            int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
            cd.set(Calendar.DATE, 1);// 把日期設置为當月第一天
            cd.roll(Calendar.DATE, -1);// 日期回滾一天，也就是最後一天
            MaxDate = cd.get(Calendar.DATE);
            if (monthOfNumber == 1) {
                return -MaxDate;
            } else {
                return 1 - monthOfNumber;
            }
        }

        /**
         * 獲得上月最後一天的日期
         *
         * @return
         */
        public String getPreviousMonthEnd() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.add(Calendar.MONTH, -1);// 減一個月
            lastDate.set(Calendar.DATE, 1);// 把日期設置为當月第一天
            lastDate.roll(Calendar.DATE, -1);// 日期回滾一天，也就是本月最後一天
            str = sdf.format(lastDate.getTime());
            return str;
        }

        /**
         * 獲得下個月第一天的日期
         *
         * @return
         */
        public String getNextMonthFirst() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.add(Calendar.MONTH, 1);// 減一個月
            lastDate.set(Calendar.DATE, 1);// 把日期設置为當月第一天
            str = sdf.format(lastDate.getTime());
            return str;
        }

        /**
         * 獲得下個月最後一天的日期
         *
         * @return
         */
        public String getNextMonthEnd() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.add(Calendar.MONTH, 1);// 加一個月
            lastDate.set(Calendar.DATE, 1);// 把日期設置为當月第一天
            lastDate.roll(Calendar.DATE, -1);// 日期回滾一天，也就是本月最後一天
            str = sdf.format(lastDate.getTime());
            return str;
        }

        /**
         * 獲得明年最後一天的日期
         *
         * @return
         */
        public String getNextYearEnd() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.add(Calendar.YEAR, 1);// 加一個年
            lastDate.set(Calendar.DAY_OF_YEAR, 1);
            lastDate.roll(Calendar.DAY_OF_YEAR, -1);
            str = sdf.format(lastDate.getTime());
            return str;
        }

        /**
         * 獲得明年第一天的日期
         *
         * @return
         */
        public String getNextYearFirst() {
            String str = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar lastDate = Calendar.getInstance();
            lastDate.add(Calendar.YEAR, 1);// 加一個年
            lastDate.set(Calendar.DAY_OF_YEAR, 1);
            str = sdf.format(lastDate.getTime());
            return str;

        }

        /**
         * 獲得本年有多少天
         *
         * @return
         */
        private int getMaxYear() {
            Calendar cd = Calendar.getInstance();
            cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期設为當年第一天
            cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滾一天。
            int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
            return MaxYear;
        }

        private int getYearPlus() {
            Calendar cd = Calendar.getInstance();
            int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 獲得當天是一年中的第幾天
            cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期設为當年第一天
            cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滾一天。
            int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
            if (yearOfNumber == 1) {
                return -MaxYear;
            } else {
                return 1 - yearOfNumber;
            }
        }

        /**
         * 獲得本年第一天的日期
         *
         * @return
         */
        public String getCurrentYearFirst() {
            int yearPlus = this.getYearPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, yearPlus);
            Date yearDay = currentDate.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String preYearDay = df.format(yearDay);
            return preYearDay;
        }

        // 獲得本年最後一天的日期 *
        public String getCurrentYearEnd() {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
            String years = dateFormat.format(date);
            return years + "-12-31";
        }

        // 獲得上年第一天的日期 *
        public String getPreviousYearFirst() {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
            String years = dateFormat.format(date);
            int years_value = Integer.parseInt(years);
            years_value--;
            return years_value + "-1-1";
        }

        // 獲得上年最後一天的日期
        public String getPreviousYearEnd() {
            weeks--;
            int yearPlus = this.getYearPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks
                    + (MaxYear - 1));
            Date yearDay = currentDate.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String preYearDay = df.format(yearDay);
            return preYearDay;
        }

        /**
         * 獲得本季度第一天
         *
         * @param month
         * @return
         */
        public String getThisSeasonFirstTime(int month) {
            int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
            int season = 1;
            if (month >= 1 && month <= 3) {
                season = 1;
            }
            if (month >= 4 && month <= 6) {
                season = 2;
            }
            if (month >= 7 && month <= 9) {
                season = 3;
            }
            if (month >= 10 && month <= 12) {
                season = 4;
            }
            int start_month = array[season - 1][0];
            int end_month = array[season - 1][2];

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
            String years = dateFormat.format(date);
            int years_value = Integer.parseInt(years);

            int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
            int end_days = getLastDayOfMonth(years_value, end_month);
            String seasonDate = years_value + "-" + start_month + "-" + start_days;
            return seasonDate;

        }

        /**
         * 獲得本季度最後一天
         *
         * @param month
         * @return
         */
        public String getThisSeasonFinallyTime(int month) {
            int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
            int season = 1;
            if (month >= 1 && month <= 3) {
                season = 1;
            }
            if (month >= 4 && month <= 6) {
                season = 2;
            }
            if (month >= 7 && month <= 9) {
                season = 3;
            }
            if (month >= 10 && month <= 12) {
                season = 4;
            }
            int start_month = array[season - 1][0];
            int end_month = array[season - 1][2];

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
            String years = dateFormat.format(date);
            int years_value = Integer.parseInt(years);

            int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
            int end_days = getLastDayOfMonth(years_value, end_month);
            String seasonDate = years_value + "-" + end_month + "-" + end_days;
            return seasonDate;

        }

        /**
         * 獲取某年某月的最後一天
         *
         * @param year
         *            年
         * @param month
         *            月
         * @return 最後一天
         */
        private int getLastDayOfMonth(int year, int month) {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                    || month == 10 || month == 12) {
                return 31;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                return 30;
            }
            if (month == 2) {
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            }
            return 0;
        }

        /**
         * 是否閏年
         *
         * @param year
         *            年
         * @return
         */
        public boolean isLeapYear(int year) {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        }

        /**
         * 是否閏年
         *
         * @param year
         * @return
         */
        public boolean isLeapYear2(int year) {
            return new GregorianCalendar().isLeapYear(year);
        }
}