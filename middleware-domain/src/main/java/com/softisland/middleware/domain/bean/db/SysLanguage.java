package com.softisland.middleware.domain.bean.db;

public class SysLanguage {
    private Integer id;

    private String name;

    private String code;

    private String description;

    private String nationalImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getNationalImg() {
        return nationalImg;
    }

    public void setNationalImg(String nationalImg) {
        this.nationalImg = nationalImg == null ? null : nationalImg.trim();
    }
}