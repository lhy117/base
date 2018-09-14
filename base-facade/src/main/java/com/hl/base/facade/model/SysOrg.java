package com.hl.base.facade.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 机构管理（中心、分站）
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor
@TableName("sys_org")
public class SysOrg extends BaseModel<SysOrg> {

    private static final long serialVersionUID = 1L;

    /**
     * 机构名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 机构编码（自定义编码）
     */
    @TableField("org_code")
    private String orgCode;

    /**
     * 机构类型（1中心，2分站）
     */
    @TableField("org_type")
    private String orgType;

    /**
     * 地区编码
     */
    @TableField("region_id")
    private String regionId;

    /**
     * 上级机构ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 组织机构代码/执照
     */
    private String license;

    /**
     * 机构地址
     */
    @TableField("org_addr")
    private String orgAddr;

    /**
     * 纬度
     */
    private Double xcoordition;

    /**
     * 经度
     */
    private Double ycoordition;

    /**
     * 联系电话1
     */
    private String telephone1;

    /**
     * 联系电话2
     */
    private String telephone2;

    /**
     * 机构介绍
     */
    private String introduce;

    /**
     * 分站类型（当机构类型是分站时需要填写，码表）
     */
    @TableField("station_type")
    private String stationType;

    /**
     * 分站电脑mac地址（当机构类型是分站时需要填写）
     */
    @TableField("station_mac")
    private String stationMac;

    /**
     * 备注
     */
    private String remark;
}
