package com.yhlo.oa.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @create: 2022-04-12 16:30
 * @description:
 **/
@Slf4j
@RestController
public class MainController implements Initializable {

    public Label userName;
    public Label todayAmount;
    public Label monthAmount;
    public Label yearAmount;
    public Label todayOrderNums;
    public Label monthOrderNums;
    public Label yearOrderNums;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("进入主页面");
        userName.setText("超级管理员");
        todayAmount.setText("11");
        monthAmount.setText("22");
        yearAmount.setText("33");
        todayOrderNums.setText("44");
        monthOrderNums.setText("55");
        yearOrderNums.setText("66");
    }
}
