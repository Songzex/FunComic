package com.scy.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName roles_permisson
 */
@TableName(value ="roles_permission")
@Data
public class RolesPermisson implements Serializable {
    /**
     * 角色主键
     */
    @TableId
    private Integer roleid;


    private Integer permissionsid;

    /**
     * 可用性
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 角色主键
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 角色主键
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 权限主键
     */
    public Integer getPermissionsid() {
        return permissionsid;
    }

    /**
     * 权限主键
     */
    public void setPermissionsid(Integer permissionsid) {
        this.permissionsid = permissionsid;
    }

    /**
     * 可用性
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 可用性
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        RolesPermisson other = (RolesPermisson) that;
        return (this.getRoleid() == null ? other.getRoleid() == null : this.getRoleid().equals(other.getRoleid()))
            && (this.getPermissionsid() == null ? other.getPermissionsid() == null : this.getPermissionsid().equals(other.getPermissionsid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleid() == null) ? 0 : getRoleid().hashCode());
        result = prime * result + ((getPermissionsid() == null) ? 0 : getPermissionsid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleid=").append(roleid);
        sb.append(", permissionsid=").append(permissionsid);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}