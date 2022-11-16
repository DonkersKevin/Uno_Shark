package be.switchfully.uno_shark;

import be.switchfully.uno_shark.domain.person.IssuingCountry;
import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.address.PostalCode;
import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class MemberIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepository;

    @Test
    void createNewMemberHappyPath() {
        PostalCode newPostalCode = new PostalCode("2000","Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC123" );

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber("003487442233")
                .setMobileNumber("+32487442233")
                .setEmailAddress("Freddi@Fish.be")
                .setLicensePlate(newLicensePlate);

        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(newUser)
                .contentType(JSON)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(userRepository.findById(1L)).isPresent();
    }
}