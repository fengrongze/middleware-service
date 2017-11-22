package com.softisland.middleware.domain.util.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 交易地址对象返回
 */
public class BaseResult implements Serializable{
    private String resultCode="fail";
    private String message="fail";
    //扩展字段,错误详情信息在里面
    public Map<String, Object> getExtern() {
        return extern;
    }

    public void setExtern(Map<String, Object> extern) {
        this.extern = extern;
    }

    private Map<String, Object> extern = new HashMap<>();


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
