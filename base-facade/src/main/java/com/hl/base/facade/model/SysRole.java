package com.hl.base.facade.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 通用-角色表
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@TableName("sys_role")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SysRole extends BaseModel<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 英文名称
     */
    @TableField("english_name")
    private String englishName;

    /**
     * 所属系统id
     */
    @TableField("system_id")
    private Integer systemId;

    /**
     * 角色类型
     */
    @TableField("role_type")
    private String roleType;

    /**
     * 是否系统数据，0是系统数据，1不是系统数据
     */
    @TableField("is_system_data")
    private Integer isSystemData;

    /**
     * 是否可用，1可用，0不可用
     */
    @TableField("is_usable")
    private Integer isUsable;

    /**
     * 删除标记，1表示正常，0表示已删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 数据范围，0仅本人可用，1按明细设计
     */
    @TableField("data_range")
    private Integer dataRange;

    /**
     * 备注
     */
    private String memo;

    /**
     * 数据插入人
     */
    @TableField("insert_operator")
    private String insertOperator;

    /**
     * 插入时间
     */
    @TableField("insert_datetime")
    private Date insertDatetime;

    /**
     * 更新人
     */
    @TableField("last_modify_operator")
    private String lastModifyOperator;

    /**
     * 更新时间
     */
    @TableField("last_modify_datetime")
    private Date lastModifyDatetime;

}
