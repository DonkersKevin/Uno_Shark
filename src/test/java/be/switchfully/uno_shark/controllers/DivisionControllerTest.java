package be.switchfully.uno_shark.controllers;


import be.switchfully.uno_shark.domain.parking.dto.CreateDivisionDto;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import net.bytebuddy.asm.Advice;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class DivisionControllerTest {

    @Autowired
    DivisionRepository divisionRepository;

    @LocalServerPort
    private int port;

    @Test
    void createDivisionHappyPath(){
        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .body(new CreateDivisionDto(1, "New Division", "Old Division Name","Gigachad"))
                .contentType(JSON)
                .post("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(divisionRepository.findById(1L)).isPresent();
    }
}