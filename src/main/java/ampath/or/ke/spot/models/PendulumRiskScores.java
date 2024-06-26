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
@Table(name = "pendulum_risk_scores")
public class PendulumRiskScores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "patientIdentifier")
    private String patientIdentifier;

    @Column(name = "nextClinicalAppointment")
    private String nextClinicalAppointment;

    @Column(name = "noShowScore")
    private String noShowScore;

    @Column(name = "risk_smg")
    private String risksmg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public String getNextClinicalAppointment() {
        return nextClinicalAppointment;
    }

    public void setNextClinicalAppointment(String nextClinicalAppointment) {
        this.nextClinicalAppointment = nextClinicalAppointment;
    }

    public String getNoShowScore() {
        return noShowScore;
    }

    public void setNoShowScore(String noShowScore) {
        this.noShowScore = noShowScore;
    }

    public String getRisksmg() {
        return risksmg;
    }

    public void setRisksmg(String risksmg) {
        this.risksmg = risksmg;
    }
}
