package com.yhlo.oa.controller;

import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @create: 2022-04-12 16:37
 * @description:
 **/
@Slf4j
@RestController
public class RolesController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("进入多角订单！");
    }
}
