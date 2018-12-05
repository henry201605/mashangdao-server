package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Data
@TableName("sys_user")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 微信openId
     */
    @TableField("user_key")
    private String userKey;
    /**
     * 微信昵称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 家庭区域表id
     */
    @TableField("home_area")
    private Integer homeArea;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注
     */
    private String note;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_uid")
    private Integer createUid;

//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUserKey() {
//        return userKey;
//    }
//
//    public void setUserKey(String userKey) {
//        this.userKey = userKey;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public Integer getHomeArea() {
//        return homeArea;
//    }
//
//    public void setHomeArea(Integer homeArea) {
//        this.homeArea = homeArea;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Integer getCreateUid() {
//        return createUid;
//    }
//
//    public void setCreateUid(Integer createUid) {
//        this.createUid = createUid;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//        ", id=" + id +
//        ", userKey=" + userKey +
//        ", userName=" + userName +
//        ", telephone=" + telephone +
//        ", homeArea=" + homeArea +
//        ", address=" + address +
//        ", note=" + note +
//        ", createTime=" + createTime +
//        ", createUid=" + createUid +
//        "}";
//    }
}
