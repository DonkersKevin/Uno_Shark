package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.person.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
AddressRepository extends JpaRepository<Address, Long> {
    //Todo fix , might not be used, or use if 'Example' doesnt work
    Address getAddressByStreetNameAndHouseNumberAndCountry(String streetname, String housenumber, String country);

}
