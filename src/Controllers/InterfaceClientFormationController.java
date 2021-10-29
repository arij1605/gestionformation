/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Formation;
import Services.ServiceFormation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceClientFormationController implements Initializable {


    @FXML
    private Button BtnBack;
    @FXML
    private TableView<Formation> tvformation;
Formation ff = null;
    @FXML
    private TableColumn<Formation, String> colTitre;
    @FXML
    private TableColumn<Formation, String> coldescription;
    @FXML
    private TableColumn<Formation, String> colniveau;
    @FXML
    private TableColumn<Formation, Integer> colprix;
    @FXML
    private TextField TfRecherche;
    /**
     * Initializes the controller class.
     */
        ObservableList<Formation> formationsRecherche = FXCollections.observableArrayList();
    @FXML
    private Button BtnCours;
    @FXML
    private AnchorPane AnchorProd;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           try {
            // TODO preload Data on the table
            AfficherFormation();
        } catch (SQLException ex) {
            Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);

        }
        tvformation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ff = (Formation) tvformation.getSelectionModel().getSelectedItem();
                System.out.println(ff);
                
                /*int pid = ff.getIdformation();
                String Spid = String.valueOf(pid);
                tftitre.setText(ff.getTitre());
                
                tfdescription.setText(ff.getDescription());
                tfniveau.setText(ff.getNiveau());
                
                int pprice = (int) ff.getPrix();
                String Spprice = String.valueOf(pprice);
                tfprix.setText(Spprice); */
               
                
            }
        });
    }    

     private void AfficherFormation() throws SQLException {
         ServiceFormation sF = new ServiceFormation();
        List formation = (List) sF.getAllFormations();
        ObservableList et = FXCollections.observableArrayList(formation);
       
        colTitre.setCellValueFactory(new PropertyValueFactory<Formation, String>("titre"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        colniveau.setCellValueFactory(new PropertyValueFactory<Formation, String>("niveau"));
        colprix.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("prix"));
        tvformation.setItems(et);}
    @FXML
    private void Back(ActionEvent event) throws IOException {
                               Parent root = FXMLLoader.load(getClass().getResource("/Views/FormationHome.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void RechercheFormation(ActionEvent event)  throws SQLException, ClassNotFoundException {
           ServiceFormation sF = new ServiceFormation();
        String critere = TfRecherche.getText().toLowerCase();
        if (critere.length() > 0) {
            formationsRecherche.clear();
            List<Formation> produitsaux = sF.displayRecherche(critere);
            if (!produitsaux.isEmpty()) {
                for (int i = 0; i < produitsaux.size(); i++) {
                    formationsRecherche.add(produitsaux.get(i));
                }
            }
        colTitre.setCellValueFactory(new PropertyValueFactory<Formation, String>("titre"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        colniveau.setCellValueFactory(new PropertyValueFactory<Formation, String>("niveau"));
        colprix.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("prix"));
            tvformation.setItems(formationsRecherche);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Recherche du formation");
            alert.setContentText("Critere non valide !");
            alert.showAndWait();
            try { //afficher au fur et a mesure avec l'ajout
                AfficherFormation();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceClientFormationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void VoirCours(ActionEvent event) throws IOException, FileNotFoundException, SQLException {
        
                FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/InterfaceClientCours.fxml"));

        AnchorPane pane = loader.load();
        AnchorProd.getChildren().setAll(pane);

        InterfaceClientCoursController controllerC = loader.getController();
        controllerC.initData(tvformation.getSelectionModel().getSelectedItem());
    }
    
}
