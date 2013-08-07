/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.AssetHolder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
