package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Usar porta aletoria para evitar error
class ParkingControllerTest {

    // Mapeando porta
    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() { RestAssured.port = randomPort; }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
                /*.body("license[0]", Matchers.equalTo("OMS-1111"));*/
                /*.extract().response().body().prettyPrint(); Testa conexão antes de fazer o teste*/
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        ParkingCreateDTO createDTO = new ParkingCreateDTO();
        createDTO.setColor("PRETO");
        createDTO.setLicense("JPA-1212");
        createDTO.setModel("MERCEDES");
        createDTO.setState("BA");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("JPA-1212"))
                .body("state", Matchers.equalTo("BA"))
                .body("model", Matchers.equalTo("MERCEDES"))
                .body("color", Matchers.equalTo("PRETO"));
    }
}