/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Formation;
import IServices.IServiceFormation;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class ServiceFormation implements IServiceFormation{

       Connection connexion;
ObservableList<Formation> FormationsList = FXCollections.observableArrayList();
    public ServiceFormation() {
        connexion = MyDB.getInstance().getConnection();
    }

   /* public formationservice(int i, String dessin, String cours_de_dessin, String anull, String anull0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }*/
     @Override
    public void ajouterFormation (Formation f) throws SQLException {
            String req ="INSERT INTO `formation`( `titre`, `description`, `niveau`, `prix`)VALUES(?,?,?,?)";
           try { 
            PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(req);
            pst.setString(1,f.getTitre()) ; 
            pst.setString(2,f.getDescription()) ;
            pst.setString(3,f.getNiveau()) ;
            pst.setInt(4,f.getPrix()) ;
            pst.executeUpdate (); 
           System.out.println("Formation Ajoutée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        } 
    }
     @Override
    public List<Formation> getAllFormations() throws SQLException {

        String req = "SELECT * FROM `formation`";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
     
           FormationsList.add(new Formation(
                          rst.getInt("idformation")  ,
                   rst.getString("titre")  ,
                    rst.getString("description"),
                    rst.getString("niveau"),
                    rst.getInt("prix")));
        }
        return FormationsList;
    }
    public Boolean chercher(int id) {
        String req = "select * from formation";
        List<Integer> list = new ArrayList<>();
        try {
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.contains(id);
    }
     
     @Override
    public boolean modifierFormation(Formation f,int id) throws SQLException {
         if (chercher(id)) {

            try {
                String req = "UPDATE `formation` SET `titre` = ?, `description` = ?, `niveau` = ?, `prix` = ? WHERE `formation`.`idformation` = ?";
             PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(req);
            pst.setString(1,f.getTitre()) ; 
            pst.setString(2,f.getDescription()) ;
            pst.setString(3,f.getNiveau()) ;
            pst.setInt(4,(int) f.getPrix()) ;
           
            pst.setInt(5, id);
            pst.executeUpdate (); 

            } catch (SQLException ex) {
                Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("update valide");
            return true;
        }
        System.out.println("update invalid: formation nexiste pas");
        return false;
    }
    
    
    @Override
    public void supprimerFormation(int idformtion) throws SQLException {

        String req = "DELETE FROM formation WHERE idformation=?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, idformtion);
            ps.executeUpdate();
        } catch (SQLException ex) {
                        System.out.println(ex);
        }

    }
    
     public static boolean ControleInt(String prix) {
        if ((prix).matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public static boolean ControleNom(String str) {
        str = str.toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Formation> displayRecherche(String critere) {
   String req = "select * from formation where LOWER(titre) like '%" + critere + "%' or LOWER(description) like '%" + critere + "%'";
        List<Formation> list = new ArrayList<>();

        try {
       ResultSet resultSet = connexion.createStatement().executeQuery(req);
            while (resultSet.next()) {
                Formation f = new Formation();
                f.setIdformation(resultSet.getInt(1));
                f.setTitre(resultSet.getString("titre"));
                   f.setDescription(resultSet.getString("description"));
                     f.setNiveau(resultSet.getString("niveau"));
                f.setPrix(resultSet.getInt("prix"));
            
                list.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }
    
     
}
