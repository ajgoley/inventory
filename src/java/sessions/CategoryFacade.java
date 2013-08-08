/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Category;
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
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "Inventory2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

    public Category findByName(String n) {
        StringBuilder queryString = new StringBuilder();

        queryString.append("SELECT c from Category c ");
        queryString.append("WHERE c.categoryName = ");
        queryString.append("'");
        queryString.append(n);
        queryString.append("'");

        TypedQuery<Category> query = em.createQuery(queryString.toString(), Category.class);

        return query.getSingleResult();
    }

    public void persist(Category c) {
        em.persist(c);
    }
}
