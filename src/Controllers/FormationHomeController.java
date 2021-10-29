/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FormationHomeController implements Initializable {

    @FXML
    private Button BtnAdmin;
    @FXML
    private Button BtnClient;
 
    
  //  BtnClient.setBackground(new Background(new BackgroundFill(Color.RED, null, null);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OuvrirPageAdminFormation(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/InterfaceAdminFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void OuvrirPageClientFormation(ActionEvent event) throws IOException {
                       Parent root = FXMLLoader.load(getClass().getResource("/Views/InterfaceClientFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
