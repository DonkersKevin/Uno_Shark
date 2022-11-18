package be.switchfully.uno_shark;

import be.switchfully.uno_shark.domain.person.IssuingCountry;
import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.User;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.address.PostalCode;
import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.domain.person.dto.UserDto;
import be.switchfully.uno_shark.domain.person.dto.UserDtoLimitedInfo;
import be.switchfully.uno_shark.domain.person.phonenumber.CountryCode;
import be.switchfully.uno_shark.domain.person.phonenumber.LandLinePhone;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;
import be.switchfully.uno_shark.repositories.UserRepository;
import be.switchfully.uno_shark.services.MemberService;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
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

import java.util.List;

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

    @Autowired
    MemberService memberService;

    @Test
    void createNewMemberHappyPath() {
        PostalCode newPostalCode = new PostalCode("2000", "Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC123");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber(new LandLinePhone("070001122", CountryCode.BELGIUM))
                .setMobileNumber(new MobilePhone("0478123456", CountryCode.BELGIUM))
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
        Assertions.assertThat(userRepository.findById(1L).orElseThrow().getMemberLevel()).isEqualByComparingTo(GOLD);
    }

    @Test
    void whenEmptyAddress_illegalArgumentExceptionIsThrown() {
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC123");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(null)
                .setPhoneNumber(new LandLinePhone("070001122", CountryCode.BELGIUM))
                .setMobileNumber(new MobilePhone("0478123456", CountryCode.BELGIUM))
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
        PostalCode newPostalCode = new PostalCode("2000", "Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber(new LandLinePhone("070001122", CountryCode.BELGIUM))
                .setMobileNumber(new MobilePhone("0478123456", CountryCode.BELGIUM))
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
        PostalCode newPostalCode = new PostalCode("2000", "Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC124");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber(new LandLinePhone("070001122", CountryCode.BELGIUM))
                .setMobileNumber(new MobilePhone("0478123456", CountryCode.BELGIUM))
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
                .setPhoneNumber(new LandLinePhone("070001122", CountryCode.BELGIUM))
                .setMobileNumber(new MobilePhone("0478123456", CountryCode.BELGIUM))
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


    @Test
    void getAllUsersHappyPath() {
        List<UserDtoLimitedInfo> userList = memberService.getAllMembers();


        List<UserDtoLimitedInfo> response = List.of(given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/members")
                .as(UserDtoLimitedInfo[].class));

        assertEquals(response, userList);

    }

    @Test
    void getAMemberHappyPath(){
        UserDto response = given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/members/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(UserDto.class);

        Assertions.assertThat(response.getMemberLevel()).isEqualByComparingTo(GOLD);
    }

    @Test
    void whenMemberDoesNotExist_IllegalArgumentExceptionIsThrown(){
        Response response = given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/members/10000000000")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("No such user exists!", response.jsonPath().getString("message"));
    }
}

