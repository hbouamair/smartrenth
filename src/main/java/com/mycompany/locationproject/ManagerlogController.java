package com.mycompany.locationproject;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.CurrentManager;
import model.CurrentUser;

public class ManagerlogController implements Initializable {

    @FXML
    private Label nomcm;


    @FXML
    private AnchorPane panvoiture;
    @FXML
    private AnchorPane client;
    @FXML
    private AnchorPane reservation;
    @FXML
    private AnchorPane acceuil;

    @FXML
    private AnchorPane localisation;

   



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//       nomcm.setText(CurrentManager.fullname);
        acceuil.setVisible(true);
        panvoiture.setVisible(false);
        client.setVisible(false);



    }

    @FXML
    void clientacess(ActionEvent event) {
        acceuil.setVisible(false);
        panvoiture.setVisible(false);
        client.setVisible(true);

    }

    @FXML
    void voitureacces(ActionEvent event) {
        acceuil.setVisible(false);
        panvoiture.setVisible(true);
        client.setVisible(false);


    }

    public void reservationacess(ActionEvent actionEvent) {
        reservation.setVisible(true);




    }

    public void paneacc(ActionEvent actionEvent) {
        acceuil.setVisible(true);
        panvoiture.setVisible(false);
        client.setVisible(false);
    }
    @FXML
    void localisationbtn(ActionEvent event) {
        acceuil.setVisible(false);
        panvoiture.setVisible(false);
        client.setVisible(false);
        localisation.setVisible(true);
    }
}
