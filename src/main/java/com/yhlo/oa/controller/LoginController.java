package com.yhlo.oa.controller;

import com.yhlo.oa.entity.OrderVO;
import com.yhlo.oa.enums.OrderTypeEnum;
import com.yhlo.oa.service.NormalOrderService;
import com.yhlo.oa.util.CommonUtil;
import com.yhlo.oa.util.OrderUtil;
import com.yhlo.oa.util.StringUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @create: 2022-03-31 17:45
 * @description: 登录
 **/
@Slf4j
@Component
public class LoginController {

    public TextField textUserAccount;
    public PasswordField textPassword;

    @Autowired
    private NormalOrderService normalOrderService;

    public void login() throws IOException {
        log.info(textUserAccount.getText() + textPassword.getText());
        String userAccount = textUserAccount.getText();
        String password = textPassword.getText();

        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(userAccount) || StringUtils.isEmpty(password)) {
            CommonUtil._alertError("", "用户名或密码为空,请检查！！！");
            return;
        }

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo(new OrderUtil().getInstance().generaterOrderNo(OrderTypeEnum.NORMAL));
        normalOrderService.saveOrder(orderVO);
        // 查询用户信息
//        SysUser user = sysUserService.selectUserByLoginName(userAccount);

        if (!"admin".equals(userAccount) || !"admin".equals(password)) {
            CommonUtil._alertError("", "用户名或密码错误,请检查！！！");
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
    public void reset() {
        log.info("重置登录名及密码");
        textUserAccount.setText(" ");
        textPassword.setText(" ");
    }
}
