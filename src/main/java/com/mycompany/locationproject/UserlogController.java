/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.locationproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CurrentUser;

/**
 *
 * @author hamza
 */
public class UserlogController  implements Initializable{

    @FXML
    private Label nomcmp;

    @FXML
    private AnchorPane reserverclient;
    @FXML
    private AnchorPane reservationlist;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        reservationlist.setVisible(true);
        reserverclient.setVisible(false);
        nomcmp.setText(CurrentUser.fullname);
    }












    @FXML
    public void editProfil(javafx.event.ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try {
            root = loader.load(getClass().getResource("/fxml/editprofile.fxml"));
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void reservationlistbtn(javafx.event.ActionEvent actionEvent) {
        reservationlist.setVisible(true);
        reserverclient.setVisible(false);


    }

    public void reservationbtn(ActionEvent actionEvent) {
        reserverclient.setVisible(true);
        reservationlist.setVisible(false);

    }
}