package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DataAcces.ClientProvider;
import sample.DataAcces.VehiculeProvider;
import sample.Models.Client;
import sample.Models.Location;
import sample.Models.Position;
import sample.Models.Vehicule;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FormLocationView implements Initializable {


    public ComboBox<Client> client;
    public ComboBox<Vehicule> vehicule;

    public DatePicker date;
    public TextField duree;
    public TextField montant;

    public TextField latitude;
    public TextField longitude;

    public TextField rayon;

    private Location LocationToEdit = null;
    private onFinish onFinished;


    ClientProvider clientProvider = new ClientProvider();
    VehiculeProvider vehiculeProvider = new VehiculeProvider();

    // default  -- add


    void edit(Location x) {


        LocationToEdit = x;
        // initalisation
        client.getSelectionModel().select(x.getClient());
        vehicule.getSelectionModel().select(x.getVehicule());

        // date
        duree.setText(x.getDureeJour() + "");
        montant.setText(x.getMontant() + "");
        latitude.setText(x.getCenterPosition().getLatitude() + "");
        longitude.setText(x.getCenterPosition().getLongitude() + "");
        rayon.setText(x.getRayon() + "");
        //
        date.setValue(LocalDate.from(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(x.getDate())));


    }

    public void save() {

        if (LocationToEdit == null) {
            LocationToEdit = new Location(
                    vehicule.getSelectionModel().getSelectedItem(),
                    client.getSelectionModel().getSelectedItem(),
                    date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    Integer.parseInt(duree.getText())
            );

        } else {
            LocationToEdit.setVeicule(vehicule.getSelectionModel().getSelectedItem());
            LocationToEdit.setClient(client.getSelectionModel().getSelectedItem());
            LocationToEdit.setDate(date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            LocationToEdit.setDureeJour(Integer.parseInt(duree.getText()));

        }


        LocationToEdit.setCenterPosition(new Position(Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText())));
        LocationToEdit.setRayon(Double.parseDouble(rayon.getText()));

        onFinished.finish(LocationToEdit);


    }

    void setOnFinished(onFinish onFinished) {

        this.onFinished = onFinished;
    }

    public void cancel(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    ObservableList<Client> clientsList;
    ObservableList<Vehicule> vehiculesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        clientsList = FXCollections.observableList(clientProvider.getAll());
        vehiculesList = FXCollections.observableList(vehiculeProvider.getAll());

        client.setItems(clientsList);
        vehicule.setItems(vehiculesList);


    }


    interface onFinish {

        void finish(Location c);

    }
}
