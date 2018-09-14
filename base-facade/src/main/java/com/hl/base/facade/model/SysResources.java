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
 * 通用-功能资源表
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@TableName("sys_resources")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SysResources extends BaseModel<SysResources> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统资源名称
     */
    private String name;

    /**
     * 资源code
     */
    private String code;

    /**
     * 所属系统编号
     */
    @TableField("system_id")
    private Integer systemId;

    /**
     * 父级资源编号
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 备注
     */
    private String memo;

    /**
     * 资源类型，0表示菜单，1表示按钮，2表示访问资源
     */
    private Integer type;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 资源的级别，用1~4表示，不能超过四级
     */
    private Integer level;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 新增人
     */
    @TableField("insert_operator")
    private String insertOperator;

    /**
     * 插入时间
     */
    @TableField("insert_datetime")
    private Date insertDatetime;

    /**
     * 最后更新人
     */
    @TableField("last_modify_operator")
    private String lastModifyOperator;

    /**
     * 最后更新时间
     */
    @TableField("last_modify_datetime")
    private Date lastModifyDatetime;

    /**
     * 删除标记，1标识正常，0标识已删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer seq;

}
