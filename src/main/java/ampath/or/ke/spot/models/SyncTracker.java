package ampath.or.ke.spot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sync_status")
public class SyncTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "module")
    private String module;

    @Column(name = "date_last_sycned")
    private Date date_last_sycned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Date getDate_last_sycned() {
        return date_last_sycned;
    }

    public void setDate_last_sycned(Date date_last_sycned) {
        this.date_last_sycned = date_last_sycned;
    }
}
