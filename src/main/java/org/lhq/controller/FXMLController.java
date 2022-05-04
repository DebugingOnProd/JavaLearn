package org.lhq.controller;

import cn.hutool.system.SystemUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FXMLController {
    @FXML
    private MenuItem about;
    @FXML
    private PieChart pidData;

    public void initialize() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(()-> Platform.runLater(()->{
            long freeMemory = SystemUtil.getFreeMemory();
            freeMemory = freeMemory/(1024*1024);
            long totalMemory = SystemUtil.getTotalMemory();
            totalMemory = totalMemory/(1024*1024);
            double pre =(double) freeMemory/(double)totalMemory;
            double freePre = 1-pre;
            log.info("已使用内存:{}",pre);
            log.info("剩余内存:{}",freePre);
            ArrayList<PieChart.Data> data = new ArrayList<>();
            PieChart.Data data1 = new PieChart.Data("已使用内存",pre);
            PieChart.Data data2 = new PieChart.Data("剩余内存",freePre);
            data.add(data1);
            data.add(data2);
            ObservableList<PieChart.Data> objects = FXCollections.observableArrayList(data);
            pidData.setData(objects);
        }),1,TimeUnit.SECONDS);

    }
    public void aboutAction(ActionEvent actionEvent) throws IOException {
        log.info("点击了关于按钮");
        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("assets/fxml/about.fxml")));
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
