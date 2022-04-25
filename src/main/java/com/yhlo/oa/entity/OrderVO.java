package com.yhlo.oa.entity;

import lombok.Data;

/**
 * @create: 2022-04-14 10:56
 * @description:
 **/
@Data
public class OrderVO {
    private String orderNo;
    private String status;
    private String createTime;
    private String createBy;
}
