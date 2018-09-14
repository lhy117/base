package com.hl.base.facade.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 通用字典表
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor
@TableName("sys_dic")
public class SysDic extends BaseModel<SysDic> {

    private static final long serialVersionUID = 1L;

    /**
     * 字典值
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 逻辑删除标记0-启用，1禁用
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 所属字典类型
     */
    @TableField("dic_type")
    private String dicType;

    /**
     * 字典说明:字典说明或备注
     */
    private String remarks;

    /**
     * 字典拼音:字典值或名称的拼音
     */
    private String pinyin;

    /**
     * 字典助记码:字典值或名称对应的助记码
     */
    @TableField("mnemonic_code")
    private String mnemonicCode;

    /**
     * 字典上级代码:字典对应的上级代码，上级编码，或者所属系列编码
     */
    @TableField("parent_code")
    private String parentCode;

    /**
     * 英文:字典值或名称对应的英文名称
     */
    @TableField("english_name")
    private String englishName;

    /**
     * 编码:字典值或名称对应的编码
     */
    @TableField("name_or_code")
    private String nameOrCode;

    /**
     * 五笔助记码:字典值或名称对应的五笔助记码
     */
    @TableField("five_stroke_mnemonic_code")
    private String fiveStrokeMnemonicCode;

    /**
     * 单位:注：仪器设备字典表的独有字段
     */
    private String unit;

    /**
     * 有效标志:注：疾病字典表和中医字典表的独有字段
     */
    private Integer flag;

    /**
     * 中文简称:注：国家字典表的独有字段
     */
    @TableField("simplified_chinese")
    private String simplifiedChinese;

    /**
     * 2字符代码:注：国家字典表的独有字段
     */
    @TableField("char2_code")
    private String char2Code;

    /**
     * 3字符代码:注：国家字典表的独有字段
     */
    @TableField("char3_code")
    private String char3Code;

    /**
     * 数字代码:注：国家字典表的独有字段
     */
    @TableField("number_code")
    private String numberCode;

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
     * (ICD10)是否适用于院前急救（0适用，1不适用）
     */
    @TableField("is_apply_phemr")
    private Integer isApplyPhemr;

    /**
     * (ICD10)是否常见疾病（0常见，1不常见）
     */
    @TableField("is_common")
    private Integer isCommon;

    /**
     * 字典分类（普通=0、ICD10疾病=1、急救物资=2、药品分类=3等）
     */
    private String type;

    /**
     * (急救物资、药品)规格型号
     */
    private String specifications;

    /**
     * (急救物资、药品)单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;

    /**
     * (急救物资、药品)是否支持医保报销(0 支持，1不支持)
     */
    @TableField("is_reimbursement")
    private Integer isReimbursement;

    /**
     * (急救药品、救治措施)是否常用（0常用，1不常用）
     */
    @TableField("is_common_use")
    private Integer isCommonUse;

    /**
     * (救治措施)适用疾病（保留该字段先不填）
     */
    @TableField("apply_disease")
    private String applyDisease;

    /**
     * 排序
     */
    private Integer sort;
}
