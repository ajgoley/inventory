/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AssetHolder;
import entities.Equipment;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sessions.AssetHolderFacade;
import sessions.EquipmentFacade;

/**
 *
 * @author Internship
 */
@ManagedBean
@RequestScoped
public class AssetHolderBean {

    
  private static Logger log = Logger.getLogger("AssetHolderBean");  
    
  @EJB
  private AssetHolderFacade assetHolderFacade;
  
  @EJB
  private EquipmentFacade equipmentFacade;
    
  private List<AssetHolder> filteredAssetHolders;
  private AssetHolder selectedAssetHolder = new AssetHolder();
  
  //private List<Equipment> assetHolderEquipment;
  private List<Equipment> filteredEquipment;
  
  private String assetHolder_id;
  
  //Table in AssetHolder.xhtml
  private List<AssetHolder> assetHolderDisplayTable; 

    /**Getters and Setters**/
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

//    public List<Equipment> getAssetHolderEquipment() {
//        log.info("asset holder equipment called");
//         if(assetHolderEquipment == null){ 
//              assetHolderEquipment = equipmentFacade.findAssetHolderEquipment(selectedAssetHolder.getAssetHolderId());
//          }
//        
//        return assetHolderEquipment;
//    }
//
//    public void setAssetHolderEquipments(List<Equipment> assetHolderEquipments) {
//        this.assetHolderEquipment = assetHolderEquipments;
//    }

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
         
         if(!isNullOrEmpty(assetHolder_id)){
             //log.info("asset id = "+ assetHolder_id);
             
             selectedAssetHolder = assetHolderFacade.findBySocialSecruity(assetHolder_id);
             
             //log.info(selectedAssetHolder.getFullName());
         }
    }

    
    
}
