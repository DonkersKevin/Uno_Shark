package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.parkingspotallocation.dto.ShowAllocationDto;
import be.switchfully.uno_shark.repositories.SpotAllocationRepository;
import be.switchfully.uno_shark.services.SpotAllocationService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ParkingSpotAllocationControllerTest {

    @Autowired
    private SpotAllocationRepository spotRepo;

    @Autowired
    private SpotAllocationService spotService;

    @LocalServerPort
    private int port;

    @Test
    void findAllWithoutFilterHappyPath(){
        ShowAllocationDto[] response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocation")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertEquals(response.length, spotRepo.findAll().size());

    }

    @Test
    void findAllWithLimitHappyPath(){
        ShowAllocationDto[] response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocation?limit=2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertEquals(response.length, 2);

    }

    @Test
    void findAllDescendingHappyPath(){
        ShowAllocationDto[] response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocation?sort=descending")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertThat(response).isSortedAccordingTo(Comparator.reverseOrder());

    }

    @Test
    void findAllStatusActiveHappyPath(){
        ShowAllocationDto[] response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocation?status=active")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertThat(Arrays.stream(response)
                .filter(showAllocationDto -> showAllocationDto.getStopTime() != null).toList()).isEmpty();

    }

    @Test
    void findAllStatusStoppedHappyPath(){
        ShowAllocationDto[] response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .get("/spotallocation?status=stopped")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ShowAllocationDto[].class);

        assertThat(Arrays.stream(response)
                .filter(showAllocationDto -> showAllocationDto.getStopTime() == null).toList()).isEmpty();

    }

    @Test
    void stopParkingHappyPath(){
        given().baseUri("http://localhost")
                .port(port)
                .when()
                .put("/spotallocation/3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        assertThat(spotRepo.findById(3L).orElseThrow().isActive()).isFalse();
        assertThat(spotRepo.findById(3L).orElseThrow().getEndTime()).isNotNull();
    }

    @Test
    void stopParkingWhenAlreadyStopped_ThrowsException(){
        Response response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .put("/spotallocation/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("This allocation has already been stopped!", response.jsonPath().getString("message"));
    }

    @Test
    void stopParkingWhenAllocationDoesntExist_ThrowsException(){
        Response response = given().baseUri("http://localhost")
                .port(port)
                .when()
                .put("/spotallocation/1000000")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        assertEquals("No such parking allocation exists!", response.jsonPath().getString("message"));
    }



}