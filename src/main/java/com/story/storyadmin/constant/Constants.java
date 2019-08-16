package com.story.storyadmin.constant;

/**
 * 常量类
 */
public final class Constants {

	/**********************通用代码 start***********************/
	//密码错误
	public static final Integer PASSWORD_CHECK_INVALID = 40001;

	//验证成功
	public static final Integer TOKEN_CHECK_SUCCESS = 20000;

	//服务端错误
	public static final Integer SERVER_ERROR = 50000;
	//参数不完整
	public static final Integer PARAMETERS_MISSING = 50002;

	//非法token
	public static final Integer TOKEN_CHECK_ILLEFALITY_ERROR = 50008;
	//其他客户端登录
	public static final Integer TOKEN_CHECK_OTHER_LOGIN = 50012;
	//token 过期
	public static final Integer TOKEN_CHECK_STALE_DATED = 50014;

	//获取条线的角色标识
	public static final String ERP_ROLE_BL = "jr_scm_bl";
	//获取组织的角色标识
	public static final String ERP_ROLE_ORG = "jr_scm_org";
	/**********************通用代码 end***********************/

	/**
	 * 过期时间
	 */
	public static class ExpireTime {
		private ExpireTime() {
		}
		public static final int TEN_SEC =  10;//10s
		public static final int THIRTY_SEC =  30;//30s
		public static final int ONE_MINUTE =  60;//一分钟
		public static final int THIRTY_MINUTES =  60 * 30;//30分钟
		public static final int ONE_HOUR = 60 * 60;//一小时
		public static final int THREE_HOURS = 60 * 60 * 3;//三小时
		public static final int TWELVE_HOURS =  60 * 60 * 12;//十二小时，单位s
		public static final int ONE_DAY = 60 * 60 * 24;//二十四小时
	}
}
