package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 订单商品详情表
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class OrderFood implements Serializable {

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
     * 商铺ID
     */
    private Integer shopId;
    /**
     * 商铺名称
     */
    private String shopname;
    /**
     * 商品ID
     */
    private Integer foodId;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品封面
     */
    private String cover;
    /**
     * 原价
     */
    private BigDecimal originPrice;
    /**
     * 售价
     */
    private BigDecimal sellPrice;
    /**
     * 下单数量
     */
    private Integer number;


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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OrderFood{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", shopId=" + shopId +
        ", shopname=" + shopname +
        ", foodId=" + foodId +
        ", title=" + title +
        ", cover=" + cover +
        ", originPrice=" + originPrice +
        ", sellPrice=" + sellPrice +
        ", number=" + number +
        "}";
    }
}
