package sample.Controllers;


import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Connection.Helper;
import sample.DataAcces.LocationProvider;
import sample.Models.Location;
import sample.Server.ServerLookUp;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationRadar implements Initializable  {
    @FXML
    public TableView tableView;

    ObservableList<Location> listLocation;
    LocationProvider locationProvider = new LocationProvider(); //
    ServerLookUp lookUp;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        listLocation = FXCollections.observableList(locationProvider.getAll());
        tableView.setItems(listLocation);

        lookUp = new ServerLookUp();
        lookUp.setOnVehiculeSyncronized(vehicule -> {

            Platform.runLater(() -> {
                for (Location location1 : listLocation) {
                    if (location1.getVehicule().equals(vehicule)) {
                        location1.setVeicule(vehicule);
                        System.out.println(vehicule.getMatricule() + " is now on " + vehicule.getPosition());
                    }
                }

                tableView.setItems(listLocation);
                tableView.refresh();

            });

        });

        for (Location location1 : listLocation) {
            location1.getVehicule().startTracing();
        }
        TableColumn<Location, String> columNomclient = new TableColumn<>("Client");

        columNomclient.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getClient().getNom() +
                                " " +
                                data.getValue().getClient().getPrenom())
        );


        TableColumn<Location, String> columVoiture = new TableColumn<>("Voiture");
        columVoiture.setCellValueFactory(data ->
                new SimpleStringProperty(
                        "matricule : " + data.getValue().getVehicule().getMatricule() +
                                " , marque : " +
                                data.getValue().getVehicule().getMarque())
        );


        TableColumn<Location, String> columndate = new TableColumn<>("Date de reservation");
        columndate.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDate()
                ));

        TableColumn<Location, String> columpos = new TableColumn<>("Derniere position");
        columpos.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getVehicule().getPosition().toString()
                ));


        TableColumn<Location, ImageView> columnImage = new TableColumn<>("statut");
        columnImage.setCellValueFactory((TableColumn.CellDataFeatures<Location, ImageView> data) -> {

                    if (Helper.distanceBetween(data.getValue().getCenterPosition(), data.getValue().getVehicule().getPosition()) > data.getValue().getRayon())
                        return new SimpleObjectProperty<ImageView>(new ImageView(new Image("src/sample/images/outRange.png")));
                    else
                        return new SimpleObjectProperty<ImageView>(new ImageView(new Image("src/sample/images/inRange.png")));
                }

        );


        tableView.getColumns().addAll(columNomclient, columVoiture, columndate, columpos, columnImage);

    }


}
