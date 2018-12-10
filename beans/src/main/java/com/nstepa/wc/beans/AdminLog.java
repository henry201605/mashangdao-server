package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 管理员操作日志
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class AdminLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 服务器ID
     */
    private Integer serverId;
    /**
     * 管理员ID
     */
    private Integer adminId;
    /**
     * 管理者名字
     */
    private String username;
    /**
     * 控制器
     */
    private String controller;
    /**
     * 动作
     */
    private String action;
    /**
     * 详情
     */
    private String detail;
    /**
     * IP
     */
    private String ip;
    /**
     * 操作结果
     */
    private String result;
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

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
        return "AdminLog{" +
        ", id=" + id +
        ", serverId=" + serverId +
        ", adminId=" + adminId +
        ", username=" + username +
        ", controller=" + controller +
        ", action=" + action +
        ", detail=" + detail +
        ", ip=" + ip +
        ", result=" + result +
        ", status=" + status +
        ", addTime=" + addTime +
        "}";
    }
}
