/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.AssetHolderFacade;
import sessions.CategoryFacade;
import sessions.EquipmentFacade;
import sessions.EquipmentProcessingFacade;
import entities.Equipment;
import java.util.List;

/**
 *
 * @author Internship
 */
@ManagedBean
@ViewScoped
public class AdminBean {

    @EJB
    AssetHolderFacade assetHolderFacade;
    @EJB
    CategoryFacade categoryFacade;
    @EJB
    EquipmentFacade equipmentFacade;
    @EJB
    EquipmentProcessingFacade equipmentProcessingFacade;
    // Object to hold the equipment item being created
    Equipment newEquipment = new Equipment();

    /**
     * Creates a new instance of AdminBean
     */
    public AdminBean() {
    }
    
    
}
