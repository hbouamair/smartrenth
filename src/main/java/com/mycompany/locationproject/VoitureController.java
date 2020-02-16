/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.locationproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hamza
 */
public class VoitureController implements Initializable{


    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
    }

    public void ajouterVoitureBtn(ActionEvent event) throws IOException {


        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try {
            root = loader.load(getClass().getResource("/fxml/newvoiture.fxml"));
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // change LoginScene.fxml so it now has fx:controller="LoginController"
      ;

    }


}
