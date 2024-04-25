package ampath.or.ke.spot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ccc_cleaner")
public class CCCno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "person_id")
    private int personID;

    @Column(name = "person_uuid")
    private String personUUID;

    @Column(name = "old_CCC")
    private String oldCCC;

    @Column(name = "new_ccc")
    private String newCCC;

    @Column(name = "location_uuid")
    private String locationUUID;

    @Column(name = "status")
    private int status;

    @Column(name = "identifier_type")
    private String identifierType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getPersonUUID() {
        return personUUID;
    }

    public void setPersonUUID(String personUUID) {
        this.personUUID = personUUID;
    }

    public String getOldCCC() {
        return oldCCC;
    }

    public void setOldCCC(String oldCCC) {
        this.oldCCC = oldCCC;
    }

    public String getNewCCC() {
        return newCCC;
    }

    public void setNewCCC(String newCCC) {
        this.newCCC = newCCC;
    }

    public String getLocationUUID() {
        return locationUUID;
    }

    public void setLocationUUID(String locationUUID) {
        this.locationUUID = locationUUID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }
}
