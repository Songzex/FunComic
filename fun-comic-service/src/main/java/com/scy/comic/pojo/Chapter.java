package com.scy.comic.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName chapter
 */
@TableName(value ="gouger_chapter")
public class Chapter implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * id
     */
    private Integer chapterid;

    /**
     * 所属漫画id
     */
    private Integer comicid;

    /**
     * 章节名字
     */
    private String title;

    /**
     * 是否免费
     */
    private String isfree;

    /**
     * 漫画Url
     */
    private String imgurl;

    /**
     * 描述
     */
    private String descp;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 价钱
     */
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * id
     */
    public Integer getChapterid() {
        return chapterid;
    }

    /**
     * id
     */
    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    /**
     * 所属漫画id
     */
    public Integer getComicid() {
        return comicid;
    }

    /**
     * 所属漫画id
     */
    public void setComicid(Integer comicid) {
        this.comicid = comicid;
    }

    /**
     * 章节名字
     */
    public String getTitle() {
        return title;
    }

    /**
     * 章节名字
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 是否免费
     */
    public String getIsfree() {
        return isfree;
    }

    /**
     * 是否免费
     */
    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    /**
     * 漫画Url
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 漫画Url
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * 描述
     */
    public String getDesc() {
        return descp;
    }

    /**
     * 描述
     */
    public void setDesc(String desc) {
        this.descp = desc;
    }

    /**
     * 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 价钱
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价钱
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", chapterid=").append(chapterid);
        sb.append(", comicid=").append(comicid);
        sb.append(", title=").append(title);
        sb.append(", isfree=").append(isfree);
        sb.append(", imgurl=").append(imgurl);
        sb.append(", desc=").append(descp);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}