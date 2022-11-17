package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.parking.divisionDto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import be.switchfully.uno_shark.services.DivisionService;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DivisionControllerTest {

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    DivisionService divisionService;

    @LocalServerPort
    private int port;

    @Test
    void createDivisionHappyPath() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(new CreateDivisionDto(1, "New Division", "Old Division Name", "Gigachad"))
                .contentType(JSON)
                .post("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(divisionRepository.findById(1L)).isPresent();
        Assertions.assertThat(divisionRepository.findById(1L).orElseThrow().getParent()).isNull();
    }

    @Test
    void whenEmptyField_illegalArgumentExceptionIsThrown() {
        Response response = given()
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
        divisionService.createDivision(new CreateDivisionDto(0, "Parent Division", "Old Division", "Gigachad"));
        divisionService.createDivision(new CreateDivisionDto(1, "Subdivision", "Old Division", "Ligma Johnson"));

        ShowDivisionDto[] response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .get("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract().as(ShowDivisionDto[].class);

        assertEquals(response.length, divisionRepository.findAll().size());

        System.out.println(Arrays.stream(response).map(ShowDivisionDto::toString).collect(Collectors.toList()));
    }

    @Test
    void getASingleDivisionHappyPath(){
       divisionService.createDivision(new CreateDivisionDto(0, "Parent Division", "Old Division", "Gigachad"));
        divisionService.createDivision(new CreateDivisionDto(1, "Subdivision", "Old Division", "Ligma Johnson"));


       Response response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .get("/divisions/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract().response();

        response.body().prettyPrint();
    }

    @Test
    void whenGetSingleDivision_andNoSuchDivisionExists_IllegalArgumentExceptionIsThrown(){
        Response response = given()
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