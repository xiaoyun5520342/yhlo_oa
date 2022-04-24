package com.yhlo.oa.service.iml;

import com.yhlo.oa.entity.OrderVO;
import com.yhlo.oa.mapper.OrderMapper;
import com.yhlo.oa.service.NormalOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @create: 2022-04-24 16:25
 * @description:
 **/
@Service
public class NormalOrderServiceImpl implements NormalOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void saveOrder(OrderVO order) {
        orderMapper.insertOrder(order);
    }
}
