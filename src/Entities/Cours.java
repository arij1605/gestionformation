/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class Cours {
     private int idcours;
     private String nomcours;
     private String descriptioncours;
    private int formation_id;

    public Cours(int idcours, String nomcours, String descriptioncours, int formation_id) {
        this.idcours = idcours;
        this.nomcours = nomcours;
        this.descriptioncours = descriptioncours;
        this.formation_id = formation_id;
    }

    public Cours() {
    }

    public Cours(String nomcours, String descriptioncours, int formation_id) {
        this.nomcours = nomcours;
        this.descriptioncours = descriptioncours;
        this.formation_id = formation_id;
    }

    public Cours(String nomcours, String descriptioncours) {
        this.nomcours = nomcours;
        this.descriptioncours = descriptioncours;
    }

    public Cours(int idcours, String nomcours) {
        this.idcours = idcours;
        this.nomcours = nomcours;
    }

    
     
    public int getIdcours() {
        return idcours;
    }

    public String getNomcours() {
        return nomcours;
    }

    public String getDescriptioncours() {
        return descriptioncours;
    }

    public int getFormation_id() {
        return formation_id;
    }

    public void setIdcours(int idcours) {
        this.idcours = idcours;
    }

    public void setNomcours(String nomcours) {
        this.nomcours = nomcours;
    }

    public void setDescriptioncours(String descriptioncours) {
        this.descriptioncours = descriptioncours;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }
    
    
}
