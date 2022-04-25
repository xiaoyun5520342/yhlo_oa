package com.yhlo.oa.mapper;

import com.yhlo.oa.entity.OrderVO;

import java.util.List;

public interface OrderMapper {

    void insertOrder(OrderVO orderVO);

    List<OrderVO> queryOrderList();
}
