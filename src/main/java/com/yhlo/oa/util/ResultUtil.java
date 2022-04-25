package com.yhlo.oa.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

/**
 * @create: 2022-04-14 16:53
 * @description: 响应结果
 **/
public class ResultUtil {

    public static void getWarringResult(String text) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        DialogPane dialogPane = dialogPane(text, "确定");
        alert.setDialogPane(dialogPane);
        alert.setHeight(200);
        alert.setWidth(300);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(CommonUtil.getLogo());
        alert.show();
    }

    public static DialogPane dialogPane(String text, String buttonText) {
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContentText(text);
        dialogPane.getButtonTypes().addAll(customText(buttonText));
        return dialogPane;
    }

    public static ButtonType customText(String text) {
        return new ButtonType(text);
    }
}
