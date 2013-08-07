/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Equipment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    
    //Method used in AssetHolderBean
    public List<Equipment> findAssetHolderEquipment(int assetHolderId) {
      StringBuilder queryString = new StringBuilder();

      queryString.append("SELECT e from Equipment e ");
      queryString.append("WHERE e.employeeId.employeeId = ");
      queryString.append(assetHolderId);

      TypedQuery<Equipment> query = em.createQuery(queryString.toString(), Equipment.class);

      return query.getResultList();

  }
    
}
