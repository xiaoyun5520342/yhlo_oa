package com.yhlo.oa.controller;

import com.yhlo.oa.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @create: 2022-03-31 17:45
 * @description: 登录
 **/
@Slf4j
@RestController
public class LoginController  {

    public TextField textUserAccount;

    public PasswordField textPassword;



    public void login(ActionEvent event) throws IOException {
        log.info(textUserAccount.getText() + textPassword.getText());
        String userAccount = textUserAccount.getText();
        String password = textPassword.getText();
        if (!"admin".equals(userAccount) || !"admin".equals(password)) {
            CommonUtil._alertError("","用户名或密码错误,请检查！！！");

        } else {
            // 登录进去后的主页面
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/views/FrameWork.fxml")), 1000, 600));

            mainStage.getScene().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

            mainStage.getIcons().add(CommonUtil.getLogo());
            mainStage.setTitle("订单系统");
            mainStage.show();

            Window window = textUserAccount.getScene().getWindow();
            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        }
    }

    /**
     * 重置登录名及密码
     */
    public void reset(){
        log.info("重置登录名及密码");
        textUserAccount.setText(" ");
        textPassword.setText(" ");
    }


}
