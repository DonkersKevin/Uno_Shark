package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.parking.divisionDto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.SingleDivisionDto;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import be.switchfully.uno_shark.services.DivisionService;
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


import java.util.Arrays;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class DivisionControllerTest {

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    DivisionService divisionService;

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
    void createDivisionHappyPath() {
        given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(new CreateDivisionDto(1, "New Division", "Old Division Name", "Gigachad"))
                .contentType(JSON)
                .post("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(divisionRepository.findById(4L).orElseThrow().getDirector()).isEqualTo("Gigachad");
    }

    @Test
    void createDivision_asMember_Forbidden() {
        given()
                .header("Authorization", "Bearer " + memberToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(new CreateDivisionDto(1, "New Division", "Old Division Name", "Gigachad"))
                .contentType(JSON)
                .post("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void whenEmptyField_illegalArgumentExceptionIsThrown() {
        Response response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(new CreateDivisionDto(1, null, "Old Division Name", "Gigachad"))
                .contentType(JSON)
                .post("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("Name can not be empty!", response.jsonPath().getString("message"));

    }

    @Test
    void getAllDivisionsHappyPath() {

        ShowDivisionDto[] response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowDivisionDto[].class);

        assertEquals(response.length, divisionRepository.findAll().size());

        System.out.println(Arrays.stream(response).map(ShowDivisionDto::toString).collect(Collectors.toList()));
    }

    @Test
    void getAllDivisions_asMember_Forbidden() {

        given()
                .header("Authorization", "Bearer " + memberToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract();
    }

    @Test
    void getASingleDivisionHappyPath() {

        SingleDivisionDto response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/divisions/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(SingleDivisionDto.class);

        assertEquals(response.getName(), "UGC");
    }

    @Test
    void getASingleDivision_asMember_Forbidden() {

        given()
                .header("Authorization", "Bearer " + memberToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/divisions/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract();

    }

    @Test
    void whenGetSingleDivision_andNoSuchDivisionExists_IllegalArgumentExceptionIsThrown() {
        Response response = given()
                .header("Authorization", "Bearer " + managerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/divisions/1000000000")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("No such division exists!", response.jsonPath().getString("message"));
    }

}