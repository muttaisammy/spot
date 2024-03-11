package ampath.or.ke.spot.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class KashaDeliveries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "person_id")
    private int person_id;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "delivery_date")
    private Date delivery_date;

    @Column(name = "expected_delivery_date")
    private Date expected_delivery_date;

    @Column(name = "expected_delivery_date")
    private Date actual_delivery_date;

    @Column(name = "delivery_tries")
    private int delivery_tries;
    @Column(name = "delivery_status")
    private String delivery_status;
    @Column(name = "address")
    private String address;

    @Column(name = "estate_village")
    private String estate_village;

    @Column(name = "county")
    private String county;

    @Column(name = "nearest_landmark")
    private String nearest_landmark;

    @Column(name = "created_by")
    private int created_by;

    @Column(name = "created_on")
    private Date created_on;

    @Column(name = "modified_by", nullable = true)
    private int modified_by;

    @Column(name = "modified_on", nullable = true)
    private Date modified_on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Date getExpected_delivery_date() {
        return expected_delivery_date;
    }

    public void setExpected_delivery_date(Date expected_delivery_date) {
        this.expected_delivery_date = expected_delivery_date;
    }

    public Date getActual_delivery_date() {
        return actual_delivery_date;
    }

    public void setActual_delivery_date(Date actual_delivery_date) {
        this.actual_delivery_date = actual_delivery_date;
    }

    public int getDelivery_tries() {
        return delivery_tries;
    }

    public void setDelivery_tries(int delivery_tries) {
        this.delivery_tries = delivery_tries;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEstate_village() {
        return estate_village;
    }

    public void setEstate_village(String estate_village) {
        this.estate_village = estate_village;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getNearest_landmark() {
        return nearest_landmark;
    }

    public void setNearest_landmark(String nearest_landmark) {
        this.nearest_landmark = nearest_landmark;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModified_on() {
        return modified_on;
    }

    public void setModified_on(Date modified_on) {
        this.modified_on = modified_on;
    }
}
