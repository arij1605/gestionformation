/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Cours;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IServiceCours {
    public void ajouterCours(Cours c) throws SQLException ;
    public boolean supprimerCours(int idcours) throws SQLException;
    public boolean modifierCours(Cours c,int idcours) throws SQLException;
    public Cours getServiceById(int id) throws SQLException ;
    public List<Cours> getAllCoursByFormation(int idformation) throws SQLException ;
      public List<Cours> getAllCours() throws SQLException ;
    
}
