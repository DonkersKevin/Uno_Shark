package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PersonTest {
    @Test
    void whenNoPhoneNumberGiven_ThrowIllegalArgument(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("a","a",new Address(),null,null,"");
        });

        String expectedMessage = "No phone number found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}