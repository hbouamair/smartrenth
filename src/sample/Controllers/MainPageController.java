package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.Connection.Helper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {


    public Pane containerIncluder;

    public Button accueilButton;
    public Button vehiculeButton;
    public Button clientButton;
    public Button locationButton;
    public Button paramsButton;
    public Button reservationButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(!Helper.isAdmin){
            clientButton.setVisible(false);
            reservationButton.setVisible(false);
            paramsButton.setVisible(false);
            locationButton.setVisible(false);






        }




        //

    }

    public void navigateurButtonClicked(ActionEvent actionEvent) {

        containerIncluder.getChildren().clear();
        try {

            if (actionEvent.getSource() == accueilButton)
                containerIncluder.getChildren().add(FXMLLoader.load(getClass().getResource("../Views/Accueil.fxml")));


            if (actionEvent.getSource() == clientButton)
                containerIncluder.getChildren().add(FXMLLoader.load(getClass().getResource("../Views/ViewClientExplorer.fxml")));

            if (actionEvent.getSource() == vehiculeButton)
                containerIncluder.getChildren().add(FXMLLoader.load(getClass().getResource("../Views/ViewVehiculeExplorer.fxml")));

            if (actionEvent.getSource() == locationButton)
                containerIncluder.getChildren().add(FXMLLoader.load(getClass().getResource("../Views/ViewLocationExplorer.fxml")));

            if (actionEvent.getSource() == reservationButton)
                containerIncluder.getChildren().add(FXMLLoader.load(getClass().getResource("../Views/ViewReservationRadar.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
//
