package org.lhq.controller;

import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;


public class AboutController {
    @FXML
    public ImageView logoImg;
    @FXML
    public ListView<String> listData;


    public void initialize() {
        logoImg.setImage(new Image("assets/textures/img/msft.png"));
        ArrayList<String> arrayList = new ArrayList<>();
        OsInfo osInfo = SystemUtil.getOsInfo();
        String name = osInfo.getName();
        String arch = osInfo.getArch();
        arrayList.add("操作系统:" + name);
        arrayList.add("架构:" + arch);
        ObservableList<String> observableList = FXCollections.observableArrayList(arrayList);
        listData.setItems(observableList);
    }
}
