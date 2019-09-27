package com.story.storyadmin.utils;

import com.story.storyadmin.service.common.AttendanceSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

/**
 * 自定义日期范围表达式，格式如下：暂支持Q,M，D(季度，月度，日期)
 * Q-1: 前一季度1日-前一季度最后一日
 * M-1: 前月1日-前月最后一日
 * Q-1-D-1/Q-0-D-1: 前两季度最后一日-前一季度倒数第二日  ==> 前1季度的前1天/当前季度前第二天
 */
@Component
public class DateExpressionUtil {

    @Autowired
    AttendanceSettingService attendanceSettingService;

    @Autowired
    private static DateExpressionUtil dateExpressionUtil;

    @PostConstruct
    public void init() {
        dateExpressionUtil = this;
        dateExpressionUtil.attendanceSettingService = this.attendanceSettingService;
    }


    /**
     * 获取当前月第一天，初始化时刻
     * @param timeFormatter
     * @return
     */
    private static Calendar getCurrentMonthMinDate(Date baseTime,String timeFormatter){
        //当前月第一天
        Calendar calendar=Calendar.getInstance();
        if(baseTime!=null){
            calendar.setTime(baseTime);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        //初始化时刻
        calendar =setCalendarTime(calendar, timeFormatter);

        return calendar;
    }

    //获取当天日期，初始化时刻 00:00:00
    public static Date getCurrentDate(Date baseTime){
        return getCurrentDate(baseTime,null).getTime();
    }

    /**
     * 获取当前日期，初始化时刻
     * @param timeFormatter 初始化时刻
     * @return
     */
    private static Calendar getCurrentDate(Date baseTime, String timeFormatter){
        //当前月第一天
        Calendar calendar=Calendar.getInstance();
        if(baseTime!=null){
            calendar.setTime(baseTime);
        }
        //初始化时刻
        calendar =setCalendarTime(calendar, timeFormatter);

        return calendar;
    }

    /**
     * 设置日期时刻
     * @param calendar
     * @param timeFormatter "00:00:00" , "23:59:59" ...
     * @return
     */
    private static Calendar setCalendarTime(Calendar calendar,String timeFormatter){
        if(!StringUtils.isEmpty(timeFormatter)){
            String[] str= timeFormatter.split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(str[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(str[1]));
            calendar.set(Calendar.SECOND, Integer.parseInt(str[2]));
            calendar.set(Calendar.MILLISECOND,0);
        }else{
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
        }
        return calendar;
    }

    /**
     * 转换时间表达式为数组
     * @param timeExpression
     * @return
     */
    private static String[] convertTimeExpressionToArray(String timeExpression){
        if(StringUtils.isEmpty(timeExpression)){
            return new String[]{"00:00:00","23:59:59"};
        }else{
            return timeExpression.split("/");
        }
    }

    /**
     * 解析单据发生时间或其他时间范围表达式
     * @param dateExpression
     * Q-1: 前一季度1日-前一季度最后一日
     * M-1: 前月1日-前月最后一日
     * Q-1-D-1/Q-0-D-2: 前两季度最后一日-前一季度倒数第二日  ==> 前1季度的前1天/当前季度前第二天
     * @param timeExpression '00:00:00/23:59:59'
     *
     * @return
     */
    public static Date[] explainDateRangeExpression(String dateExpression, String timeExpression, Date baseTime){
        Date[] dateArray= new Date[2];

        //准备
        String[] dataExpressionArr = dateExpression.split("/");
        String[] timeFormatters = convertTimeExpressionToArray(timeExpression);

        //申请临时变量
        Calendar calendar ;
        int calDateIndex=0;

        for(String dateExpress : dataExpressionArr) {
                //计算表达式
            calendar = calculate(dateExpress, timeFormatters[calDateIndex], calDateIndex, baseTime);
            dateArray[calDateIndex] = calendar.getTime();

            calDateIndex++;
        }

        if(dateArray[1]==null){
            //处理不带'/'的表达式，计算array[1]
            dateArray[1] = calculate(dataExpressionArr[0],timeFormatters[1],1,baseTime).getTime();
        }
        return dateArray;
    }

    /**
     * 解析单个表达式
     * @param dateExpress
     * @param timeFormatters
     * @param calDateIndex
     * @param baseTime
     * @return
     */
    private static Calendar calculate(String dateExpress,String timeFormatters, int calDateIndex, Date baseTime){
        //解析起/止日期表达式
        char[] chars= dateExpress.toCharArray();

        Calendar calendar= null ;
        char tempType='\0';
        int operator = 0;
        String tempValue="";

        //按照字符解析
        for(char ch : chars){
            switch(ch){
                case 'Q':
                    //季度
                case 'M':
                    //月度
                case 'D':
                    //自然日
                case 'T':
                    //交易日
                    calendar = calculate(calendar, tempType, operator, tempValue, timeFormatters,calDateIndex,baseTime);
                    tempType = ch;
                    operator=0;
                    tempValue="";
                    break;
                case '-':
                    if(operator==0){
                        operator = -1;
                    }
                    break;
                case '+':
                    if(operator==0){
                        operator = 1;
                    }
                    break;
                default:
                    //做数字处理
                    if(StringUtils.isEmpty(tempValue)){
                        tempValue = String.valueOf(ch);
                    }else{
                        tempValue += String.valueOf(ch);
                    }
            }
        }

        return calculate(calendar,tempType,operator,tempValue,timeFormatters,calDateIndex,baseTime);
    }

    /**
     * 计算单个表达式
     * @param calendar  指定日期
     * @param dateType Q,M,D,T
     * @param operator 1，-1
     * @param value 自然数
     * @param timeFormatter
     * @param calDateIndex 计算位置 0:起始时间，1:结束时间
     * @param baseTime
     * @return
     */
    private static Calendar calculate(Calendar calendar, char dateType, int operator, String value, String timeFormatter, int calDateIndex, Date baseTime) {
        if (dateType != '\0' && operator!=0) {
            //初始化日期
            calendar = initCalendar(calendar,timeFormatter,dateType,baseTime);
            switch (dateType) {
                case 'Q':
                    //季度
                    calendar.add(Calendar.MONTH, operator * Integer.parseInt(value) * 3 - ( calendar.get(Calendar.MONTH) % 3));
                    break;
                case 'M':
                    //月度
                    calendar.add(Calendar.MONTH, operator * Integer.parseInt(value));
                    break;
                case 'D':
                    //自然日
                    calendar.add(Calendar.DAY_OF_MONTH, operator * Integer.parseInt(value));
                    break;
                case 'T':
                    //交易日
                    calendar.setTime(dateExpressionUtil.attendanceSettingService.getNextNumMarketDay(calendar.getTime(),Integer.parseInt(value)));
                    break;
            }
            //修正
            calendar = fixedDate(calendar,dateType,calDateIndex);
        }
        return calendar;
    }

    /**
     * 初始化日期
     * @param calendar
     * @param timeFormatter
     * @param dateType
     * @param baseTime
     * @return
     */
    private static Calendar initCalendar(Calendar calendar, String timeFormatter, char dateType, Date baseTime){
        if(calendar==null) {
            switch(dateType){
                case 'Q':
                case 'M':
                    calendar = getCurrentMonthMinDate(baseTime, timeFormatter);
                    break;
                case 'D':
                case 'T':
                    calendar = getCurrentDate(baseTime,timeFormatter);
            }
        }
        return calendar;
    }

    /**
     * 根据日期取给定日期的季度或月度最后一天
     * @param calendar  给定日期
     * @param dateType  Q，M,D,T
     * @param calDateIndex  时刻
     * @return
     */
    private static Calendar fixedDate(Calendar calendar, char dateType, int calDateIndex){
        if(calDateIndex>0){
            if (dateType != '\0') {
                switch (dateType) {
                    case 'Q':
                        //取季度最后一天
                        calendar.add(Calendar.MONTH, 3);
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                        break;
                    case 'M':
                        //取月度最后一天
                        calendar.add(Calendar.MONTH, 1);
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                    case 'D':
                    case 'T':
                        break;
                }
            }
        }
        return calendar;
    }


    /**
     * 根据表达式获取日期
     * @param dateExpression
     * @return
     */
    public static Date explainDateExpression(String dateExpression){
        return calculate(dateExpression,null,0,null).getTime();
    }

    /**
     * 根据表达式获取日期
     * @param dateExpression
     * @return
     */
    public static Date explainDateExpression(String dateExpression,Date baseTime){
        return calculate(dateExpression,null,0,baseTime).getTime();
    }

    /**
     * 检查规则执行日期是否是当前日期
     * @param executeData
     * @return
     */
    public static boolean checkExecuteData(String executeData){
        if(!StringUtils.isEmpty(executeData)){
            Date execDate = explainDateExpression(executeData,null);
            if(DateUtils.formatDateTime(execDate).equals(DateUtils.formatDateTime(getCurrentDate(null)))){
                return true;
            }
        }
        return false;
    }


}
