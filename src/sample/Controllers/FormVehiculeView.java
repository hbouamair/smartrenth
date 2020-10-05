package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Models.Vehicule;

import java.net.URL;
import java.util.ResourceBundle;


public class FormVehiculeView implements Initializable {

    public TextField matriculeField;
    public TextField couleurField;
    public TextField marqueField;
    public TextField carburantField;
    public TextField prixField;
    public TextField kilometrageField;
    public TextField modelleField;


    private Vehicule vehiculeToEdit = null;
    private onFinish onFinished;
    // default  -- add


    void edit(Vehicule x) {


        vehiculeToEdit = x;
        // initalisation
        matriculeField.setText(x.getMatricule());
        couleurField.setText(x.getCouleur());
        marqueField.setText(x.getMarque());
        carburantField.setText(x.getCarburant());
        prixField.setText(x.getPrixParJour() + "");
        kilometrageField.setText(x.getKilometrage() + "");
        modelleField.setText(x.getModelle());


    }

    public void save() {

        if (vehiculeToEdit == null) {
            vehiculeToEdit = new Vehicule(
                    matriculeField.getText(),
                    couleurField.getText(),
                    modelleField.getText(),
                    marqueField.getText(),
                    carburantField.getText(),
                    Double.parseDouble(prixField.getText()),
                    Double.parseDouble(kilometrageField.getText())
            );
        } else {
            vehiculeToEdit.setMatricule(matriculeField.getText());
            vehiculeToEdit.setCouleur(couleurField.getText());
            vehiculeToEdit.setModelle(modelleField.getText());
            vehiculeToEdit.setMarque(marqueField.getText());
            vehiculeToEdit.setCarburant(carburantField.getText());
            vehiculeToEdit.setPrixParJour(Double.parseDouble(prixField.getText()));
            vehiculeToEdit.setKilometrage(Double.parseDouble(kilometrageField.getText()));
        }
        onFinished.finish(vehiculeToEdit);


    }

    void setOnFinished(onFinish onFinished) {

        this.onFinished = onFinished;
    }

    public void cancel(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    interface onFinish {

        void finish(Vehicule c);

    }


}
