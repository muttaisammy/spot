package ampath.or.ke.spot.models;

import java.util.Date;

import lombok.*;

import javax.persistence.*;
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "new_on_art_linelist")
public class NewonARTLineList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "person_id")
    private int personId;

    @Column(name = "mflcode")
    private int mflcode;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "synced")
    private int synced;


    @Column(name = "created_by")
    private int created_by;

    @Column(name = "created_on")
    private Date created_on;

    @Column(name = "modified_by", nullable = true)
    private int modified_by;

    @Column(name = "modified_on", nullable = true)
    private Date modified_on;

    public void setId(int id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setMflcode(int mflcode) {
        this.mflcode = mflcode;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSynced(int synced) {
        this.synced = synced;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public void setModified_on(Date modified_on) {
        this.modified_on = modified_on;
    }
}
