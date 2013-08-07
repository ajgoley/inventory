/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Internship
 */
@Entity
@Table(name = "asset_holder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssetHolder.findAll", query = "SELECT a FROM AssetHolder a"),
    @NamedQuery(name = "AssetHolder.findByAssetHolderId", query = "SELECT a FROM AssetHolder a WHERE a.assetHolderId = :assetHolderId"),
    @NamedQuery(name = "AssetHolder.findByFirstName", query = "SELECT a FROM AssetHolder a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "AssetHolder.findByLastName", query = "SELECT a FROM AssetHolder a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "AssetHolder.findByCompany", query = "SELECT a FROM AssetHolder a WHERE a.company = :company"),
    @NamedQuery(name = "AssetHolder.findByEmail", query = "SELECT a FROM AssetHolder a WHERE a.email = :email"),
    @NamedQuery(name = "AssetHolder.findBySsn", query = "SELECT a FROM AssetHolder a WHERE a.ssn = :ssn")})
public class AssetHolder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asset_holder_id")
    private Integer assetHolderId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 45)
    @Column(name = "company")
    private String company;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "ssn")
    private String ssn;
    @OneToMany(mappedBy = "assetHolderId")
    private Collection<Equipment> equipmentCollection;

    public AssetHolder() {
    }

    public AssetHolder(Integer assetHolderId) {
        this.assetHolderId = assetHolderId;
    }

    public AssetHolder(Integer assetHolderId, String firstName, String lastName) {
        this.assetHolderId = assetHolderId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getAssetHolderId() {
        return assetHolderId;
    }

    public void setAssetHolderId(Integer assetHolderId) {
        this.assetHolderId = assetHolderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @XmlTransient
    public Collection<Equipment> getEquipmentCollection() {
        return equipmentCollection;
    }

    public void setEquipmentCollection(Collection<Equipment> equipmentCollection) {
        this.equipmentCollection = equipmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetHolderId != null ? assetHolderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssetHolder)) {
            return false;
        }
        AssetHolder other = (AssetHolder) object;
        if ((this.assetHolderId == null && other.assetHolderId != null) || (this.assetHolderId != null && !this.assetHolderId.equals(other.assetHolderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AssetHolder[ assetHolderId=" + assetHolderId + " ]";
    }
    
}
