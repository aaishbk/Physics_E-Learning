package com.sc.learn.controllers;

import com.sc.learn.dto.UserDto;
import com.sc.learn.models.Users;
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
public class SignUpController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }


    @FXML
    public void handleSignUp(ActionEvent e){
        if(txtUsername.getText().trim().length() > 0){
            if(txtPassword.getText().trim().length() > 6 && txtPassword.getText().trim().equals(txtConfirmPassword.getText().trim())){
                Users user = userService.createUser(new UserDto(txtUsername.getText(),txtConfirmPassword.getText().trim()));
                clearText();
                if(user != null){
                    Alerts.showDialog("Sign Up","User created", Alert.AlertType.INFORMATION);

                    //Navigate to login
                    navigateToLogIn(e);
                }
            }else{
                Alerts.showDialog("Sign Up","Ensure password length is greater and 6 and both passwords match", Alert.AlertType.ERROR);
            }
        }else{
            Alerts.showDialog("Sign Up","Username cannot be empty", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleLinkClick(ActionEvent e){
        navigateToLogIn(e);
    }

    private void clearText(){
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtUsername.requestFocus();
    }

    private void  navigateToLogIn(ActionEvent e){
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Navigation.navigateTo(null,false,getClass().getResource("/login.fxml"),stage,false,0,0,false);
    }
}
