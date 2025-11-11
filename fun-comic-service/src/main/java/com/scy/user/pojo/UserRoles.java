package com.scy.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 
 * @author 24022
 * @TableName user_roles
 */
@Getter
@TableName(value ="user_roles")
@Data
public class UserRoles implements Serializable {
    /**
     * id
     * -- GETTER --
     *  id

     */
    @TableId
    private Integer rolesid;

    /**
     *
     * -- GETTER --
     *

     */
    private Integer userid;

    /**
     *
     * -- GETTER --
     *

     */
    private Integer status;

    /**
     *
     * -- GETTER --
     *

     */
    private String roles;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public void setRolesid(Integer rolesid) {
        this.rolesid = rolesid;
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
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     */
    public void setRoles(String roles) {
        this.roles = roles;
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
        UserRoles other = (UserRoles) that;
        return (this.getRolesid() == null ? other.getRolesid() == null : this.getRolesid().equals(other.getRolesid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRoles() == null ? other.getRoles() == null : this.getRoles().equals(other.getRoles()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRolesid() == null) ? 0 : getRolesid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRoles() == null) ? 0 : getRoles().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rolesid=").append(rolesid);
        sb.append(", userid=").append(userid);
        sb.append(", status=").append(status);
        sb.append(", roles=").append(roles);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}