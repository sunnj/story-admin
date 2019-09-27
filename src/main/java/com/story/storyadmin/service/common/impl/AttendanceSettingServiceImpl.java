package com.story.storyadmin.service.common.impl;

import com.story.storyadmin.service.common.AttendanceSettingService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AttendanceSettingServiceImpl implements AttendanceSettingService {
    @Override
    public Date getNextNumMarketDay(Date date, Integer days) {
        return null;
    }
}
