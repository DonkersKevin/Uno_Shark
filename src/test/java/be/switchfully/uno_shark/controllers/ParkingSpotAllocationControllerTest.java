package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.parkingspotallocation.dto.ShowAllocationDto;
import be.switchfully.uno_shark.repositories.SpotAllocationRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Comparator;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ParkingSpotAllocationControllerTest {

    @Autowired
    private SpotAllocationRepository spotRepo;

    @LocalServerPort
    private int port;

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
    void findAllWithoutFilterHappyPath() {
        ShowAllocationDto[] response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertEquals(response.length, spotRepo.findAll().size());
    }

    @Test
    void findAllWithoutFilter_asMember_Forbidden() {
        given()
                .header("Authorization", "Bearer " + memberToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocations")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract();
    }

    @Test
    void findAllWithLimitHappyPath() {
        ShowAllocationDto[] response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocations?limit=2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertEquals(response.length, 2);

    }

    @Test
    void findAllDescendingHappyPath() {
        ShowAllocationDto[] response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocations?sort=descending")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertThat(response).isSortedAccordingTo(Comparator.reverseOrder());

    }

    @Test
    void findAllStatusActiveHappyPath() {
        ShowAllocationDto[] response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocations?status=active")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertThat(Arrays.stream(response)
                .filter(showAllocationDto -> showAllocationDto.getStopTime() != null).toList()).isEmpty();

    }

    @Test
    void findAllStatusStoppedHappyPath() {
        ShowAllocationDto[] response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocations?status=stopped")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertThat(Arrays.stream(response)
                .filter(showAllocationDto -> showAllocationDto.getStopTime() == null).toList()).isEmpty();

    }

    @Test
    void stopParkingHappyPath() {
        given()
                .header("Authorization", "Bearer " + memberToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .put("/spotallocations/3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        assertThat(spotRepo.findById(3L).orElseThrow().isActive()).isFalse();
        assertThat(spotRepo.findById(3L).orElseThrow().getEndTime()).isNotNull();
    }

    @Test
    void stopParking_asManager_Forbidden() {
        given()
                .header("Authorization", "Bearer " +managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .put("/spotallocations/3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void stopParkingWhenAlreadyStopped_ThrowsException() {
        Response response = given()
                .header("Authorization", "Bearer " + memberToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .put("/spotallocations/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("This allocation has already been stopped!", response.jsonPath().getString("message"));
    }

    @Test
    void stopParkingWhenAllocationDoesntExist_ThrowsException() {
        Response response = given()
                .header("Authorization", "Bearer " + memberToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .put("/spotallocations/1000000")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("No such parking allocation exists!", response.jsonPath().getString("message"));
    }


}