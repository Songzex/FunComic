package com.scy.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 
 * @TableName roles
 */
@TableName(value ="roles")
@Data
@Getter
@AllArgsConstructor
public class Roles implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer rolesid;

    /**
     * 角色值
     */
    private String rolesvalue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Integer getRolesid() {
        return rolesid;
    }

    /**
     * 主键
     */
    public void setRolesid(Integer rolesid) {
        this.rolesid = rolesid;
    }

    /**
     * 角色值
     */
    public String getRolesvalue() {
        return rolesvalue;
    }

    /**
     * 角色值
     */
    public void setRolesvalue(String rolesvalue) {
        this.rolesvalue = rolesvalue;
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
        Roles other = (Roles) that;
        return (this.getRolesid() == null ? other.getRolesid() == null : this.getRolesid().equals(other.getRolesid()))
            && (this.getRolesvalue() == null ? other.getRolesvalue() == null : this.getRolesvalue().equals(other.getRolesvalue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRolesid() == null) ? 0 : getRolesid().hashCode());
        result = prime * result + ((getRolesvalue() == null) ? 0 : getRolesvalue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rolesid=").append(rolesid);
        sb.append(", rolesvalue=").append(rolesvalue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}