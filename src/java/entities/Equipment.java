/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Network Admin
 */
@Entity
@Table(name = "equipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e"),
    @NamedQuery(name = "Equipment.findById", query = "SELECT e FROM Equipment e WHERE e.id = :id"),
    @NamedQuery(name = "Equipment.findBySerialNumber", query = "SELECT e FROM Equipment e WHERE e.serialNumber = :serialNumber"),
    @NamedQuery(name = "Equipment.findByActive", query = "SELECT e FROM Equipment e WHERE e.active = :active"),
    @NamedQuery(name = "Equipment.findByDescription", query = "SELECT e FROM Equipment e WHERE e.description = :description"),
    @NamedQuery(name = "Equipment.findByModel", query = "SELECT e FROM Equipment e WHERE e.model = :model"),
    @NamedQuery(name = "Equipment.findByEqCondition", query = "SELECT e FROM Equipment e WHERE e.eqCondition = :eqCondition"),
    @NamedQuery(name = "Equipment.findByBarcode", query = "SELECT e FROM Equipment e WHERE e.barcode = :barcode"),
    @NamedQuery(name = "Equipment.findByItcTag", query = "SELECT e FROM Equipment e WHERE e.itcTag = :itcTag"),
    @NamedQuery(name = "Equipment.findByLocationArea", query = "SELECT e FROM Equipment e WHERE e.locationArea = :locationArea"),
    @NamedQuery(name = "Equipment.findByAdditionalNotes", query = "SELECT e FROM Equipment e WHERE e.additionalNotes = :additionalNotes")})
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "active")
    private Boolean active;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "model")
    private String model;
    @Size(max = 7)
    @Column(name = "eq_condition")
    private String eqCondition;
    @Size(max = 255)
    @Column(name = "barcode")
    private String barcode;
    @Size(max = 255)
    @Column(name = "itc_tag")
    private String itcTag;
    @Size(max = 255)
    @Column(name = "location_area")
    private String locationArea;
    @Size(max = 255)
    @Column(name = "additional_notes")
    private String additionalNotes;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne
    private Category categoryId;
    @JoinColumn(name = "asset_holder_id", referencedColumnName = "asset_holder_id")
    @ManyToOne
    private AssetHolder assetHolderId;

    public Equipment() {
    }

    public Equipment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEqCondition() {
        return eqCondition;
    }

    public void setEqCondition(String eqCondition) {
        this.eqCondition = eqCondition;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItcTag() {
        return itcTag;
    }

    public void setItcTag(String itcTag) {
        this.itcTag = itcTag;
    }

    public String getLocationArea() {
        return locationArea;
    }

    public void setLocationArea(String locationArea) {
        this.locationArea = locationArea;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public AssetHolder getAssetHolderId() {
        return assetHolderId;
    }

    public void setAssetHolderId(AssetHolder assetHolderId) {
        this.assetHolderId = assetHolderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipment[ id=" + id + " ]";
    }
    
}
