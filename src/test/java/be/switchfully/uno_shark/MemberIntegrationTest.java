package be.switchfully.uno_shark;

import be.switchfully.uno_shark.domain.person.IssuingCountry;
import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.MembershipLevel;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.address.PostalCode;
import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.repositories.UserRepository;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static be.switchfully.uno_shark.domain.person.MembershipLevel.BRONZE;
import static be.switchfully.uno_shark.domain.person.MembershipLevel.GOLD;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
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
        Assertions.assertThat(userRepository.findById(1L).orElseThrow().getMemberLevel()).isEqualByComparingTo(BRONZE);
    }

    @Test
    void whenEmptyAddress_illegalArgumentExceptionIsThrown() {
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC123" );

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(null)
                .setPhoneNumber("003487442233")
                .setMobileNumber("+32487442233")
                .setEmailAddress("Freddi@Fish.be")
                .setLicensePlate(newLicensePlate);

        Response response = given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(newUser)
                .contentType(JSON)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("Provide an address please!", response.jsonPath().getString("message"));

    }

    @Test
    void whenEmptyLicensePlate_illegalArgumentExceptionIsThrown() {
        PostalCode newPostalCode = new PostalCode("2000","Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber("003487442233")
                .setMobileNumber("+32487442233")
                .setEmailAddress("Freddi@Fish.be")
                .setLicensePlate(null);

        Response response = given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(newUser)
                .contentType(JSON)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("Provide an license plate please!", response.jsonPath().getString("message"));

    }

    @Test
    void createNewMemberGold() {
        PostalCode newPostalCode = new PostalCode("2000","Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC124" );

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber("003487442233")
                .setMobileNumber("+32487442233")
                .setEmailAddress("Freddi@Fish.be")
                .setLicensePlate(newLicensePlate)
                .setMemberLevel(GOLD);

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
        Assertions.assertThat(userRepository.findById(1L).orElseThrow().getMemberLevel()).isEqualByComparingTo(GOLD);
    }

    @Test
    void whenSameLicensePlate_illegalArgumentExceptionIsThrown() {
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

        Response response = given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(newUser)
                .contentType(JSON)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("This license plate is already registered!", response.jsonPath().getString("message"));

    }
}
