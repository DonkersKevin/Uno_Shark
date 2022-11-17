package be.switchfully.uno_shark.domain.parking;

import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.domain.person.address.Address;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

class ParkinglotTest {

    @Test
    void ifNameNull_ThrowIllegalArgument() {
        //GIVEN
        assertThatThrownBy(() -> new Parkinglot(null, null, null, 100, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid name.");
    }

    @Test
    void ifParkingCategoryIsNull_ThrowIllegalArgument() {
        //GIVEN
        assertThatThrownBy(() -> new Parkinglot("De Brouckere", null, null, 100, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid Parking Category.");
    }

    @Test
    void ifDivisionIsNull_ThrowIllegalArgument() {
        //GIVEN
        assertThatThrownBy(() -> new Parkinglot("De Brouckere", ParkingCategory.UNDERGROUND, null, 100, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid Division for this parking lot.");
    }

    @Test
    void ifCapacityIsLowerThanZero_ThrowIllegalArgument() {
        //GIVEN
        assertThatThrownBy(() -> new Parkinglot("De Brouckere", ParkingCategory.UNDERGROUND, new Division(), -100, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid capacity for this parking lot.");
    }

    @Test
    void ifPersonIsNull_ThrowIllegalArgument() {
        //GIVEN
        assertThatThrownBy(() -> new Parkinglot("De Brouckere", ParkingCategory.UNDERGROUND, new Division(), 100, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid Contactperson for this parking lot.");
    }

    @Test
    void ifAddressIsNull_ThrowIllegalArgument() {
        //GIVEN
        assertThatThrownBy(() -> new Parkinglot("De Brouckere", ParkingCategory.UNDERGROUND, new Division(), 100, new Person(), null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid address for this parking lot.");
    }

    @Test
    void ifPricePerHourIsNull_ThrowIllegalArgument() {
        //GIVEN
        assertThatThrownBy(() -> new Parkinglot("De Brouckere", ParkingCategory.UNDERGROUND, new Division(), 100, new Person(), new Address(), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid price per hour.");
    }

    @Test
    void ifGivenCorrectParkingLot_thenAllOke() {
        Parkinglot parkinglot = new Parkinglot("De Brouckere", ParkingCategory.UNDERGROUND, new Division(), 100, new Person(), new Address(), new Price());
        assertThat(parkinglot).isNotNull();
    }
}