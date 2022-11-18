package be.switchfully.uno_shark.controllers;


import be.switchfully.uno_shark.domain.parkingspotallocation.dto.CreateParkingSpotAllocationDto;
import be.switchfully.uno_shark.domain.person.licenseplate.IssuingCountry;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.repositories.SpotAllocationRepository;
import be.switchfully.uno_shark.services.SpotAllocationService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class SpotAllocationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    SpotAllocationRepository spotAllocationRepository;

    @Autowired
    SpotAllocationService spotAllocationService;

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
    void createNewSpotAllocationHappyPath() {
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE, "ABC123");
        CreateParkingSpotAllocationDto createParkingSpotAllocationDto = new CreateParkingSpotAllocationDto(1L, licensePlate, 1L);

        given()
                .baseUri("http://localhost")
                .header("Authorization", "Bearer " + memberToken)
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    void createNewSpotAllocation_asManager_Forbidden() {
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE, "ABC123");
        CreateParkingSpotAllocationDto createParkingSpotAllocationDto = new CreateParkingSpotAllocationDto(1L, licensePlate, 1L);

        given()
                .baseUri("http://localhost")
                .header("Authorization", "Bearer " + managerToken)
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void createNewSpotAllocation_whenProvidingUnknownUserId_ThenThrowError(){
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE, "ABC123");
        CreateParkingSpotAllocationDto createParkingSpotAllocationDto = new CreateParkingSpotAllocationDto(12L, licensePlate, 1L);

        Response response = given()
                .baseUri("http://localhost")
                .header("Authorization", "Bearer " + memberToken)
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("Unknown userID.", response.jsonPath().getString("message"));
    }

    @Test
    void createNewSpotAllocation_whenProvidedLicensePlateDoesNotMatchMemberLP_ThenThrowError(){
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE, "ABC123");
        CreateParkingSpotAllocationDto createParkingSpotAllocationDto = new CreateParkingSpotAllocationDto(3L, licensePlate, 1L);

        Response response = given()
                .baseUri("http://localhost")
                .header("Authorization", "Bearer " + memberToken)
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("provided license plate is not the same as member's license plate.", response.jsonPath().getString("message"));
    }

    @Test
    void createNewSpotAllocation_whenProvidedLicensePlateDoesNotMatchMemberLP_ButMemberIsGold_HappyPath(){
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE, "ABC125");
        CreateParkingSpotAllocationDto createParkingSpotAllocationDto = new CreateParkingSpotAllocationDto(1L, licensePlate, 1L);

        given()
                .baseUri("http://localhost")
                .header("Authorization", "Bearer " + memberToken)
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void createNewSpotAllocation_whenProvidedParkingLotIdIsUnknown_ThenThrowError(){
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE, "ABC123");
        CreateParkingSpotAllocationDto createParkingSpotAllocationDto = new CreateParkingSpotAllocationDto(1L, licensePlate, 12L);

        Response response = given()
                .baseUri("http://localhost")
                .header("Authorization", "Bearer " + memberToken)
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("Unknown parking lot.", response.jsonPath().getString("message"));
    }

    @Test
    void createNewSpotAllocation_whenCapacityHasBeenReached_ThenThrowError(){
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE, "ABC123");
        CreateParkingSpotAllocationDto createParkingSpotAllocationDto = new CreateParkingSpotAllocationDto(1L, licensePlate, 1L);

        given()
                .baseUri("http://localhost")
                .header("Authorization", "Bearer " + memberToken)
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Response response = given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(createParkingSpotAllocationDto)
                .contentType(JSON)
                .post("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();


        assertEquals("parking is full!", response.jsonPath().getString("message"));
    }

}
