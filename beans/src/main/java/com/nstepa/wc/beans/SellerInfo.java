package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商铺信息表
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class SellerInfo implements Serializable {

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
     * 商铺所属的TAG
     */
    private String tag;
    /**
     * 商品名称
     */
    private String shopname;
    /**
     * 联系人
     */
    private String contactMan;
    /**
     * 联系电话
     */
    private String contactMobile;
    /**
     * 门店类型
     */
    private Integer cateid;
    /**
     * 营业开始时间
     */
    private Integer beginTime;
    /**
     * 营业结束时间
     */
    private Integer endTime;
    /**
     * 门店图片
     */
    private String storeImg;
    /**
     * 店内图片
     */
    private String instoreImg;
    /**
     * logo图片
     */
    private String logoImg;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 街道/门牌号
     */
    private String street;
    /**
     * 外卖电话
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 其他图片
     */
    private String images;
    /**
     * 商家公告
     */
    private String notice;
    /**
     * 平均评分
     */
    private Float score;
    /**
     * 配送时间
     */
    private String sendTime;
    /**
     * 餐盒费用
     */
    private BigDecimal boxCost;
    /**
     * 配送费用
     */
    private BigDecimal sendCost;
    /**
     * 起送消费
     */
    private BigDecimal floorSendCost;


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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getInstoreImg() {
        return instoreImg;
    }

    public void setInstoreImg(String instoreImg) {
        this.instoreImg = instoreImg;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
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

    public BigDecimal getFloorSendCost() {
        return floorSendCost;
    }

    public void setFloorSendCost(BigDecimal floorSendCost) {
        this.floorSendCost = floorSendCost;
    }

    @Override
    public String toString() {
        return "SellerInfo{" +
        ", id=" + id +
        ", shopId=" + shopId +
        ", tag=" + tag +
        ", shopname=" + shopname +
        ", contactMan=" + contactMan +
        ", contactMobile=" + contactMobile +
        ", cateid=" + cateid +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", storeImg=" + storeImg +
        ", instoreImg=" + instoreImg +
        ", logoImg=" + logoImg +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", address=" + address +
        ", street=" + street +
        ", mobile=" + mobile +
        ", email=" + email +
        ", images=" + images +
        ", notice=" + notice +
        ", score=" + score +
        ", sendTime=" + sendTime +
        ", boxCost=" + boxCost +
        ", sendCost=" + sendCost +
        ", floorSendCost=" + floorSendCost +
        "}";
    }
}
