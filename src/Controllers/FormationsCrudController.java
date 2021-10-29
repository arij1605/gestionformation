/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Formation;
import Services.ServiceFormation;
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
import javafx.geometry.Pos;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FormationsCrudController implements Initializable {

    @FXML
    private TableView<Formation> tvformation;
    @FXML
    private TableColumn<Formation, Integer> colId;
    @FXML
    private TableColumn<Formation, String> colTitre;
    @FXML
    private TableColumn<Formation, String> colDescription;
    @FXML
    private TableColumn<Formation, String> colNiveau;
    @FXML
    private TableColumn<Formation, Integer> colPrix;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;  
    @FXML
    private TextField tfniveau;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfcours;
    @FXML
    private Button btnajouter;
    
Formation ff = null;
    @FXML
    private Button BtnBack;
    /**
     * Initializes the controller class.
     */
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
                
                int pid = ff.getIdformation();
                String Spid = String.valueOf(pid);
                tftitre.setText(ff.getTitre());
                
                tfdescription.setText(ff.getDescription());
                tfniveau.setText(ff.getNiveau());
                
                int pprice = (int) ff.getPrix();
                String Spprice = String.valueOf(pprice);
                tfprix.setText(Spprice);
               
                
            }
        });
    }    

    @FXML
    private void SupprimerFormation(ActionEvent event) throws SQLException {
        ServiceFormation sF = new ServiceFormation();
                Formation f = (Formation) tvformation.getSelectionModel().getSelectedItem();
        System.out.println(f);
        if (ff == null) {
            JOptionPane.showMessageDialog(null, "choisir formation");

        } else {
            sF.supprimerFormation(ff.getIdformation());

            try { 
                AfficherFormation();
            } catch (SQLException ex) {
                Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);
            }

          Notifications notificationBuilder = Notifications.create().title("Formation Supprimer").graphic(null)
            	.hideAfter(javafx.util.Duration.seconds(10)).position(Pos.TOP_LEFT);
            notificationBuilder.show();
            tftitre.clear();
            tfdescription.clear();
            tfniveau.clear();
            tfprix.clear();

            ff = null;
        }
    }

    @FXML
    private void ModifierFormation(ActionEvent event) throws SQLException {
        ServiceFormation sF = new ServiceFormation();
         System.out.println(ff);
        if (ff == null) {
            JOptionPane.showMessageDialog(null, "choisir formation");

        } else {
            String title = tftitre.getText();
            String description = tfdescription.getText();
            String niveau = tfniveau.getText();    
            int price = Integer.parseInt(tfprix.getText());
       
            sF.modifierFormation(new Formation(title, description, niveau, price), ff.getIdformation());

            Notifications notificationBuilder = Notifications.create().title("Formation Modifier").graphic(null)
            	.hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_LEFT);
            notificationBuilder.show();
            tftitre.clear();
            tfdescription.clear();
            tfniveau.clear();
            tfprix.clear();
  
    
            ff = null;
            try {
                AfficherFormation();
              
            } catch (SQLException ex) {
                Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void AjouterFormation(ActionEvent event) throws SQLException {
        ServiceFormation sF = new ServiceFormation();
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Ajout de Formation");
        String titleval = tftitre.getText().toString().toLowerCase();
        String descrival = tfdescription.getText().toString().toLowerCase();
        String niveauval = tfniveau.getText().toString().toLowerCase();
        String prixval = tfprix.getText().toString().toLowerCase();
        boolean verif = true;
        System.out.println("Initialize Done !");
        
        if (!sF.ControleNom(titleval)) {
            verif = false;
            alert.setContentText("Verifiez le nom du produit !");
            alert.showAndWait();
        }

        if (!sF.ControleNom(descrival)) {
            verif = false;
            alert.setContentText("Verifiez la description du produit !");
            alert.showAndWait();
        }
         if (!sF.ControleNom(niveauval)) {
            verif = false;
            alert.setContentText("Verifiez la description du produit !");
            alert.showAndWait();
        }
        if (!sF.ControleInt(prixval)) {
            verif = false;
            alert.setContentText("Verifiez le prix du produit !");
            alert.showAndWait();
        }

        if (verif) {
          Formation formation = new Formation(titleval,descrival,niveauval,Integer.parseInt(prixval));
                 System.out.println(formation);
            System.out.println("Formation Initialized !");
            sF.ajouterFormation(formation);
            System.out.println("Formation Added Done !");
            //Api CONTROLSfx
            Notifications notificationBuilder = Notifications.create().title("Formation ajouté").graphic(null)
            	.hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_LEFT);
            notificationBuilder.show();
        }

        try {
            AfficherFormation();
        } catch (SQLException ex) {
            Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        tftitre.clear();
        tfdescription.clear();
        tfniveau.clear();
        tfprix.clear();
    }
     private void AfficherFormation() throws SQLException {
         ServiceFormation sF = new ServiceFormation();
        List formation = (List) sF.getAllFormations();
        ObservableList et = FXCollections.observableArrayList(formation);
        colId.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("idformation"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Formation, String>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        colNiveau.setCellValueFactory(new PropertyValueFactory<Formation, String>("niveau"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("prix"));
        tvformation.setItems(et);}

    @FXML
    private void BackAdmin(ActionEvent event) throws IOException {
                   	Parent root = FXMLLoader.load(getClass().getResource("/Views/InterfaceAdminFormation.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}