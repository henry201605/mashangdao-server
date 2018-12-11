package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 订单--评论
 * </p>
 *
 * @author henry
 * @since 2018-12-10
 */
public class OrderComment implements Serializable {

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
     * 评论内容
     */
    private String content;
    /**
     * 评论图片
     */
    private String images;
    /**
     * 加入时间
     */
    private Integer addTime;
    /**
     * 回复的ID路径:1/2/3/5
     */
    private String path;
    /**
     * 点赞次数
     */
    private Integer numPraise;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 引用的评论ID
     */
    private Integer reCommentId;
    /**
     * 点赞用户的IDs
     */
    private String likeIds;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getNumPraise() {
        return numPraise;
    }

    public void setNumPraise(Integer numPraise) {
        this.numPraise = numPraise;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReCommentId() {
        return reCommentId;
    }

    public void setReCommentId(Integer reCommentId) {
        this.reCommentId = reCommentId;
    }

    public String getLikeIds() {
        return likeIds;
    }

    public void setLikeIds(String likeIds) {
        this.likeIds = likeIds;
    }

    @Override
    public String toString() {
        return "OrderComment{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", content=" + content +
        ", images=" + images +
        ", addTime=" + addTime +
        ", path=" + path +
        ", numPraise=" + numPraise +
        ", status=" + status +
        ", reCommentId=" + reCommentId +
        ", likeIds=" + likeIds +
        "}";
    }
}
