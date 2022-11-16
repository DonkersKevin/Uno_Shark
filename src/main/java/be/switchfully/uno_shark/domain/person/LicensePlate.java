package be.switchfully.uno_shark.domain.person;

import javax.persistence.*;

@Entity
@Table(name = "license_plate")
public class LicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licenseplate_seq")
    @SequenceGenerator(name = "licenseplate_seq", sequenceName = "licenseplate_seq", allocationSize = 1)
    private long id;

    @Column(name = "issuing_country")
    private IssuingCountry issuingCountry;
    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    public LicensePlate() {
    }

    public LicensePlate(IssuingCountry issuingCountry, String licensePlateNumber) {
        this.issuingCountry = issuingCountry;
        this.licensePlateNumber = licensePlateNumber;
    }

    public IssuingCountry getIssuingCountry() {
        return issuingCountry;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
}
