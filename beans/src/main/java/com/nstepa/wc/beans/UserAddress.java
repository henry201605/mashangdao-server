package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户配送地址
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 姓名
     */
    private String username;
    /**
     * 性别
     */
    private String gender;
    /**
     * 联系电话
     */
    private String mobile;
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
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 街道,门牌号
     */
    private String street;
    /**
     * 标签
     */
    private Integer tag;
    /**
     * 是否为默认地址:0-默认；1-不默认
     */
    private Integer isDefaultAddress;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 加入时间
     */
    private Integer addTime;
    /**
     * 编辑时间
     */
    private Integer editTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getIsDefaultAddress() {
        return isDefaultAddress;
    }

    public void setIsDefaultAddress(Integer isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
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

    public Integer getEditTime() {
        return editTime;
    }

    public void setEditTime(Integer editTime) {
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
        ", id=" + id +
        ", userId=" + userId +
        ", username=" + username +
        ", gender=" + gender +
        ", mobile=" + mobile +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", address=" + address +
        ", street=" + street +
        ", tag=" + tag +
        ", isDefaultAddress=" + isDefaultAddress +
        ", status=" + status +
        ", addTime=" + addTime +
        ", editTime=" + editTime +
        "}";
    }
}
