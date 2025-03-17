package com.example.hotelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ImageView backgroundImage;
    @FXML
    private StackPane root;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private Alert alert;

    @FXML
    private void Login(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Khong duoc bo trong username hoac password!");
            alert.showAndWait();
        }
        else
        {
            String sql="select * from UserAccount where username=? and password=?";
            Connection connection=ApplicationDatabase.connectdb();
            try
            {
                PreparedStatement prepare=connection.prepareStatement(sql);
                prepare.setString(1, usernameField.getText());
                prepare.setString(2, passwordField.getText());
                ResultSet result=prepare.executeQuery();
                if (result.next())
                {
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Dang nhap thanh cong!");
                    alert.showAndWait();
                    Stage stage=(Stage)loginButton.getScene().getWindow();
                    Parent root= FXMLLoader.load(getClass().getResource("layout/adminmainpage.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else
                {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Sai username hoac password!");
                    alert.showAndWait();
                }
            }
            catch(Exception e)
            {
                System.out.println("LOI ERROR DATABASE: "+e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
