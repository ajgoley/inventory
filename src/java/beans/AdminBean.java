/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AssetHolder;
import entities.Category;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.AssetHolderFacade;
import sessions.CategoryFacade;
import sessions.EquipmentFacade;
import sessions.EquipmentProcessingFacade;
import entities.Equipment;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
    Equipment newEquipment;
    // Object to hold the category to be added
    Category newCategory;
    // Object to hold the employee to be added
    AssetHolder newAssetHolder;
    Boolean equipmentPanel = false;
    Boolean categoryPanel = false;
    Boolean assetHolderPanel = false;
    Boolean active;
    String condition;
    int categoryKey;
    private static final Logger logger = Logger.getLogger("adminBean");

    /**
     * Creates a new instance of AdminBean
     */
    public AdminBean() {
    }

    public Boolean getAssetHolderPanel() {
        return assetHolderPanel;
    }

    public void setAssetHolderPanel(Boolean assetHolderPanel) {
        this.assetHolderPanel = assetHolderPanel;
    }

    public AssetHolder getNewAssetHolder() {
        return newAssetHolder;
    }

    public void setNewAssetHolder(AssetHolder newAssetHolder) {
        this.newAssetHolder = newAssetHolder;
    }
    
    public void initNewAssetHolder(){
        newAssetHolder = new AssetHolder();
    }

    public Boolean getCategoryPanel() {
        return categoryPanel;
    }

    public void setCategoryPanel(Boolean categoryPanel) {
        this.categoryPanel = categoryPanel;
    }

    public int getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(int categoryKey) {
        this.categoryKey = categoryKey;
    }

    public Equipment getNewEquipment() {
        return newEquipment;
    }

    public void setNewEquipment(Equipment newEquipment) {
        this.newEquipment = newEquipment;
    }

    public Category getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(Category newCategory) {
        this.newCategory = newCategory;
    }

    public void initNewcategory() {
        newCategory = new Category();
    }

    public Boolean getEquipmentPanel() {
        return equipmentPanel;
    }

    public void setEquipmentPanel(Boolean equipmentPanel) {
        this.equipmentPanel = equipmentPanel;
    }

    public boolean renderEquipmentPanel() {
        if (!equipmentPanel) {
            equipmentPanel = true;
            return equipmentPanel;
        } else {
            equipmentPanel = false;
            return equipmentPanel;
        }
    }

    public boolean renderCategoryPanel() {
        if (!categoryPanel) {
            categoryPanel = true;
            return categoryPanel;
        } else {
            categoryPanel = false;
            return categoryPanel;
        }
    }
    
    public boolean renderAssetHolderPanel() {
        if (!assetHolderPanel) {
            assetHolderPanel = true;
            return assetHolderPanel;
        } else {
            assetHolderPanel = false;
            return assetHolderPanel;
        }
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void initNewEquip() {
        newEquipment = new Equipment();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<SelectItem> getAllCategories() {
        List<SelectItem> retval = new ArrayList<>();

        for (Category c : categoryFacade.findAll()) {
            retval.add(new SelectItem(c.getCategoryId(), c.getCategoryName()));
        }

        //Collections.sort(retval, byLabel);
        return retval;
    }

    public void saveNewEquipment() {
        newEquipment.setActive(active);
        newEquipment.setEqCondition(condition);
        equipmentFacade.persist(newEquipment);
        newEquipment.setCategoryId(categoryFacade.find(categoryKey));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "New Equipment Saved.", "complete"));
        newEquipment = null;
        categoryKey = 0;

    }
    
    public void saveNewCategory() {
        categoryFacade.persist(newCategory);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "New Category Saved.", "complete"));
        newCategory = null;
    }
    
    public void saveNewEmployee(){
        assetHolderFacade.persist(newAssetHolder);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "New Employee Saved.", "complete"));
        newAssetHolder = null;
    }
}
