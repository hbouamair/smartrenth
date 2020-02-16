package com.mycompany.locationproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void ajouterReservationbtn(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try {
            root = loader.load(getClass().getResource("/fxml/newreservation.fxml"));
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void ajouterreservation(ActionEvent actionEvent) {
    }
}
