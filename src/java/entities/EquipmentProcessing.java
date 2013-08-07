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
 * @author Internship
 */
@Entity
@Table(name = "equipment_processing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipmentProcessing.findAll", query = "SELECT e FROM EquipmentProcessing e"),
    @NamedQuery(name = "EquipmentProcessing.findByEquipmentProcessId", query = "SELECT e FROM EquipmentProcessing e WHERE e.equipmentProcessId = :equipmentProcessId"),
    @NamedQuery(name = "EquipmentProcessing.findByCheckInTime", query = "SELECT e FROM EquipmentProcessing e WHERE e.checkInTime = :checkInTime"),
    @NamedQuery(name = "EquipmentProcessing.findByCheckOutTime", query = "SELECT e FROM EquipmentProcessing e WHERE e.checkOutTime = :checkOutTime"),
    @NamedQuery(name = "EquipmentProcessing.findByCheckedInBy", query = "SELECT e FROM EquipmentProcessing e WHERE e.checkedInBy = :checkedInBy"),
    @NamedQuery(name = "EquipmentProcessing.findByCheckedOutBy", query = "SELECT e FROM EquipmentProcessing e WHERE e.checkedOutBy = :checkedOutBy"),
    @NamedQuery(name = "EquipmentProcessing.findByLastUsedBy", query = "SELECT e FROM EquipmentProcessing e WHERE e.lastUsedBy = :lastUsedBy")})
public class EquipmentProcessing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "equipment_process_id")
    private Integer equipmentProcessId;
    @Size(max = 255)
    @Column(name = "check_in_time")
    private String checkInTime;
    @Size(max = 255)
    @Column(name = "check_out_time")
    private String checkOutTime;
    @Size(max = 8)
    @Column(name = "checked_in_by")
    private String checkedInBy;
    @Size(max = 8)
    @Column(name = "checked_out_by")
    private String checkedOutBy;
    @Size(max = 255)
    @Column(name = "last_used_by")
    private String lastUsedBy;
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    @ManyToOne
    private Equipment equipmentId;

    public EquipmentProcessing() {
    }

    public EquipmentProcessing(Integer equipmentProcessId) {
        this.equipmentProcessId = equipmentProcessId;
    }

    public Integer getEquipmentProcessId() {
        return equipmentProcessId;
    }

    public void setEquipmentProcessId(Integer equipmentProcessId) {
        this.equipmentProcessId = equipmentProcessId;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getCheckedInBy() {
        return checkedInBy;
    }

    public void setCheckedInBy(String checkedInBy) {
        this.checkedInBy = checkedInBy;
    }

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(String checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public String getLastUsedBy() {
        return lastUsedBy;
    }

    public void setLastUsedBy(String lastUsedBy) {
        this.lastUsedBy = lastUsedBy;
    }

    public Equipment getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Equipment equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipmentProcessId != null ? equipmentProcessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipmentProcessing)) {
            return false;
        }
        EquipmentProcessing other = (EquipmentProcessing) object;
        if ((this.equipmentProcessId == null && other.equipmentProcessId != null) || (this.equipmentProcessId != null && !this.equipmentProcessId.equals(other.equipmentProcessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EquipmentProcessing[ equipmentProcessId=" + equipmentProcessId + " ]";
    }
    
}
