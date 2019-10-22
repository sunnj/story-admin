package com.story.storyadmin.constant.enumtype;

/**
 * @date 2014-10-16 下午02:51:21
 */
public enum YNFlagStatusEnum {
	VALID("1","有效"),
	FAIL("0","无效");
	
	private YNFlagStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String desc;
	private String code;
	
	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}
	
	public String toString(){
		return code+","+desc;
	}
	
	/**
	 * 通过key 查找描述 方法
	 * @param key
	 */
	public static String getValueByKey(String key) {
		for (YNFlagStatusEnum pm : YNFlagStatusEnum.values()) {
			if (pm.getCode().equals(key)) {
				return pm.getDesc();
			}
		}
		return null;
	}
}
