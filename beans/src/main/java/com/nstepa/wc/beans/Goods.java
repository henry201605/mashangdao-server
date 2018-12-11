package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 菜品信息表
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商店ID
     */
    private Integer shopId;
    /**
     * 分类ID
     */
    private Integer cateId;
    /**
     * 食品名字
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 食品封面图
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
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 点赞
     */
    private Integer like;
    /**
     * 限购数量
     */
    private Integer limitNum;
    /**
     * 规格选项
     */
    private String option;
    /**
     * 总的销量
     */
    private Integer totalSales;
    /**
     * 月销量
     */
    private Integer monthSales;
    /**
     * 好评率
     */
    private Float praiseRate;
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

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(Integer monthSales) {
        this.monthSales = monthSales;
    }

    public Float getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(Float praiseRate) {
        this.praiseRate = praiseRate;
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
        return "Goods{" +
        ", id=" + id +
        ", shopId=" + shopId +
        ", cateId=" + cateId +
        ", title=" + title +
        ", desc=" + desc +
        ", cover=" + cover +
        ", originPrice=" + originPrice +
        ", sellPrice=" + sellPrice +
        ", discount=" + discount +
        ", like=" + like +
        ", limitNum=" + limitNum +
        ", option=" + option +
        ", totalSales=" + totalSales +
        ", monthSales=" + monthSales +
        ", praiseRate=" + praiseRate +
        ", status=" + status +
        ", addTime=" + addTime +
        "}";
    }
}
