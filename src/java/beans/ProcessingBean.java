/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import entities.AssetHolder;
import entities.Equipment;
import entities.EquipmentProcessing;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sessions.EquipmentFacade;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
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
    boolean itemCheckedOut = false;
    
    private String currentITC;
    private String currentBarcode;
    private String currentSerialNum;
    
    private String assetHolder_id;
    
    private String checkOutTo;
//    private String signedOffBy;
//    
//    private EquipmentProcessing timeStamp;
    
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

    public boolean isItemCheckedOut() {
        return itemCheckedOut;
    }

    public void setItemCheckedOut(boolean itemCheckedOut) {
        this.itemCheckedOut = itemCheckedOut;
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

    public String getAssetHolder_id() {
        return assetHolder_id;
    }

    public void setAssetHolder_id(String assetHolder_id) {
        this.assetHolder_id = assetHolder_id;
    }

    public String getCheckOutTo() {
        return checkOutTo;
    }

    public void setCheckOutTo(String checkOutTo) {
        this.checkOutTo = checkOutTo;
    }

  
    
    
    
    public Equipment findEquipment(){
        
        log.info("ITC "+currentITC);
        log.info("Serial "+currentSerialNum);
                
        currentITC = currentITC.trim();
        currentSerialNum = currentSerialNum.trim();
        
        if(!(currentITC.equals(""))){
            log.info("Setting ITC");
            setCurrentEquipment(equipmentFacade.findEquipmentByITC(currentITC));
        }
         if(!(currentSerialNum.equals(""))){
            log.info("Setting Serial");
            setCurrentEquipment(equipmentFacade.findEquipmentBySerial(currentSerialNum));
        }
        return currentEquipment;
    }

//    public String getSignedOffBy() {
//        return signedOffBy;
//    }
//
//    public void setSignedOffBy(String signedOffBy) {
//        this.signedOffBy = signedOffBy;
//    }
//
// 
    public Date getDateNow() {
        return dateNow;
    }

    public void setDateNow(Date dateNow) {
        this.dateNow = dateNow;
    }
//
//    public EquipmentProcessing getTimeStamp() {
//        return timeStamp;
//    }
//
//    public void setTimeStamp(EquipmentProcessing timeStamp) {
//        this.timeStamp = timeStamp;
//    }

    
      public void resetFields() {
        setCurrentITC(null);
        setCurrentBarcode(null);
        setCurrentSerialNum(null); 
          
    }
    
      public void cancelInDisplay(){
        setItemCheckedIn(false);
        resetFields();
    }
      
     public void cancelOutDisplay(){
        setItemCheckedOut(false);
        resetFields();
    }
    
      private boolean isNullOrEmpty(String value) {
        if (value == null || value.length() == 0) {
            return true;
        }
        return false;
    }
     
    public AssetHolder findAssetHolder(){
         Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	 assetHolder_id = params.get("asset_holder_id");
         
        
         return assetHolderFacade.findBySocialSecruity(assetHolder_id);

    }
     
     
    public void checkInItem(){
        
       //Updates any changed fields in equipment
      
       if(currentEquipment == null || currentEquipment.getAssetHolderId()==null){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "Check-In Fail", "Item not registered to this employee"));
             
         setItemCheckedIn(false);
         resetFields();
    
       }else{
             if(currentEquipment.getAssetHolderId().getEquipmentCollection().remove(currentEquipment))
           log.info("successful");
       else
               log.info("error");

           assetHolderFacade.edit(currentEquipment.getAssetHolderId());
           assetHolderFacade.updateTable();

           currentEquipment.setAssetHolderId(null); //Deletes currentEquipment from currentAssetHolder
           equipmentFacade.edit(currentEquipment);
           equipmentFacade.updateTable();


//       
       dateNow = new Date();
//       timeStamp = new EquipmentProcessing();

           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                   "Check In Successful", dateNow.toString()));

           resetFields();
          setItemCheckedIn(false);
       }      
    }
    
    public void checkOutItem(){
        if(currentEquipment == null || currentEquipment.getAssetHolderId()!=null){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "Check-Out Fail", "Item not registered to this employee"));
             
         setItemCheckedOut(false);
         resetFields();
    
       }else{
            
          currentAssetHolder = assetHolderFacade.findBySocialSecruity(checkOutTo);
                
          if(currentAssetHolder.getEquipmentCollection().add(currentEquipment))
                log.info("successful");
          else
                log.info("error");

           assetHolderFacade.edit(currentAssetHolder);
           assetHolderFacade.updateTable();

          //Deletes currentEquipment from currentAssetHolder
           currentEquipment.setAssetHolderId(currentAssetHolder);
           equipmentFacade.edit(currentEquipment);
           equipmentFacade.updateTable();


//       
       dateNow = new Date();
//       timeStamp = new EquipmentProcessing();

       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                   "Check Out Successful", dateNow.toString()));

       resetFields();
       setItemCheckedOut(false);
          
       }      
    }
    
    
    
    
    
    
}
