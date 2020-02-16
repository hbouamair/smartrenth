package com.mycompany.locationproject;

import API.Intconnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterVoitureController implements Initializable {

    @FXML
    private JFXTextField matricule;

    @FXML
    private JFXTextField couleur;

    @FXML
    private JFXTextField modele;

    @FXML
    private JFXTextField marque;

    @FXML
    private JFXTextField carburant;

    @FXML
    private JFXTextField prixj;

    @FXML
    private JFXTextField kilometrage;
    Connection con = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = Intconnection.conDB();

    }

    @FXML
    public void ajoutvoiture(javafx.event.ActionEvent actionEvent) throws SQLException {
        String sql1 = ("INSERT INTO `Voiture`( `matricule`, `couleur`, `modele`, `marque`, `carburant`, `prixj`, `kilometrage`) VALUES (?,?,?,?,?,?,?)  ");
        PreparedStatement statement = con.prepareStatement(sql1);

        statement.setString(1, matricule.getText());
        statement.setString(2, couleur.getText());
        statement.setString(3, modele.getText());
        statement.setString(4, marque.getText());
        statement.setString(5, carburant.getText());
        statement.setString(6, prixj.getText());
        statement.setString(7, kilometrage.getText());

        int rs = statement.executeUpdate();

        if (rs == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Voiture Jouter");
            alert.setHeaderText(null);
            alert.setContentText("Voiture Ajoutee");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les informations Correct");
            alert.showAndWait();
            alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                @Override
                public void handle(DialogEvent event) {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                }
            });
        }
    }
}