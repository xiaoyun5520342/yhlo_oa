package com.yhlo.oa.service;

import com.yhlo.oa.entity.OrderVO;

import java.util.List;

public interface NormalOrderService {

    void saveOrder(OrderVO order);

    List<OrderVO> queryOrderList();
}
