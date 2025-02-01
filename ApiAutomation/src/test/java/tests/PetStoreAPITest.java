package tests;


import endpoints.PetEndpoints;
import io.restassured.response.Response;
import model.Pet;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.PropertyManager;
import utils.TestBase;


import java.util.Arrays;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetStoreAPITest extends TestBase {

    PropertyManager propertyManager = new PropertyManager();

    @Test
    public void testAddPet() {
        Pet pet = Pet.builder()
                .id(12345)
                .name("Buddy")
                .status("available")
                .photoUrls(Arrays.asList("https://example.com/photo1.jpg"))
                .build();

        Response response = PetEndpoints.addPet(pet);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testGetPetById() {
        int petId = 12345;
        Response response = PetEndpoints.getPetById(petId);
        Assertions.assertEquals(200, response.getStatusCode());

        Pet pet = response.as(Pet.class);
        Assertions.assertEquals(petId, pet.getId());
        Assertions.assertEquals("Buddy", pet.getName());
    }

    @Test
    public void testUpdatePet() {
        Pet pet = Pet.builder()
                .id(12345)
                .name("Buddy")
                .status("sold")
                .photoUrls(Arrays.asList("https://example.com/photo1.jpg"))
                .build();

        Response response = PetEndpoints.updatePet(pet);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @ParameterizedTest
    @MethodSource("getPetFailProvider")
    public void testGetPetFail() {

        int petId = 1234555;
        Response response = PetEndpoints.getPet(petId);
        Assertions.assertEquals(400, response.getStatusCode());
       // Assertions.assertEquals(response.jsonPath().getString("message"), message);
     //   Assertions.assertEquals(response.jsonPath().getString("type"), type);
       // Assertions.assertEquals(response.jsonPath().getString("code"), code);
    }

    @Test
    public void testGetNonExistentPet() {
        int petId = 12345345; // Var olmayan bir pet ID

        try {
            // Yanıtı al
            Response response = given()
                    .when()
                    .get("https://petstore.swagger.io/v2/pet/" + petId)
                    .then()
                    .extract()
                    .response();

            // 404 durum kodunu doğrulayın
            Assertions.assertEquals(404, response.getStatusCode());
        } catch (Exception e) {
            // Hata mesajını yazdır
        }
}

        public Object[][] getPetFailProvider(){
        return new Object[][]{
                {propertyManager.getProperty("pet.properties", "invalidIdCharacter"), HttpStatus.SC_NOT_FOUND, "404", propertyManager.getProperty("pet.properties","numberFormatExceptionMessage") +"\"" + propertyManager.getProperty("pet.properties","invalidIdCharacter")+"\"" , "unknown"},
                {propertyManager.getProperty("pet.properties", "invalidIdLong"), HttpStatus.SC_NOT_FOUND, "404", propertyManager.getProperty("pet.properties","numberFormatExceptionMessage") +"\"" + propertyManager.getProperty("pet.properties","invalidIdLong")+"\"" , "unknown"},
                {propertyManager.getProperty("pet.properties", "invalidIdNegative"), HttpStatus.SC_NOT_FOUND, "1", propertyManager.getProperty("pet.properties","petNotFoudMessage"), "error"},
        };
    }

    @Test
    public void testDeletePet() {
        int petId = 12345;
        Response response = PetEndpoints.deletePet(petId);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testAddInvalidPet() {
        // Geçersiz bir JSON gönder
        String invalidJson = "{ \"id\": invalid}";

        Response response = PetEndpoints.addInvalidPet(invalidJson);
        Assertions.assertEquals(400, response.getStatusCode()); // Geçersiz istek durum kodu
    }



    @Test
    public void testUpdateNonExistentPet() {
        String invalidJson = "{ \"id\": invalid}";

        Response response = PetEndpoints.updateNonExistentPet(invalidJson);
        Assertions.assertEquals(response.getStatusCode(),400); // Bulunamadı durum kodu
    }

    @Test
    public void testDeleteNonExistentPet() {
        int petId = -1; // Var olmayan bir pet ID
        Response response = PetEndpoints.deleteNonExistentPet(petId);
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode()); // Bulunamadı durum kodu
    }
}