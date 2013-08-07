/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Equipment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Internship
 */
@Stateless
public class EquipmentFacade extends AbstractFacade<Equipment> {
    @PersistenceContext(unitName = "Inventory2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipmentFacade() {
        super(Equipment.class);
    }
    
}
