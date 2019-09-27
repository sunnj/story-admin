package com.story.storyadmin.domain.vo.pub;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 天气VO
 */
@Data
public class WeatherVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cityId;
    private String province;
    private String city;

    //天气明细
    List<WeatherDetailVo> dateList;
}
