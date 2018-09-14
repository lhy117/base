package com.hl.base.facade.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 云调度-配置
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:23:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor
@TableName("sys_config")
public class SysConfig extends BaseModel<SysConfig> implements IExcelModel{

    private static final long serialVersionUID = 1L;

    /**
     * 编码id【码表】
     */
    @Excel(name = "配置key", isImportField = "true_cfg")
    @TableField("data_key")
    @Length(max = 36, message = "配置不能超过36位")
    @NotBlank(message="配置key不能为空")
    private String dataKey;

    /**
     * 值
     */
    @Excel(name = "配置value", isImportField = "true_cfg")
    @TableField("data_value")
    @NotBlank(message="配置value不能为空")
    private String dataValue;

    /**
     * 机构id
     */
    @Excel(name = "所属中心")
    @TableField("org_id")
    private String orgId;

    /**
     * 数据类型（1车辆等级，2其他）
     */
    @Excel(name = "配置类型", replace={ "车辆等级_1", "其他_2" }, isImportField = "true_cfg")
    private Integer type;

    /**
     * 单位
     */
    @Excel(name = "配置项单位")
    private String unit;

    /**
     * 说明
     */
    @Excel(name = "配置项说明", isImportField = "true_cfg",  dict = "DIC_0004")
    @NotBlank(message="配置项说明不能为空")
    private String notes;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标签类型
     */
    private String label;
    
    /**
     * 导入配置项错误信息
     */
    @TableField(exist = false)
    private String errorMsg;

}
