package com.softisland.middleware.domain.bean.db;

public class SysRoleMenu {
    private Integer id;

    private Integer roleId;

    private Integer menuId;

    private Short manageFlag;

    private Short status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Short getManageFlag() {
        return manageFlag;
    }

    public void setManageFlag(Short manageFlag) {
        this.manageFlag = manageFlag;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}