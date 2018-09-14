package com.hl.base.util.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回结果
 * @author anonymity
 * @date 2018-08-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7268040542410707954L;

	/**
	 * 是否成功
	 */
	private boolean success = false;

	/**
	 * 返回信息
	 */
	private String message;

	/**
	 * 装在数据
	 */
	private Object data;

	/**
	 * 错误代码
	 */
	private int code;
	
	/**
	 * @param success 是否成功
	 * @param message 返回的消息
	 */
	public Result(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	/**
	 * 
	 * @param success 是否成功
	 */
	public Result(boolean success, Object data){
		this.success = success;
		this.data = data;
	}
}


