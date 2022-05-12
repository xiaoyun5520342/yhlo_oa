package com.yhlo.oa;

import com.yhlo.oa.util.CommonUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CompletableFuture;

@Slf4j
@MapperScan(basePackages = {"com.yhlo.oa.mapper"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.yhlo.oa"})
public class YhloOaApplication extends Application {

    protected static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        // 先加载spring
        CompletableFuture.supplyAsync(() -> applicationContext = SpringApplication.run(YhloOaApplication.class, args)).whenComplete((ctx, throwable) -> {
            if (throwable != null) {
                log.error("Failed to load spring application context: ", throwable);
            } else {
                // 再启动主页面
                launch(args);
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setTitle("订单管理系统");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);//禁用最大化
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(CommonUtil.getLogo());
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (applicationContext != null) {
            applicationContext.close();
        }
    }
}
