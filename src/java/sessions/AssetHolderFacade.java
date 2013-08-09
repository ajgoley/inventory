/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.AssetHolder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Internship
 */
@Stateless
public class AssetHolderFacade extends AbstractFacade<AssetHolder> {
    @PersistenceContext(unitName = "Inventory2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssetHolderFacade() {
        super(AssetHolder.class);
    }
    
    
     public void updateTable(){
         em.flush();
     }
     
     
     //Queries
     
      public AssetHolder findBySocialSecruity(String social){
       StringBuilder queryString = new StringBuilder();
       queryString.append("SELECT e FROM AssetHolder e ");
       queryString.append("WHERE e.ssn = :social");
       
       TypedQuery<AssetHolder> query = em.createQuery(queryString.toString(),AssetHolder.class);
       query.setParameter("social", social);
       return query.getSingleResult();
    }
      
      public void persist(AssetHolder a){
          em.persist(a);
      }
    
      public AssetHolder findByName(String name){
          StringBuilder queryString = new StringBuilder();
          queryString.append("SELECT e FROM AssetHolder e ");
          queryString.append("WHERE e.ssn LIKE = :name");

          TypedQuery<AssetHolder> query = em.createQuery(queryString.toString(), AssetHolder.class);
          query.setParameter("name", name);
          return query.getSingleResult();
      }
      
}
