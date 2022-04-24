package com.yhlo.oa;

import com.yhlo.oa.util.CommonUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yhlo.**.mapper")
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

        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        primaryStage.setTitle("订单管理系统");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);//禁用最大化
       // primaryStage.initStyle(StageStyle.UTILITY);//禁用最大化和最小化
       // primaryStage.initStyle(StageStyle.UNDECORATED);//全部禁用
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(CommonUtil.getLogo());
        primaryStage.show();
    }
}
