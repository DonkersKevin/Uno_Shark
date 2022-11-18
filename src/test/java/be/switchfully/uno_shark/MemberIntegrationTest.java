package be.switchfully.uno_shark;

import be.switchfully.uno_shark.domain.person.licenseplate.IssuingCountry;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.address.PostalCode;
import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.domain.person.dto.UserDto;
import be.switchfully.uno_shark.domain.person.dto.UserDtoLimitedInfo;
import be.switchfully.uno_shark.repositories.UserRepository;
import be.switchfully.uno_shark.services.MemberService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static be.switchfully.uno_shark.domain.person.MembershipLevel.*;

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

    private static String managerToken;
    private static String memberToken;

    @BeforeAll
    static void generateManagerToken() {
        managerToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("username", "manager")
                .formParam("password", "manager")
                .formParam("grant_type", "password")
                .formParam("client_id", "parkSharkoUno")
                .formParam("client_secret", "a50b122c-462f-4f5f-996c-2af33b6506be")
                .when()
                .post("https://keycloak.switchfully.com/auth/realms/sharkoUno/protocol/openid-connect/token")
                .then()
                .extract()
                .path("access_token")
                .toString();
    }

    @BeforeAll
    static void generateMemberToken() {
        memberToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("username", "member")
                .formParam("password", "member")
                .formParam("grant_type", "password")
                .formParam("client_id", "parkSharkoUno")
                .formParam("client_secret", "a50b122c-462f-4f5f-996c-2af33b6506be")
                .when()
                .post("https://keycloak.switchfully.com/auth/realms/sharkoUno/protocol/openid-connect/token")
                .then()
                .extract()
                .path("access_token")
                .toString();
    }

    @Test
    void createNewMemberHappyPath() {
        PostalCode newPostalCode = new PostalCode("2000", "Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC123");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber("003487442233")
                .setMobileNumber("+32487442233")
                .setEmailAddress("Freddi@Fish.be")
                .setLicensePlate(newLicensePlate)
                .setMemberLevel(BRONZE)
                .setUserName("testUser")
                .setPassword("pwd")
                .setRole("member");

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
        PostalCode newPostalCode = new PostalCode("2000", "Antwerp");
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
        PostalCode newPostalCode = new PostalCode("2000", "Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC124");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber("003487442233")
                .setMobileNumber("+32487442233")
                .setEmailAddress("Freddi@Fish.be")
                .setLicensePlate(newLicensePlate)
                .setMemberLevel(GOLD)
                .setUserName("testUser")
                .setPassword("pwd")
                .setRole("member");

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
        PostalCode newPostalCode = new PostalCode("2000", "Antwerp");
        Address newAddress = new Address("fishlane", "23", newPostalCode, "belgium");
        LicensePlate newLicensePlate = new LicensePlate(IssuingCountry.BE, "1ABC123");

        CreateUserDto newUser = new CreateUserDto()
                .setFirstName("Freddi")
                .setLastName("Fish")
                .setAddress(newAddress)
                .setPhoneNumber("003487442233")
                .setMobileNumber("+32487442233")
                .setEmailAddress("Freddi@Fish.be")
                .setLicensePlate(newLicensePlate)
                .setMemberLevel(BRONZE)
                .setUserName("testFreddi")
                .setPassword("pwd")
                .setRole("member");

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


        List<UserDtoLimitedInfo> response = List.of(
                given()
                        .header("Authorization", "Bearer " + managerToken)
                        .baseUri("http://localhost")
                        .port(port)
                        .when()
                        .get("/members")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(UserDtoLimitedInfo[].class));

        assertEquals(response, userList);

    }

    @Test
    void getAMemberHappyPath() {
        UserDto response = given()
                .header("Authorization", "Bearer " + managerToken)
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
    void whenMemberDoesNotExist_IllegalArgumentExceptionIsThrown() {
        Response response = given()
                .header("Authorization", "Bearer " + managerToken)
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

