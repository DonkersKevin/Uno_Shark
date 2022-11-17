package be.switchfully.uno_shark.controllers;import be.switchfully.uno_shark.domain.parking.ParkingCategory;import be.switchfully.uno_shark.domain.parking.Price;import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotDto;import be.switchfully.uno_shark.domain.person.Person;import be.switchfully.uno_shark.domain.person.address.Address;import be.switchfully.uno_shark.domain.person.address.PostalCode;import be.switchfully.uno_shark.repositories.ParkingLotRepository;import be.switchfully.uno_shark.services.ParkingLotService;import io.restassured.RestAssured;import io.restassured.http.ContentType;import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.web.server.LocalServerPort;import org.springframework.http.HttpStatus;import java.util.Currency;import java.util.List;import static org.assertj.core.api.Assertions.assertThat;import static org.junit.jupiter.api.Assertions.assertEquals;@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)@AutoConfigureTestDatabaseclass ParkingLotControllerIntegrationTest {    @Autowired    ParkingLotRepository parkingLotRepository;    @Autowired    ParkingLotService parkingLotService;    @LocalServerPort    private int port;    @Test    void createParkinglot_HappyPath() {        //ARRANGE        String requestBody = """                    {                    "name": "Goeminne",                    "parkingCategory": "UNDERGROUND",                    "division": {                        "parentName": "No Parent Division",                        "name": "UGC",                        "originalName": "KBC",                        "director": "Freddy"                    },                    "capacity": 400,                    "address": {                        "streetName": "Langestraat",                        "houseNumber": "22",                        "postalCode": {                            "postalCode": "3000",                            "city": "Leuven"                        },                        "country": "Belgium"                    },                    "pricePerHour": {                        "amount": 8.0,                        "currency": "EUR"                    },                    "person": {                        "firstName": "Freddy",                        "lastName": "Broeckx",                        "address": {                            "streetName": "Nieuwestraat",                            "houseNumber": "44",                            "postalCode": {                                "postalCode": "1000",                                "city": "Brussel"                            },                            "country": "Belgium"                        },                        "phoneNumber": "032261018",                        "mobileNumber": "0473876578",                        "emailAddress": "testing3@email.com"                    }                }""";        ParkingLotDto expected = new ParkingLotDto(1L, "Goeminne", ParkingCategory.UNDERGROUND,                new ShowDivisionDto(1, "No Parent Division", "UGC", "KBC", "Freddy"), 400,                new Person("Freddy", "Broeckx",                        new Address(2L, "Nieuwestraat", "44", new PostalCode("1000", "Brussel"), "Belgium"),                        "032261018", "0473876578", "testing3@email.com"),                new Address(1L, "Langestraat", "22", new PostalCode("3000", "Leuven"), "Belgium"),                new Price(8.0, Currency.getInstance("EUR")));        //ACT        ParkingLotDto result =                RestAssured                        .given()                        .contentType(ContentType.JSON)                        .baseUri("http://localhost")                        .port(port)                        .body(requestBody)                        .when()                        .accept(ContentType.JSON)                        .post("/parkinglots")                        .then()                        .assertThat()                        .statusCode(HttpStatus.CREATED.value())                        .extract()                        .as(ParkingLotDto.class);        //ASSES        assertThat(result).isNotNull();        // assertThat(result).isEqualTo(expected);        //assertEquals(result, expected);    }    @Test    void getAllParkingLots_HappyPath() {        ParkingLotDto[] result = RestAssured                .given()                .contentType(ContentType.JSON)                .baseUri("http://localhost")                .port(port)                .when()                .accept(ContentType.JSON)                .get("/parkinglots")                .then()                .assertThat()                .statusCode(HttpStatus.OK.value())                .extract()                .as(ParkingLotDto[].class);        assertThat(result.length == 0);    }}