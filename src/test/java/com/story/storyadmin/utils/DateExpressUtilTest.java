package com.story.storyadmin.utils;

import org.assertj.core.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateExpressUtilTest {

    @Test
    public void calcDateExpress(){

        //汇总区间
        String q0="Q-0";
        Date[] q0Arr = DateExpressionUtil.explainDateRangeExpression(q0,"00:00:00/23:59:59", DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2019-01-01 00:00:00",DateUtils.formatDateTime(q0Arr[0]));
        Assert.assertEquals("2019-03-31 23:59:59",DateUtils.formatDateTime(q0Arr[1]));

        String q1="Q-1";
        Date[] q1Arr = DateExpressionUtil.explainDateRangeExpression(q1,"00:00:00/23:59:59", DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-10-01 00:00:00",DateUtils.formatDateTime(q1Arr[0]));
        Assert.assertEquals("2018-12-31 23:59:59",DateUtils.formatDateTime(q1Arr[1]));

        String q4q0="Q-4/Q-0";
        Date[] q4q0Arr = DateExpressionUtil.explainDateRangeExpression(q4q0,"00:00:00/23:59:59", DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-01-01 00:00:00",DateUtils.formatDateTime(q4q0Arr[0]));
        Assert.assertEquals("2019-03-31 23:59:59",DateUtils.formatDateTime(q4q0Arr[1]));

        String q1d1q1d1="Q-1-D-1/Q-1-D-1";
        Date[] q1d1q1d1Arr = DateExpressionUtil.explainDateRangeExpression(q1d1q1d1,"00:00:00/23:59:59", DateUtil.parse("2019-01-24"));
        Assert.assertEquals("2018-09-30 00:00:00",DateUtils.formatDateTime(q1d1q1d1Arr[0]));
        Assert.assertEquals("2018-12-30 23:59:59",DateUtils.formatDateTime(q1d1q1d1Arr[1]));

        String q1d1q0d2="Q-1-D-1/Q-0-D-2";
        Date[] q1d1q0d2Arr = DateExpressionUtil.explainDateRangeExpression(q1d1q0d2,"00:00:00/23:59:59", DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-09-30 00:00:00",DateUtils.formatDateTime(q1d1q0d2Arr[0]));
        Assert.assertEquals("2019-03-29 23:59:59",DateUtils.formatDateTime(q1d1q0d2Arr[1]));

        String m1="M-1";
        Date[] m1Arr = DateExpressionUtil.explainDateRangeExpression(m1,"00:00:00/23:59:59", DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-12-01 00:00:00",DateUtils.formatDateTime(m1Arr[0]));
        Assert.assertEquals("2018-12-31 23:59:59",DateUtils.formatDateTime(m1Arr[1]));

        String m1d1m0d2="M-1-D-1/M-0-D-2";
        Date[] m1d1m0d2Arr = DateExpressionUtil.explainDateRangeExpression(m1d1m0d2,"00:00:00/23:59:59", DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-11-30 00:00:00",DateUtils.formatDateTime(m1d1m0d2Arr[0]));
        Assert.assertEquals("2019-01-29 23:59:59",DateUtils.formatDateTime(m1d1m0d2Arr[1]));

        String d1="D-1";
        Date[] d1Arr = DateExpressionUtil.explainDateRangeExpression(d1,"00:00:00/23:59:59", DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-12-31 00:00:00",DateUtils.formatDateTime(d1Arr[0]));
        Assert.assertEquals("2018-12-31 23:59:59",DateUtils.formatDateTime(d1Arr[1]));

        //执行时间
        String expd0="D-0";
        Date expd0Result= DateExpressionUtil.explainDateExpression(expd0,DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2019-01-01 00:00:00",DateUtils.formatDateTime(expd0Result));

        String expm0d5="M-0+D+5";
        Date expm0d5Result= DateExpressionUtil.explainDateExpression(expm0d5,DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2019-01-06 00:00:00",DateUtils.formatDateTime(expm0d5Result));

        String expq0m1="Q-1+M+1";
        Date expq0m1Result= DateExpressionUtil.explainDateExpression(expq0m1,DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-11-01 00:00:00",DateUtils.formatDateTime(expq0m1Result));

        String expq0m1d4="Q-1+M+1+D+4";
        Date expq0m1d4Result= DateExpressionUtil.explainDateExpression(expq0m1d4,DateUtil.parse("2019-01-01"));
        Assert.assertEquals("2018-11-05 00:00:00",DateUtils.formatDateTime(expq0m1d4Result));
    }

    @Test
    public void calcTxnDateExpress(){
        String expq0m1d4="Q-0+T+3";
        Date expq0m1d4Result= DateExpressionUtil.explainDateExpression(expq0m1d4,DateUtil.parse("2019-04-11"));
        Assert.assertEquals("2019-04-03 00:00:00",DateUtils.formatDateTime(expq0m1d4Result));

        String expq0m1d5="Q-0+T+7";
        Date expq0m1d5Result= DateExpressionUtil.explainDateExpression(expq0m1d5,DateUtil.parse("2019-04-11"));
        Assert.assertEquals("2019-04-10 00:00:00",DateUtils.formatDateTime(expq0m1d5Result));
    }

}
