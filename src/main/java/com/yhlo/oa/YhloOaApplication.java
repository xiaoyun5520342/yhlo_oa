package com.yhlo.oa;

import com.yhlo.oa.util.CommonUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YhloOaApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 这里的root从FXML文件中加载进行初始化，这里FXMLLoader类用于加载FXML文件
        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
//         Scene scene = new Scene(root, 500, 500);
        Scene scene = new Scene(root);
        primaryStage.setTitle("订单管理系统");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(CommonUtil.getLogo());
        primaryStage.show();
    }
}
