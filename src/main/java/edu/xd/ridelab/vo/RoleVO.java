package edu.xd.ridelab.vo;

import java.util.Date;

/**
 * @Author ChenXiang
 * @Date 2018/08/12,15:25
 */
public class RoleVO {
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//    /**
//     * 修改时间
//     */
//    private Date modifyTime;

    public String getName() {
        return roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

}
