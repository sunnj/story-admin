package com.story.storyadmin.config.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author admin
 *
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long userId;          // 主键ID
    public String account;      // 账号
    public String name;         // 姓名
	public Long orgId;      // 组织ID


	public LoginUser() {
	}

	public LoginUser(String account, Long orgId) {
		this.account=account;
		this.orgId=orgId;
	}

}
