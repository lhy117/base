package com.hl.base.util.constants;

/**
 * 是否枚举类
 * @author liheyu
 * @date 2018-08-02
 */
public enum YesAndNoType {
	/**
	 * 是
	 */
	YES(1),
	/**
	 * 否
	 */
	NO( 0);
	private int value;

	private YesAndNoType( int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
