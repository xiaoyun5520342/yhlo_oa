package com.yhlo.oa.controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @create: 2022-04-11 11:15
 * @description:
 **/
@Slf4j
@RestController
public class FrameWorkController implements Initializable {
    public Pane main;
    public Pane normal;
    public BorderPane roles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePage();
    }

    public void homePage() {
        log.info("进入主页");
        main.setVisible(true);
        normal.setVisible(false);
        roles.setVisible(false);
    }

    public void normal() {
        log.info("进入一般订单");
        main.setVisible(false);
        normal.setVisible(true);
        roles.setVisible(false);
    }

    public void roles() {
        log.info("进入多角订单");
        main.setVisible(false);
        normal.setVisible(false);
        roles.setVisible(true);
    }
}


