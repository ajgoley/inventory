/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AssetHolder;
import entities.Equipment;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessions.AssetHolderFacade;
import sessions.EquipmentFacade;

/**
 *
 * @author Internship
 */
@ManagedBean
@RequestScoped
public class AssetHolderBean {

  @EJB
  private AssetHolderFacade assetHolderFacade;
  
  @EJB
  private EquipmentFacade equipmentFacade;
    
  private List<AssetHolder> filteredAssetHolders;
  private AssetHolder selectedAssetHolder;
  private List<Equipment> assetHolderEquipments;
  
  
  
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

    
    
    public List<Equipment> getAssetHolderEquipments() {
        
         if(assetHolderEquipments != null){
              assetHolderEquipments = equipmentFacade.findAssetHolderEquipment(selectedAssetHolder.getAssetHolderId());
          }
        
        return assetHolderEquipments;
    }

    public void setAssetHolderEquipments(List<Equipment> assetHolderEquipments) {
        this.assetHolderEquipments = assetHolderEquipments;
    }
    
    
    
    

    
    
}
