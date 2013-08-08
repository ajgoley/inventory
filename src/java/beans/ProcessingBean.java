/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import entities.AssetHolder;
import entities.Equipment;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sessions.EquipmentFacade;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Internship
 */
@ManagedBean
@ViewScoped
public class ProcessingBean {

    
    
    @EJB
    private EquipmentFacade equipmentFacade;

     private static Logger log = java.util.logging.Logger.getLogger("ProcessingBean");
    
    private Equipment currentEquipment;
    
    private AssetHolder currentAssetHolder;

    boolean itemCheckedIn = false;
    
    private String currentITC;
    private String currentBarcode;
    
    public Equipment getCurrentEquipment() {
        return currentEquipment;
    }

    public void setCurrentEquipmentIn(Equipment currentEquipment) {
        this.currentEquipment = currentEquipment;
    }

    public AssetHolder getCurrentAssetHolder() {
        return currentAssetHolder;
    }

    public void setCurrentAssetHolder(AssetHolder currentAssetHolder) {
        this.currentAssetHolder = currentAssetHolder;
    }

    public boolean isItemCheckedIn() {
        return itemCheckedIn;
    }

    public void setItemCheckedIn(boolean itemCheckedIn) {
        this.itemCheckedIn = itemCheckedIn;
    }

    public String getCurrentITC() {
        return currentITC;
    }

    public void setCurrentITC(String currentITC) {
        this.currentITC = currentITC;
    }

    public String getCurrentBarcode() {
        return currentBarcode;
    }

    public void setCurrentBarcode(String currentBarcode) {
        this.currentBarcode = currentBarcode;
    }
    
    public Equipment findEquipmentByITC(){
        
        setCurrentEquipmentIn(equipmentFacade.findEquipmentByITC(currentITC));
        
        return currentEquipment;
    }
    
    
    public void display(){
      
      log.info("display being called");
      currentEquipment = findEquipmentByITC();  
   
      
      if(currentEquipment == null){
          
            FacesMessage msg = new FacesMessage("Check-In Error", "ITC Tag Number: "+currentITC+" not found in inventory");

            FacesContext.getCurrentInstance().addMessage(null, msg);
      }else{
          setItemCheckedIn(true);
          log.info(currentEquipment.getModel());
          if(itemCheckedIn == true){
              log.info("TRUE");
              
          }else
              log.info("FALSE");
          
      }
      
      
      
      //check if item is in inventory
      
      
      
    }
    
    
    
    
}
