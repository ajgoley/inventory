/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import entities.AssetHolder;
import entities.Equipment;
import entities.EquipmentProcessing;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sessions.EquipmentFacade;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import sessions.AssetHolderFacade;
import sessions.EquipmentProcessingFacade;

/**
 *
 * @author Internship
 */
@ManagedBean
@ViewScoped
public class ProcessingBean {

    
    
    @EJB
    private EquipmentFacade equipmentFacade;
    //private AssetHolderFacade assetHolderFacade;
    
    @EJB
    private EquipmentProcessingFacade processingFacade;
    
   @EJB
   private AssetHolderFacade assetHolderFacade;
    

     private static Logger log = java.util.logging.Logger.getLogger("ProcessingBean");
    
    private Equipment currentEquipment;
    
    private AssetHolder currentAssetHolder;

    boolean itemCheckedIn = false;
    
    private String currentITC;
    private String currentBarcode;
    private String currentSerialNum;
    private String signedOffBy;
    
    private EquipmentProcessing timeStamp;
    
    private Date dateNow;
    
    public Equipment getCurrentEquipment() {
        return currentEquipment;
    }

    public void setCurrentEquipment(Equipment currentEquipment) {
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

    public String getCurrentSerialNum() {
        return currentSerialNum;
    }

    public void setCurrentSerialNum(String currentSerialNum) {
        this.currentSerialNum = currentSerialNum;
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
        
        setCurrentEquipment(equipmentFacade.findEquipmentByITC(currentITC));
        
        return currentEquipment;
    }

    public String getSignedOffBy() {
        return signedOffBy;
    }

    public void setSignedOffBy(String signedOffBy) {
        this.signedOffBy = signedOffBy;
    }

 
    public Date getDateNow() {
        return dateNow;
    }

    public void setDateNow(Date dateNow) {
        this.dateNow = dateNow;
    }

    public EquipmentProcessing getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(EquipmentProcessing timeStamp) {
        this.timeStamp = timeStamp;
    }

    
    public void checkInItem(){
        
       //Updates any changed fields in equipment
      
       
       if(currentEquipment.getAssetHolderId().getEquipmentCollection().remove(currentEquipment))
           log.info("successful");
       else
           log.info("error");
       
       assetHolderFacade.edit(currentEquipment.getAssetHolderId());
       assetHolderFacade.updateTable();
       
        currentEquipment.setAssetHolderId(null); //Deletes currentEquipment from currentAssetHolder
       equipmentFacade.edit(currentEquipment);
       equipmentFacade.updateTable();
      
     
       
       dateNow = new Date();
       timeStamp = new EquipmentProcessing();
    
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "Check In Successful", dateNow.toString()));
         
         setItemCheckedIn(false);
         
         
    }
    
    
    
    
}
