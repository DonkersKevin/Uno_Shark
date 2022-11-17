package be.switchfully.uno_shark.domain.parking;

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
}