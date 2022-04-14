package com.yhlo.oa.controller;

import com.yhlo.oa.util.CommonUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @create: 2022-03-31 17:45
 * @description: 登录
 **/
@Slf4j
@RestController
public class LoginController {

    public TextField textUserAccount;

    public PasswordField textPassword;

    public void login() throws IOException {
        log.info(textUserAccount.getText() + textPassword.getText());
        String userAccount = textUserAccount.getText();
        String password = textPassword.getText();
        if (!"admin".equals(userAccount) || !"admin".equals(password)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("用户名或者密码错误");
            alert.showAndWait();
        } else {
            // 登录进去后的主页面
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/views/FrameWork.fxml")), 1000, 600));
            mainStage.getIcons().add(CommonUtil.getLogo());
            mainStage.setTitle("订单系统");
            mainStage.show();

            Window window = textUserAccount.getScene().getWindow();
            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        }
    }
}
