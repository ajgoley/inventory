/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AssetHolder;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessions.AssetHolderFacade;

/**
 *
 * @author Internship
 */
@ManagedBean
@RequestScoped
public class AssetHolderBean {

  @EJB
  private AssetHolderFacade assetHolderFacade;
    
  private List<AssetHolder> filteredAssetHolders;
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

    
    
}
