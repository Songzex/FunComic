package com.scy.user.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user_paycomic
 */
//@TableName(value ="gouger_chapter")
public class UserPaycomic implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private Integer comicid;

    /**
     * 【全本  单章】
     */
    private String type;

    /**
     * 购买的主键章节
     */
    private Integer chapterid;

    /**
     * 
     */
    private Date paytime;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 
     */
    public Integer getComicid() {
        return comicid;
    }

    /**
     * 
     */
    public void setComicid(Integer comicid) {
        this.comicid = comicid;
    }

    /**
     * 【全本  单章】
     */
    public String getType() {
        return type;
    }

    /**
     * 【全本  单章】
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 购买的章节
     */
    public Integer getChapterid() {
        return chapterid;
    }

    /**
     * 购买的章节
     */
    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    /**
     * 
     */
    public Date getPaytime() {
        return paytime;
    }

    /**
     * 
     */
    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserPaycomic other = (UserPaycomic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getComicid() == null ? other.getComicid() == null : this.getComicid().equals(other.getComicid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getChapterid() == null ? other.getChapterid() == null : this.getChapterid().equals(other.getChapterid()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getComicid() == null) ? 0 : getComicid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getChapterid() == null) ? 0 : getChapterid().hashCode());
        result = prime * result + ((getPaytime() == null) ? 0 : getPaytime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", comicid=").append(comicid);
        sb.append(", type=").append(type);
        sb.append(", chapterid=").append(chapterid);
        sb.append(", paytime=").append(paytime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}