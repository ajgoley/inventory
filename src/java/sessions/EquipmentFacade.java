/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Equipment;
import java.util.List;
import java.util.logging.Logger;
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
    
    private static Logger log = Logger.getLogger("EquipmentFacade");
    
    //Method used in AssetHolderBean
    public List<Equipment> findAssetHolderEquipment(int assetHolderId) {
      StringBuilder queryString = new StringBuilder();

      queryString.append("SELECT e from Equipment e ");
      queryString.append("WHERE e.assetHolderId.assetHolderId = ");
      queryString.append(assetHolderId);
      

      TypedQuery<Equipment> query = em.createQuery(queryString.toString(), Equipment.class);
      
      return query.getResultList();

  }
    
   public Equipment findEquipmentByITC(String tag) {
      String queryString = "SELECT e FROM Equipment e WHERE e.itcTag = :itcTag";

      TypedQuery<Equipment> query = em.createQuery(queryString, Equipment.class);
      
      query.setParameter("itcTag",tag);
      
      return query.getSingleResult();

  }
   
    public Equipment findEquipmentByBarcode(String barcode) {
        String queryString = "SELECT e FROM Equipment e WHERE e.barcode = :barcode";

        TypedQuery<Equipment> query = em.createQuery(queryString, Equipment.class);

        query.setParameter("barcode", barcode);

        return query.getSingleResult();

    }
    
    public void persist(Equipment e){
        em.persist(e);
    }
}
