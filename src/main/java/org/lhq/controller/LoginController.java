package org.lhq.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginController {

    @FXML
    public PasswordField password;
    public TextField username;

    public void submit(ActionEvent actionEvent) {
        String usernameText = username.getText();
    }
}
