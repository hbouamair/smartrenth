package sample.Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Connection.Helper;
import sample.DataAcces.ClientProvider;
import sample.DataAcces.ManagerProvider;
import sample.Models.Client;

import java.io.IOException;

public class LoginController {
    public Label labelError;
    ManagerProvider managerProvider = new ManagerProvider();
    ClientProvider clientProvider = new ClientProvider();


    public JFXTextField username;
    public JFXPasswordField password;

    public void tryToConnect(ActionEvent actionEvent) {
        String usern = username.getText();
        String passw = password.getText();
        boolean connected = false;

        if (managerProvider.connect(usern, passw)) {
            Helper.isAdmin = true;
            connected = true;
        } else if (clientProvider.connect(usern, passw)) {
            Helper.isAdmin = false;
            connected = true;

        } else {
            labelError.setText("Informations incorrects,"); //
        }

        if (connected) {

            Stage primaryStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../Views/MainPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setTitle("Gestion de Location");
            primaryStage.setScene(new Scene(root, 1024, 600));
            primaryStage.show();

            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }


    }
}
