/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EquipmentProcessing;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Internship
 */
@Stateless
public class EquipmentProcessingFacade extends AbstractFacade<EquipmentProcessing> {
    @PersistenceContext(unitName = "Inventory2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipmentProcessingFacade() {
        super(EquipmentProcessing.class);
    }
    
}
