package tests;


import endpoints.PetEndpoints;
import io.restassured.response.Response;
import model.Pet;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import utils.TestBase;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetStoreAPITest extends TestBase {

    @Test
    @Order(1)
    public void testAddPet() {
        Response response = PetEndpoints.addPet(pet);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    public void testAddPetFail() {
        Response response = PetEndpoints.addInvalidPet(invalidJson);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(3)
    public void testGetPet() {
        Response response = PetEndpoints.getPetById(PET_ID);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        Pet pet = response.as(Pet.class);
        Assertions.assertEquals(PET_ID, pet.getId());
        Assertions.assertEquals(PET_NAME, pet.getName());
    }

    @Test
    @Order(4)
    public void testGetPetFail() {
        Response response = PetEndpoints.getPetFail(nonExistendPetID);
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Order(5)
    public void testUpdatePet() {
        pet.setStatus("sold");
        Response response = PetEndpoints.updatePet(pet);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @Test
    @Order(6)
    public void testUpdatePetFail() {
        Response response = PetEndpoints.updateNonExistentPet(invalidJson);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(7)
    public void testDeletePet() {
        Response response = PetEndpoints.deletePet(PET_ID);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @Test
    @Order(8)
    public void testDeletePetFail() {
        Response response = PetEndpoints.deleteNonExistentPet(negativePetID);
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }
}