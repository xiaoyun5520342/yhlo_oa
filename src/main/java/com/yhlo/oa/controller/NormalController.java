package com.yhlo.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.yhlo.oa.entity.OrderVO;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @create: 2022-04-12 15:40
 * @description:
 **/
@Slf4j
@RestController
public class NormalController implements Initializable {

    public TableView<OrderVO> orderList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("进入多角订单！");
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo("11111");
        orderVO.setStatus("有效");
        orderVO.setCreateBy("张三");
        orderVO.setCreateTime("2022-04-01");

        OrderVO orderVO2 = new OrderVO();
        orderVO2.setOrderNo("222222");
        orderVO2.setStatus("无效");
        orderVO2.setCreateBy("李四");
        orderVO2.setCreateTime("2022-04-11");

        List<OrderVO> list = new ArrayList<>();
        list.add(orderVO);
        list.add(orderVO2);
        // 这里应该从iotdb数据库中加载数据
        ObservableList<OrderVO> items = orderList.getItems();
        items.clear();
        items.addAll(list);

        orderList.setRowFactory(tv -> {
            TableRow<OrderVO> row = new TableRow<>();
            tv.setOnMouseClicked(event -> {
                OrderVO order = tv.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 1 && null != order) {
                    this.getDetailInfo(order);
                }
            });
            return row;
        });

        ObservableList<TableColumn<OrderVO, ?>> columns = orderList.getColumns();
        columns.clear();
        // 开始将列的值与当前的javabean的属性进行绑定
        TableColumn<OrderVO, Object> columne1 = new TableColumn<OrderVO, Object>("订单编号");
        TableColumn<OrderVO, Object> columne2 = new TableColumn<OrderVO, Object>("状态");
        TableColumn<OrderVO, Object> columne3 = new TableColumn<OrderVO, Object>("创建人");
        TableColumn<OrderVO, Object> columne4 = new TableColumn<OrderVO, Object>("创建时间");
        columne1.setCellValueFactory(new PropertyValueFactory<OrderVO, Object>("orderNo"));
        columne2.setCellValueFactory(new PropertyValueFactory<OrderVO, Object>("status"));
        columne3.setCellValueFactory(new PropertyValueFactory<OrderVO, Object>("createBy"));
        columne4.setCellValueFactory(new PropertyValueFactory<OrderVO, Object>("createTime"));

        columns.add(columne1);
        columns.add(columne2);
        columns.add(columne3);
        columns.add(columne4);
    }

    public TextField orderNo;
    public TextField status;
    public TextField createBy;
    public TextField createTime;
    private void getDetailInfo(OrderVO order) {
        orderNo.setText(order.getOrderNo());
        status.setText(order.getStatus());
        createBy.setText(order.getCreateBy());
        createTime.setText(order.getCreateTime());
    }
}
