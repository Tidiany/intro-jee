/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Utilisateur;

/**
 *
 * @author Tidiany
 */
@Local
public interface UtilisateurFacadeLocal {

    /**
     *
     * @param utilisateur
     */
    void create(Utilisateur utilisateur);

    /**
     *
     * @param utilisateur
     */
    void edit(Utilisateur utilisateur);

    /**
     *
     * @param utilisateur
     */
    void remove(Utilisateur utilisateur);

    /**
     *
     * @param id
     * @return
     */
    Utilisateur find(Object id);

    /**
     *
     * @return
     */
    List<Utilisateur> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Utilisateur> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();

    /**
     * Recuperer l'utilisateur par son login et mot de passe...
     * @param login
     * @param password
     * @return
     */
    Utilisateur getUserByLoginAndPassword(String login, String password);

    /**
     *
     * @return
     */
    int getLastInsertId();
}
