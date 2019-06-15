package com.story.storyadmin.domain.vo.sysmgr;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String avatar;

}
