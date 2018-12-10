package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 通用的分类表
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类类型
     */
    private Integer type;
    /**
     * 分类名字
     */
    private String name;
    /**
     * 图片
     */
    private String cover;
    /**
     * 上一级ID
     */
    private Integer parentId;
    /**
     * 简单的描述
     */
    private String desc;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 加入时间
     */
    private Integer addTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Category{" +
        ", id=" + id +
        ", type=" + type +
        ", name=" + name +
        ", cover=" + cover +
        ", parentId=" + parentId +
        ", desc=" + desc +
        ", status=" + status +
        ", addTime=" + addTime +
        "}";
    }
}
