package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 商家的食物分类
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class GoodsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商铺ID
     */
    private Integer shopId;
    /**
     * 分类类型
     */
    private String name;
    /**
     * 描述
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "GoodsCategory{" +
        ", id=" + id +
        ", shopId=" + shopId +
        ", name=" + name +
        ", desc=" + desc +
        ", status=" + status +
        ", addTime=" + addTime +
        "}";
    }
}
