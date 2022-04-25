package com.yhlo.oa;

import com.yhlo.oa.config.SpringFXMLLoader;
import com.yhlo.oa.util.CommonUtil;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.yhlo.oa.mapper"})
@SpringBootApplication(scanBasePackages = {"com.yhlo.oa"})
public class YhloOaApplication extends Application {

    private static final SpringFXMLLoader loader = new SpringFXMLLoader();

    public static void main(String[] args) {
        // 启动spring-boot
//        SpringApplication.run(YhloOaApplication.class, args);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 这里的root从FXML文件中加载进行初始化，这里FXMLLoader类用于加载FXML文件
//        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        Parent root = (Parent) loader.load("/views/Login.fxml");
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
