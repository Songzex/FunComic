package com.scy.common.modles;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author 24022
 * @TableName rido
 */
@Data

public class RidoDto implements Serializable {
    /**
     * 广播剧
     */
    @TableId
    private Integer ridoid;

    /**
     * 广播剧名字
     */
    private String ridoname;

    /**
     * 广播剧地址
     */
    private String ridourl;

    /**
     * 状态
     */
    private String ridostaus;

    /**
     * 简介
     */
    private String ridocontext;

    /**
     * 种类
     */
    private String ridotype;

    /**
     * 是否vip
     */
    private Integer ridoisvip;

    /**
     * 导演或者作者
     */
    private String ridoauthor;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 广播剧
     */
    public Integer getRidoid() {
        return ridoid;
    }

    /**
     * 广播剧
     */
    public void setRidoid(Integer ridoid) {
        this.ridoid = ridoid;
    }

    /**
     * 广播剧名字
     */
    public String getRidoname() {
        return ridoname;
    }

    /**
     * 广播剧名字
     */
    public void setRidoname(String ridoname) {
        this.ridoname = ridoname;
    }

    /**
     * 广播剧地址
     */
    public String getRidourl() {
        return ridourl;
    }

    /**
     * 广播剧地址
     */
    public void setRidourl(String ridourl) {
        this.ridourl = ridourl;
    }

    /**
     * 状态
     */
    public String getRidostaus() {
        return ridostaus;
    }

    /**
     * 状态
     */
    public void setRidostaus(String ridostaus) {
        this.ridostaus = ridostaus;
    }

    /**
     * 简介
     */
    public String getRidocontext() {
        return ridocontext;
    }

    /**
     * 简介
     */
    public void setRidocontext(String ridocontext) {
        this.ridocontext = ridocontext;
    }

    /**
     * 种类
     */
    public String getRidotype() {
        return ridotype;
    }

    /**
     * 种类
     */
    public void setRidotype(String ridotype) {
        this.ridotype = ridotype;
    }

    /**
     * 是否vip
     */
    public Integer getRidoisvip() {
        return ridoisvip;
    }

    /**
     * 是否vip
     */
    public void setRidoisvip(Integer ridoisvip) {
        this.ridoisvip = ridoisvip;
    }

    /**
     * 导演或者作者
     */
    public String getRidoauthor() {
        return ridoauthor;
    }

    /**
     * 导演或者作者
     */
    public void setRidoauthor(String ridoauthor) {
        this.ridoauthor = ridoauthor;
    }

}