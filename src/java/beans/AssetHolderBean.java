/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AssetHolder;
import entities.Equipment;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sessions.AssetHolderFacade;
import sessions.EquipmentFacade;

/**
 *
 * @author Internship
 */
@ManagedBean
@ViewScoped
public class AssetHolderBean {

    
  private static Logger log = Logger.getLogger("AssetHolderBean");  
    
  @EJB
  private AssetHolderFacade assetHolderFacade;
  
  @EJB
  private EquipmentFacade equipmentFacade;
    
  @ManagedProperty(value="#{processingBean}")
  ProcessingBean processingBean;
  
  
  private Equipment currentEquipment;
  
  private List<AssetHolder> filteredAssetHolders;
  private AssetHolder selectedAssetHolder = new AssetHolder();
  
  //private List<Equipment> assetHolderEquipment;
  private List<Equipment> filteredEquipment;
  
  private String assetHolder_id;
  private String currentEquipment_itc;
  private String currentEquipment_serial;
  
  //Table in AssetHolder.xhtml
  private List<AssetHolder> assetHolderDisplayTable; 

    /**Getters and Setters**/
    public ProcessingBean getProcessingBean() {
        return processingBean;
    }

    public void setProcessingBean(ProcessingBean processingBean) {
        this.processingBean = processingBean;
    }
  
    public List<AssetHolder> getFilteredAssetHolders() {
        return filteredAssetHolders;
    }

    public void setFilteredAssetHolders(List<AssetHolder> filteredAssetHolders) {
        this.filteredAssetHolders = filteredAssetHolders;
    }

    public List<AssetHolder> getAssetHolderDisplayTable() {
        
          if(assetHolderDisplayTable == null){
           assetHolderDisplayTable = assetHolderFacade.findAll(); 
        }
        assetHolderFacade.updateTable();
        
        return assetHolderDisplayTable;
    }

    public void setAssetHolderDisplayTable(List<AssetHolder> assetHolderDisplayTable) {
        this.assetHolderDisplayTable = assetHolderDisplayTable;
    }

    public AssetHolder getSelectedAssetHolder() {
        return selectedAssetHolder;
    }

    public void setSelectedAssetHolder(AssetHolder selectedAssetHolder) {
        this.selectedAssetHolder = selectedAssetHolder;
    }

    public List<Equipment> getFilteredEquipment() {
        return filteredEquipment;
    }

    public void setFilteredEquipment(List<Equipment> filteredEquipment) {
        this.filteredEquipment = filteredEquipment;
    }

    public Equipment getCurrentEquipment() {
        return currentEquipment;
    }

    public void setCurrentEquipment(Equipment currentEquipment) {
        this.currentEquipment = currentEquipment;
        
    }


    public String getAssetHolder_id() {
        return assetHolder_id;
    }

    public void setAssetHolder_id(String assetHolder_id) {
        this.assetHolder_id = assetHolder_id;
    }
    
    
    /**Methods**/
    
    private boolean isNullOrEmpty(String value) {
        if (value == null || value.length() == 0) {
            return true;
        }
        return false;
    }
    
    @PostConstruct
    public void init(){
         Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	 assetHolder_id = params.get("asset_holder_id");  
         currentEquipment_itc = params.get("currentEquipment_itc");
         currentEquipment_serial = params.get("currentEquipment_serial");
//         
//         if(assetHolder_id == null){
//             log.info("assetHolder is null");
//         }else{
//             log.info(assetHolder_id);
//         }
         
         log.info("init called");
         if(!isNullOrEmpty(assetHolder_id)){
             //log.info("asset id = "+ assetHolder_id);
             
             selectedAssetHolder = assetHolderFacade.findBySocialSecruity(assetHolder_id);
             
             //log.info(selectedAssetHolder.getFullName());
         }
         
          if(!isNullOrEmpty(currentEquipment_itc)){
              log.info("setting equipment");
              currentEquipment=equipmentFacade.findEquipmentByITC(currentEquipment_itc);
              log.info(currentEquipment.getModel());
             
          }
           if(!isNullOrEmpty(currentEquipment_serial)){
              log.info("setting equipment");
              currentEquipment=equipmentFacade.findEquipmentBySerial(currentEquipment_serial);
              log.info(currentEquipment.getModel());
             
          }
    }
    
  
    
     public void displayCheckIn(){
      
      log.info("display being called");
      processingBean.findEquipment();  
      setCurrentEquipment(processingBean.getCurrentEquipment());
      
      if(currentEquipment == null){
           processingBean.resetFields();
            FacesMessage msg = new FacesMessage("Check-In Error", "ITC Tag Number: "+processingBean.getCurrentITC()+" not found in inventory");

            FacesContext.getCurrentInstance().addMessage(null, msg);
      }else{
          processingBean.setItemCheckedIn(true);
          processingBean.resetFields();
          log.info(currentEquipment.getModel());
          if(processingBean.itemCheckedIn == true){
              log.info("TRUE");
//              FacesMessage msg = new FacesMessage("Hello "+processingBean.getCurrentITC()+" "+currentEquipment.getModel());
          }else
              log.info("FALSE");
          
      }
      
      
      //check if item is in inventory
    }
     
       public void displayCheckOut(){
      
      log.info("display being called");
      processingBean.findEquipment();  
      setCurrentEquipment(processingBean.getCurrentEquipment());
      
      if(currentEquipment == null){
          
            FacesMessage msg = new FacesMessage("Check-In Error", "ITC Tag Number: "+processingBean.getCurrentITC()+" not found in inventory");

            FacesContext.getCurrentInstance().addMessage(null, msg);
            processingBean.resetFields();
      }else{
          processingBean.setItemCheckedOut(true);
          log.info(currentEquipment.getModel());
          processingBean.resetFields();
          if(processingBean.itemCheckedIn == true){
              log.info("TRUE");
//              FacesMessage msg = new FacesMessage("Hello "+processingBean.getCurrentITC()+" "+currentEquipment.getModel());
          }else
              log.info("FALSE");
          
      }
      
      
      //check if item is in inventory
    }
       
        public void checkOutItem(){
        if(currentEquipment == null || currentEquipment.getAssetHolderId()!=null){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "Check-Out Fail", "Item not registered to this employee"));
             
         processingBean.setItemCheckedOut(false);
         processingBean.resetFields();
    
       }else{
        
          //currentAssetHolder = assetHolderFacade.findBySocialSecruity(checkOutTo);
                
          if(selectedAssetHolder.getEquipmentCollection().add(currentEquipment))
                log.info("successful");
          else
                log.info("error");

           assetHolderFacade.edit(selectedAssetHolder);
           assetHolderFacade.updateTable();

          //Deletes currentEquipment from currentAssetHolder
           currentEquipment.setAssetHolderId(selectedAssetHolder);
           equipmentFacade.edit(currentEquipment);
           equipmentFacade.updateTable();


//       
       processingBean.setDateNow(new Date());
//       timeStamp = new EquipmentProcessing();

       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                   "Check Out Successful", processingBean.getDateNow().toString()));

       processingBean.resetFields();
       processingBean.setItemCheckedOut(false);
          
       }      
    }

    
    
}
