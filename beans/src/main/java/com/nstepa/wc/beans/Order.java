package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单主表
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class Order implements Serializable {

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
     * 商铺ID
     */
    private Integer shopId;
    /**
     * 餐盒费
     */
    private BigDecimal boxCost;
    /**
     * 配送费
     */
    private BigDecimal sendCost;
    /**
     * 总价
     */
    private BigDecimal totalMoney;
    /**
     * 优惠金额
     */
    private BigDecimal discountMoney;
    /**
     * 红包ID
     */
    private String couponId;
    /**
     * 红包满减金额
     */
    private BigDecimal couponMoney;
    /**
     * 实付金额
     */
    private BigDecimal payMoney;
    /**
     * 支付方式
     */
    private Integer payWay;
    /**
     * 限定的时间
     */
    private Integer demandTime;
    /**
     * 加入时间
     */
    private Integer addTime;
    /**
     * 状态
     */
    private Integer status;


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

    public BigDecimal getBoxCost() {
        return boxCost;
    }

    public void setBoxCost(BigDecimal boxCost) {
        this.boxCost = boxCost;
    }

    public BigDecimal getSendCost() {
        return sendCost;
    }

    public void setSendCost(BigDecimal sendCost) {
        this.sendCost = sendCost;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(Integer demandTime) {
        this.demandTime = demandTime;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", shopId=" + shopId +
        ", boxCost=" + boxCost +
        ", sendCost=" + sendCost +
        ", totalMoney=" + totalMoney +
        ", discountMoney=" + discountMoney +
        ", couponId=" + couponId +
        ", couponMoney=" + couponMoney +
        ", payMoney=" + payMoney +
        ", payWay=" + payWay +
        ", demandTime=" + demandTime +
        ", addTime=" + addTime +
        ", status=" + status +
        "}";
    }
}
