package com.yhlo.oa.controller;

import com.yhlo.oa.entity.KeyList;
import com.yhlo.oa.entity.OrderVO;
import com.yhlo.oa.service.NormalOrderService;
import com.yhlo.oa.util.DataTypeWrapper;
import com.yhlo.oa.util.NodeUtil;
import com.yhlo.oa.util.ResultUtil;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
 * @description: 一般订单
 **/
@Slf4j
@RestController
public class NormalController implements Initializable {

    public TextField orderNo;
    public TextField status;
    public TextField createBy;
    public TextField createTime;
    public TableView<OrderVO> orderList;

    private NormalOrderService normalOrderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("进入多角订单！");
        // 这里应该从iotdb数据库中加载数据
        ObservableList<OrderVO> items = orderList.getItems();
        items.clear();
        items.addAll(getDataList());

        orderList.setRowFactory(tv -> {
            tv.setOnMouseClicked(event -> {
                OrderVO order = tv.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 1 && null != order) {
                    this.getDetailInfo(order);
                }
            });
            return new TableRow<>();
        });

        ObservableList<TableColumn<OrderVO, ?>> columns = orderList.getColumns();
        columns.clear();
        // 开始将列的值与当前的javabean的属性进行绑定
        List<KeyList> keyList = DataTypeWrapper.getKeyList(OrderVO.class);
        keyList.stream().forEach(e -> {
            TableColumn<OrderVO, Object> column = new TableColumn();
            column.setText(e.getLabel());
            column.setCellValueFactory(new PropertyValueFactory(e.getKey()));
            columns.add(column);
        });
    }

    private List<OrderVO> getDataList() {
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
        return list;
    }

    private void getDetailInfo(OrderVO order) {
        orderNo.setText(order.getOrderNo());
        status.setText(order.getStatus());
        createBy.setText(order.getCreateBy());
        createTime.setText(order.getCreateTime());
    }

    public void updateData() {
        log.info("修改数据");
        OrderVO order = orderList.getSelectionModel().getSelectedItem();
        if (null == order) {
            ResultUtil.getWarringResult("请选择要修改订单！");
        }

        ArrayList<Node> nodes = NodeUtil.getAllTextFiledNodes(orderList.getScene().getRoot());
        nodes.stream().forEach(e -> e.setDisable(false));
    }

    public void saveData() {
        log.info("保存订单数据");
        ArrayList<Node> nodes = NodeUtil.getAllTextFiledNodes(orderList.getScene().getRoot());
        nodes.stream().forEach(e -> e.setDisable(true));

        OrderVO order = new OrderVO();
        order.setOrderNo(orderNo.getText());
        order.setStatus(status.getText());
        order.setCreateBy(createBy.getText());
        normalOrderService.saveOrder(order);
        ResultUtil.getWarringResult("保存成功！");
    }
}
