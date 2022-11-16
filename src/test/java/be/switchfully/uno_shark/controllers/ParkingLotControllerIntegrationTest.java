package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotDto;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import be.switchfully.uno_shark.services.ParkingLotService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ParkingLotControllerIntegrationTest {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    ParkingLotService parkingLotService;

    @LocalServerPort
    private int port;

    @Test
    void createParkinglot_HappyPath() {
        //ARRANGE

        String requestBody = "    {\n" +
                "        \"name\": \"Goeminne2\",\n" +
                "        \"parkingCategory\": \"UNDERGROUND\",\n" +
                "        \"division\": {\n" +
                "            \"name\":\"FSAB\",\n" +
                "            \"originalName\":\"BASF\",\n" +
                "            \"director\":\"JoengeJoenge\"\n" +
                "        },\n" +
                "        \"capacity\": 400,\n" +
                "        \"address\": {\n" +
                "            \"streetName\": \"Langestraat\",\n" +
                "            \"houseNumber\": \"22\",\n" +
                "            \"postalCode\": {\n" +
                "                \"postalCode\": \"3000\",\n" +
                "                \"city\": \"Leuven\"\n" +
                "            },\n" +
                "            \"country\": \"Belgiuum\"\n" +
                "        },\n" +
                "        \"pricePerHour\": {\n" +
                "            \"amount\": 8.0,\n" +
                "            \"currency\": \"EUR\"\n" +
                "        },\n" +
                "        \"person\": {\n" +
                "            \"name\": \"Jilly\"\n" +
                "        }\n" +
                "    }";

        //ACT
        ParkingLotDto result = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestBody)
                .when()
                .accept(ContentType.JSON)
                .post("/parkinglots")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ParkingLotDto.class);

        //ASSES
        assertThat(result).isNotNull();
    }
}