package com.yhlo.oa.enums;

public enum OrderTypeEnum {

    NORMAL("N"), ROLES("R");

    private String orderType;

    OrderTypeEnum(String orderType){
        this.orderType = orderType;
    }

    public String getOrderType(){
        return this.orderType;
    }
}
