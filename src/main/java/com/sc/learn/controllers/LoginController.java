package com.sc.learn.controllers;

import com.sc.learn.navigation.Navigation;
import com.sc.learn.service.UserService;
import com.sc.learn.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void handleLogin(ActionEvent e){
        if(!txtUsername.getText().isBlank() && !txtPassword.getText().isEmpty()){
            if(userService.login(txtUsername.getText().trim(),txtPassword.getText().trim())){
                clearText();
                //Navigate to dashboard
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                Navigation.navigateTo(null,false,getClass().getResource("/dashboard.fxml"),stage,true,1200,800,false);
            }else{
                Alerts.showDialog("Log In","Invalid credentials", Alert.AlertType.ERROR);
            }
        }else{
            Alerts.showDialog("Log In","Username or password is empty", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleToSignUpLink(ActionEvent e){
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Navigation.navigateTo(null,false,getClass().getResource("/signup.fxml"),stage,false,0,0,false);
    }

    private void clearText(){
        txtUsername.setText("");
        txtPassword.setText("");
    }
}
