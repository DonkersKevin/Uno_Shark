package be.switchfully.uno_shark.domain.person.licenseplate;

import javax.persistence.*;

@Entity
@Table(name = "license_plate")
public class LicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "licenseplate_seq")
    @SequenceGenerator(name = "licenseplate_seq", sequenceName = "licenseplate_seq", allocationSize = 1)
    private long id;

    @Column(name = "issuing_country")
    @Enumerated(EnumType.STRING)
    private IssuingCountry issuingCountry;
    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    public LicensePlate() {
    }

    public LicensePlate(IssuingCountry issuingCountry, String licensePlateNumber) {
        this.issuingCountry = issuingCountryVerification(issuingCountry);
        this.licensePlateNumber = licenseplateNumberVerification(licensePlateNumber);
    }

    private IssuingCountry issuingCountryVerification(IssuingCountry issuingCountry) {
        if (issuingCountry == null) {
            throw new IllegalArgumentException("Issuing country has to be provided.");
        }
        return issuingCountry;
    }

    private String licenseplateNumberVerification(String licensePlateNumber) {
        if (licensePlateNumber == null) {
            throw new IllegalArgumentException("License plate number has to be provided.");
        }
        return licensePlateNumber;
    }

    public IssuingCountry getIssuingCountry() {
        return issuingCountry;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicensePlate that)) return false;

        if (getIssuingCountry() != that.getIssuingCountry()) return false;
        return getLicensePlateNumber() != null ? getLicensePlateNumber().equals(that.getLicensePlateNumber()) : that.getLicensePlateNumber() == null;
    }

}
