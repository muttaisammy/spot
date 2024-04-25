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
@Table(name = "service_tracker")
public class ServiceTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="service_name")
    private String serviceName;

    @Column(name="service_type")
    private String serviceType;

    @Column(name="department")
    private String department;

    @Column(name="experiryDate")
    private Date experiryDate;

    @Column(name="status")
    private String status;

    @Column(name="amount")
    private String amount;

    @Column(name="vendor")
    private String vendor;

    @Column(name="person_responsible")
    private String personResponsible;

    @Column(name="created_by")
    private int created_by;

    @Column(name="created_on")
    private Date created_on;

    @Column(name="modified_by", nullable=true)
    private int modified_by;

    @Column(name="modified_on", nullable=true)
    private Date modified_on;

}
