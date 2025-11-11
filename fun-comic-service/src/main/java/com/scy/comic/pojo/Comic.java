package com.scy.comic.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * @TableName comic
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value ="gouger_comic")
public class Comic implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 漫画id
     */
    private String comicid;

    /**
     * 名字
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 简介描述
     */
    private String descriptions;

    /**
     * 封面
     */
    private String coverimage;

    /**
     * 标签 [ 韩漫 日漫 等 ]
     */
    private String tags;

    /**
     * 状态  [更新中   完结]
     */
    private String status;

    /**
     * 类型[ 付费1 免费0]
     */
    private String type;

    /**
     * 价钱
     */
    private BigDecimal price;



    private Date createtime;
    private Date updatetime;



    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 漫画id
     */
    public String getComicid() {
        return comicid;
    }

    /**
     * 漫画id
     */
    public void setComicid(String comicid) {
        this.comicid = comicid;
    }

    /**
     * 名字
     */
    public String getTitle() {
        return title;
    }

    /**
     * 名字
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 简介描述
     */
    public String getDescription() {
        return descriptions;
    }

    /**
     * 简介描述
     */
    public void setDescription(String description) {
        this.descriptions = description;
    }

    /**
     * 封面
     */
    public String getCoverimage() {
        return coverimage;
    }

    /**
     * 封面
     */
    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    /**
     * 标签 [ 韩漫 日漫 等 ]
     */
    public String getTags() {
        return tags;
    }

    /**
     * 标签 [ 韩漫 日漫 等 ]
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 状态  [更新中   完结]
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态  [更新中   完结]
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 类型[ 付费1 免费0]
     */
    public String getType() {
        return type;
    }

    /**
     * 类型[ 付费1 免费0]
     */
    public void setType(String type) {
        this.type = type;
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
        Comic other = (Comic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getComicid() == null ? other.getComicid() == null : this.getComicid().equals(other.getComicid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCoverimage() == null ? other.getCoverimage() == null : this.getCoverimage().equals(other.getCoverimage()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getComicid() == null) ? 0 : getComicid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCoverimage() == null) ? 0 : getCoverimage().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", comicid=").append(comicid);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", description=").append(descriptions);
        sb.append(", coverimage=").append(coverimage);
        sb.append(", tags=").append(tags);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}