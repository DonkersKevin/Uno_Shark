package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.parking.Division;
import be.switchfully.uno_shark.domain.person.User;
import be.switchfully.uno_shark.domain.person.licenseplate.IssuingCountry;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicensePlateRepository extends JpaRepository<LicensePlate, Long> {
    LicensePlate findLicensePlateByIssuingCountryAndLicensePlateNumber(IssuingCountry issuingCountry, String licensePlateNumber);
}
