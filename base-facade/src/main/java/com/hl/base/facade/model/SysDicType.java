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
 * 通用字典类型表
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor
@TableName("sys_dic_type")
public class SysDicType extends BaseModel<SysDicType> {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 上级字典编码
     */
    @TableField("parent_code")
    private String parentCode;

    @TableField("type_code")
    private String typeCode;

    /**
     * 类别名称
     */
    @TableField("type_name")
    private String typeName;

    /**
     * 标准来源
     */
    @TableField("source_from")
    private String sourceFrom;

    /**
     * 新增人
     */
    @TableField("insert_operator")
    private String insertOperator;

    /**
     * 新增时间
     */
    @TableField("insert_datetime")
    private Date insertDatetime;

    /**
     * 最后修改人
     */
    @TableField("last_modify_operator")
    private String lastModifyOperator;

    /**
     * 最后修改时间
     */
    @TableField("last_modify_datetime")
    private Date lastModifyDatetime;

    /**
     * 逻辑删除标志:0，正常，1删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 排序
     */
    private Integer sort;
}
