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
@Table(name = "afyastat_errors")
public class AfyastatErrors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eid")
    private int eid;
    @Column(name = "id")
    private Integer id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "location")
    private String location;
    @Column(name = "provider")
    private String provider;
    @Column(name = "discriminator")
    private String discriminator;
    @Column(name = "dataSource")
    private String dataSource;
    @Column(name = "payload", columnDefinition = "TEXT")
    private String payload;
    @Column(name = "formName")
    private String formName;
    @Column(name = "patientUuid")
    private String patientUuid;
    @Column(name = "formDataUuid")
    private String formDataUuid;
    @Column(name = "errormessage", columnDefinition = "TEXT")
    private String errormessage;
    @Column(name = "status")
    private String status;
    @Column(name = "responsecode")
    private String responsecode;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getPatientUuid() {
        return patientUuid;
    }

    public void setPatientUuid(String patientUuid) {
        this.patientUuid = patientUuid;
    }

    public String getFormDataUuid() {
        return formDataUuid;
    }

    public void setFormDataUuid(String formDataUuid) {
        this.formDataUuid = formDataUuid;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }
}
