package com.yhlo.oa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @create: 2022-04-14 10:56
 * @description:
 **/
@Data
@ApiModel("订单")
public class OrderVO {
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
}
