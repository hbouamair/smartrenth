package com.mycompany.locationproject;

import API.Intconnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.stage.Stage;
import model.CurrentUser;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterClientController implements Initializable {


    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField numt;
    Connection con = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = Intconnection.conDB();



        
    }


    public void ajoutclient(ActionEvent actionEvent) throws SQLException {
        String sql1 = ("INSERT INTO `Clients`(`Nom`, `Prenom`, `Cin`, `Adresse`, `Numtele`) VALUES (?,?,?,?,?)  ");
        PreparedStatement statement = con.prepareStatement(sql1);

        statement.setString(1, nom.getText());
        statement.setString(2, prenom.getText());
        statement.setString(3,cin.getText());
        statement.setString(4, adresse.getText());
        statement.setString(5, numt.getText());



        int rs = statement.executeUpdate();

        if (rs == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout de client");
            alert.setHeaderText(null);
            alert.setContentText("Client Ajouter");
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
