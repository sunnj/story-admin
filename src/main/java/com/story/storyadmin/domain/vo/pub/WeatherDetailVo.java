package com.story.storyadmin.domain.vo.pub;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 天气明细VO
 */
@Data
public class WeatherDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date date;
    private String week;
    private String wea;
    private String weaImg;
    private String tem1;
    private String tem2;

}
