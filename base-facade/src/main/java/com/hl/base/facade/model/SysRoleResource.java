package com.hl.base.facade.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 通用-角色资源关系
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@TableName("sys_role_resource")
@Data
@AllArgsConstructor
public class SysRoleResource implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 资源编号
     */
    @TableField("resource_id")
    private Integer resourceId;

    /**
     * 角色编号
     */
    @TableField("role_id")
    private Integer roleId;

}
