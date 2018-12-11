package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 商家登录
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class Seller implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品名称
     */
    private String shopname;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 登录信息
     */
    private String loginInfo;
    /**
     * 登录错误次数
     */
    private Integer numLoginError;
    /**
     * 锁定登录时间
     */
    private Integer timeLoginLock;
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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Integer getNumLoginError() {
        return numLoginError;
    }

    public void setNumLoginError(Integer numLoginError) {
        this.numLoginError = numLoginError;
    }

    public Integer getTimeLoginLock() {
        return timeLoginLock;
    }

    public void setTimeLoginLock(Integer timeLoginLock) {
        this.timeLoginLock = timeLoginLock;
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
        return "Seller{" +
        ", id=" + id +
        ", shopname=" + shopname +
        ", mobile=" + mobile +
        ", password=" + password +
        ", email=" + email +
        ", loginInfo=" + loginInfo +
        ", numLoginError=" + numLoginError +
        ", timeLoginLock=" + timeLoginLock +
        ", status=" + status +
        ", addTime=" + addTime +
        "}";
    }
}
