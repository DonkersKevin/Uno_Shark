package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.repositories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address checkAddressForDuplicatesByFields(Address address) {
        log.info("Checking database for existing address based on fields.");
        Example<Address> example = Example.of(address);
        Optional<Address> searchresult = addressRepository.findOne(example);
        return searchresult.orElse(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll().stream().toList();
    }
}
