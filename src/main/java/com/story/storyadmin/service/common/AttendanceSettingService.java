package com.story.storyadmin.service.common;

import java.util.Date;

public interface AttendanceSettingService {

    /**
     * 获取指定日期后的第几个交易日
     * @param date
     * @param days
     * @return
     */
    Date getNextNumMarketDay(Date date, Integer days);
}
