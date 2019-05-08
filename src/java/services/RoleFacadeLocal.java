/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import model.Role;

/**
 *
 * @author Tidiany
 */
@Local
public interface RoleFacadeLocal {

    /**
     *
     * @param role
     */
    void create(Role role);

    /**
     *
     * @param role
     */
    void edit(Role role);

    /**
     *
     * @param role
     */
    void remove(Role role);

    /**
     *
     * @param id
     * @return
     */
    Role find(Object id);

    /**
     *
     * @return
     */
    List<Role> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Role> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
    
}
