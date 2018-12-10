package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 订单支付表
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class Pay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 商店ID
     */
    private Integer shopId;
    /**
     * 支付总数
     */
    private BigDecimal totalMoney;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 加入时间
     */
    private Integer addTime;
    /**
     * 处理时间
     */
    private Integer dealTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
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

    public Integer getDealTime() {
        return dealTime;
    }

    public void setDealTime(Integer dealTime) {
        this.dealTime = dealTime;
    }

    @Override
    public String toString() {
        return "Pay{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", shopId=" + shopId +
        ", totalMoney=" + totalMoney +
        ", status=" + status +
        ", addTime=" + addTime +
        ", dealTime=" + dealTime +
        "}";
    }
}
