package utils;


import io.restassured.RestAssured;
import model.Pet;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;

public class TestBase {

    protected static final int PET_ID = 12345;
    protected static final String PET_NAME = "Limon";
    protected static final String PET_STATUS = "available";
    protected static final String PHOTO_URL = "https://example.com/photo1.jpg";
    protected static final String invalidJson = "{ \"id\": invalid}";
    protected static final int nonExistendPetID = 1234555;
    protected static final int negativePetID = -1;
    protected static Pet pet;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        pet = Pet.builder()
                .id(PET_ID)
                .name(PET_NAME)
                .status(PET_STATUS)
                .photoUrls(Arrays.asList(PHOTO_URL))
                .build();
    }
}