package com.hl.base.facade.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统基础模型
 * </p>
 * @author liheyu
 * @date 2018-07-26 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseModel<T extends Model<T>> extends Model<T>{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private String id;
    /**
     * 创建人编码
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新人编码
     */
    @TableField("update_user")
    private String updateUser;
    /**
     * 更新时间
     */
    @TableField("update_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 状态(1:有效 0 :无效 2:软删除)
     */
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
