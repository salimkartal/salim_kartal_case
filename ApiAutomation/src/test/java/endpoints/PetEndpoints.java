package endpoints;



import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Pet;

import static io.restassured.RestAssured.given;

public class PetEndpoints {

    public static Response addPet(Pet pet) {
        return given()
                .contentType("application/json")
                .body(pet)
                .when()
                .post("/pet");
    }

    public static Response getPetById(int petId) {
        return given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}");
    }

    public static Response updatePet(Pet pet) {
        return given()
                .contentType("application/json")
                .body(pet)
                .when()
                .put("/pet");
    }

    public static Response deletePet(int petId) {
        return given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}");
    }

    // Negatif Senaryolar
    public static Response addInvalidPet(String invalidJson) {
        return given()
                .contentType("application/json")
                .body(invalidJson)
                .when()
                .post("/pet");
    }

    public static Response getPet(int petId) {
        return given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}");
    }

    public static Response updateNonExistentPet(String invalidJson) {
        return given()
                .contentType("application/json")
                .body(invalidJson)
                .when()
                .put("/pet");
    }

    public static Response deleteNonExistentPet(int petId) {
        return given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}");
    }
}