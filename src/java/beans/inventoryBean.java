/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import entities.Equipment;
import java.util.List;
import javax.ejb.EJB;
import sessions.EquipmentFacade;

/**
 *
 * @author Internship
 */
@ManagedBean
@ViewScoped
public class inventoryBean {
    @EJB
    EquipmentFacade equipmentFacade = new EquipmentFacade();

    /**
     * Creates a new instance of inventoryBean
     */
    public inventoryBean() {
    }

    public List<Equipment> getTheEquipemntList() {
        return equipmentFacade.findAll();
    }
}
