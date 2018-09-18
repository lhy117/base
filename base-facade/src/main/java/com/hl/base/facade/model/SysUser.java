package com.hl.base.facade.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 云调度-用户
 * </p>
 * @author liheyu
 * @since 2018-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor
@TableName("sys_user")
public class SysUser extends BaseModel<SysUser> {

	private static final long serialVersionUID = 1L;

	/**
	 * 登录名
	 */
	@TableField("login_name")
	private String loginName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 职工id
	 */
	@TableField("staff_id")
	private String staffId;
	/**
	 * 头像（URL)
	 */
	@TableField("head_pic")
	private String headPic;
	/**
	 * 有效期开始(用户)
	 */
	@TableField("validtime_start")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date validtimeStart;
	/**
	 * 用户有效期（Y:年M:月D:天）
	 */
	@TableField("expire_date")
	private String expireDate;
	/**
	 * 有效期结束(用户)
	 */
	@TableField("validtime_end")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date validtimeEnd;
	/**
	 * 备注
	 */
	private String remark;
}
