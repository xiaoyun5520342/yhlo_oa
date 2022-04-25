package com.yhlo.oa.config;

import com.yhlo.oa.YhloOaApplication;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * @create: 2022-04-25 12:05
 * @description:
 **/
public class SpringFXMLLoader {

    private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(YhloOaApplication.class);

    public Object load(String url) {
        try (InputStream fxmlStream = SpringFXMLLoader.class.getResourceAsStream(url)) {
            System.err.println(SpringFXMLLoader.class.getResourceAsStream(url));
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    return applicationContext.getBean(clazz);
                }
            });
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
