package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Models.Client;

import java.net.URL;
import java.util.ResourceBundle;


public class FormClientView implements Initializable {
    public TextField nomField;
    public TextField prenomField;
    public TextField cinField;
    public TextField addressField;
    public TextField teleField;
    public TextField usernameField;
    public PasswordField passwordField;
    private Client clientToEdit = null;
    private onFinish onFinished;
    // default  -- add


    void edit(Client x) {


        clientToEdit = x;
        // initalisation
        nomField.setText(x.getNom());
        prenomField.setText(x.getPrenom());
        cinField.setText(x.getCin());
        addressField.setText(x.getAdresse());
        teleField.setText(x.getTelephone());
        usernameField.setText(x.getLogin());
        passwordField.setText(x.getPassword());


    }

    public void save() {

        if (clientToEdit == null) {
            clientToEdit = new Client(
                    nomField.getText(),
                    prenomField.getText(),
                    cinField.getText(),
                    teleField.getText(),
                    addressField.getText(),
                    usernameField.getText(),
                    passwordField.getText()
            );
        } else {
            clientToEdit.setNom(nomField.getText());
            clientToEdit.setPrenom(prenomField.getText());
            clientToEdit.setCin(cinField.getText());
            clientToEdit.setAdresse(addressField.getText());
            clientToEdit.setTelephone(teleField.getText());
            clientToEdit.setLogin(usernameField.getText());
            clientToEdit.setPassword(passwordField.getText());
        }
        onFinished.finish(clientToEdit);


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

        void finish(Client c);

    }


}
