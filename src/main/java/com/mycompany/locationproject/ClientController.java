package com.mycompany.locationproject;

import API.Intconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void newclient(ActionEvent actionEvent) throws IOException

    {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try {
            root = loader.load(getClass().getResource("/fxml/newclient.fxml"));
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    }

